package nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TestNIO {
	public static void main(String[] args) throws Exception {
		//baseTest();
		//scannerAndGatherTest();
		//transferTest();
		selectorTest();
		
	}
	//FileChannel是一个连接到文件的通道。可以通过文件通道读写文件。
	//FileChannel无法设置为非阻塞模式，它总是运行在阻塞模式下
	public static void baseTest() throws Exception{
		String path = TestNIO.class.getClassLoader().getResource("").getPath()+"/nio/nio-data.txt";
		RandomAccessFile file = new RandomAccessFile(new File(path), "rw");
		FileChannel fileChannel = file.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int count = fileChannel.read(buf);
		StringBuffer sb = new StringBuffer();
		while(count != -1){
			buf.flip();
			if(buf.hasArray()) {
				byte[] bytes = buf.array();
				String str = new String(bytes,buf.position(),buf.limit(),"UTF-8");
				sb.append(str);
			}
//			
//			while(buf.hasRemaining()){
//				System.out.print((char)buf.get());
//			}
			buf.clear();
			count = fileChannel.read(buf);
		}
		System.out.println(sb);
		buf.clear();
		fileChannel.close();
		file.close();
	}
	
	//1.Scatter  从一个Channel读取的信息分散到N个缓冲区中(Buufer).
	//2.Gather  将N个Buffer里面内容按照顺序发送到一个Channel.  
	public static void scannerAndGatherTest() throws Exception{
		String path = TestNIO.class.getClassLoader().getResource("").getPath() + "nio/nio-data.txt";
		RandomAccessFile file = new RandomAccessFile(new File(path), "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer b1 = ByteBuffer.allocate(20);
		ByteBuffer b2 = ByteBuffer.allocate(20);
		ByteBuffer[] bytesArray = {b1,b2};
		long count = channel.read(bytesArray);
		StringBuffer sb = new StringBuffer();
		while(count != -1){
			for(int i = 0;i<bytesArray.length;i++){
				ByteBuffer b = bytesArray[i];
				b.flip();
				String str = new String(b.array(),b.position(),b.limit(),"UTF-8");
				bytesArray[i].clear();
				sb.append(str);
			}
			count = channel.read(bytesArray);
		}
		System.out.println(sb);
		channel.close();
		file.close();
	}
	//通过FileChannel传输文件
	public static void transferTest() throws Exception{
		String path = TestNIO.class.getClassLoader().getResource("").getPath()+"nio/from-nio-data.txt";
		String path2 = TestNIO.class.getClassLoader().getResource("").getPath()+"nio/to-nio-data.txt";

		RandomAccessFile fromFile = new RandomAccessFile(path, "rw");  
		FileChannel      fromChannel = fromFile.getChannel();  
		RandomAccessFile toFile = new RandomAccessFile(path2, "rw");  
		FileChannel      toChannel = toFile.getChannel();  		  
		toChannel.transferFrom(fromChannel, 0, fromChannel.size()); 
		
		ByteBuffer b = ByteBuffer.allocate(1024);
		StringBuffer sb = new StringBuffer();
		int count = toChannel.read(b);
		while(count != -1){
			b.flip();
			byte[] bytes = b.array();
			String str = new String(bytes,b.position(),b.limit(),"UTF-8");
			sb.append(str);
			b.clear();
			count = toChannel.read(b);
		}
		System.out.println(sb);
	}
	//NIo客户端
	public static void selectorTest() throws Exception{

		Selector selector = Selector.open();
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);//设置 SocketChannel 为非阻塞模式（non-blocking mode）.设置之后，就可以在异步模式下调用connect(), read() 和write()了
		SocketAddress address = new InetSocketAddress("127.0.0.1", 9969); 
		socketChannel.connect(address);
		
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		while(true) {  
			int readyChannels = selector.select();  
			if(readyChannels == 0) {
				continue;                                                                 
			}
			Set<SelectionKey> selectedKeys = selector.selectedKeys();  
			Iterator keyIterator = selectedKeys.iterator();  
			while(keyIterator.hasNext()) {  
				SelectionKey key = (SelectionKey) keyIterator.next();  
				keyIterator.remove();
				if (key.isConnectable()) {  
					System.out.println("连接到服务端了");
					SocketChannel sc = (SocketChannel) key.channel();
					if(sc.isConnectionPending()){
						sc.finishConnect();
					}
					sc.write(ByteBuffer.wrap(new String("服务端你好，我是客户端.").getBytes()));
					System.out.println("客户端连接成功");
					
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){
					System.out.println("客户端读取服务端信息");
					SocketChannel sc = (SocketChannel) key.channel();
					ByteBuffer b = ByteBuffer.allocate(1024);
					int count = sc.read(b);
					String str = new String(b.array());
					System.out.println(str);
				}
			}
		}
	} 
	
}
