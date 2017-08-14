package nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.springframework.expression.spel.ast.Selection;

public class TestServerSocketChannel {
	public static void main(String[] args) throws Exception{
		//打开ServerSocketChannel，用于监听客户端的连接，他是所有客户端连接的父管道
		ServerSocketChannel  serverChannel = ServerSocketChannel.open();
		//绑定监听端口
		serverChannel.bind(new InetSocketAddress("127.0.0.1", 9969));
		//设置连接为非阻塞模式
		serverChannel.configureBlocking(false);
		//创建多路复用器
		Selector selector = Selector.open();
		//将ServerSocketChannel注册到多路复用器Serlector上
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		//多路复用器在无限循环体内轮询准备就绪的Key
		while(true){
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator it = keys.iterator();
			while(it.hasNext()){
				SelectionKey key = (SelectionKey) it.next();
				it.remove();
				//多路复用器监听到有新的客户端接入，处理新的接入请求
				if(key.isAcceptable()){
					System.out.println("有客户端连接上了");
					 ServerSocketChannel server = (ServerSocketChannel)key.channel();
                     //获得客户端连接通道
                     SocketChannel sc = server.accept();
                     //设置客户端链路为非阻塞模式
                     sc.configureBlocking(false);
                     //将新接入的客户端连接注册到多路复用器上，监听读操作，多去客户端发送的网络消息
	                 sc.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){//多路复用器监听到有信心网络消息
					SocketChannel sc = (SocketChannel) key.channel();
					ByteBuffer b = ByteBuffer.allocate(200);
					int count = sc.read(b);
					StringBuffer sb = new StringBuffer();
					String str = new String(b.array());
					sb.append(str);
				
					System.out.println(sb);
					
					key.interestOps(SelectionKey.OP_WRITE);
				}else if(key.isWritable()){//发送异步消息给客户端
					System.out.println("服务端写信息");
					String path = TestServerSocketChannel.class.getClassLoader().getResource("").getPath()+"nio/nio-data.txt";
					RandomAccessFile file = new RandomAccessFile(new File(path), "rw");
					FileChannel fileChannel = file.getChannel();
					SocketChannel sc = (SocketChannel) key.channel();
					fileChannel.transferTo(0, fileChannel.size(), sc);
					key.cancel();
				}
			}
		}
		
	}
	
}	
