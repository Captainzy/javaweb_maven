package netty.basic;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import netty.appFramework.netty.proto.ProtoRequest;

public class DiscardServer {
	private static int port;
	public static void main(String[] args) {
		new DiscardServer(8899).run();
	}

	public DiscardServer(){}
	public DiscardServer(int port){
		this.port = port;
	}
	public static void run(){
		/**
		 * 在Netty的里面有一个Boss，他开了一家公司（开启一个服务端口）对外提供业务服务，
		 * 它手下有一群做事情的workers。Boss一直对外宣传自己公司提供的业务，并且接受(accept)有需要的客户(client)，
		 * 当一位客户找到Boss说需要他公司提供的业务，Boss便会为这位客户安排一个worker，这个worker全程为这位客户服务（read/write）。
		 * 如果公司业务繁忙，一个worker可能会为多个客户进行服务。这就是Netty里面Boss和worker之间的关系
		 */
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		/**ServerBootstrap is a helper class that sets up a server.*/
		ServerBootstrap bs = new ServerBootstrap();
		bs.group(bossGroup, workerGroup);
		bs.channel(NioServerSocketChannel.class);
		//bs.childHandler(new DiscardServerHandler());
		/**调用在用户具体定义的initChannel函数来对当前的channel进行一些初始化的操作*/
		bs.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				
				//传递数据为对象
//				sc.pipeline().addLast(new ObjectDecoder(1024*1024,ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
//				sc.pipeline().addLast(new ObjectEncoder());
				
				
				//使用protobuf传送数据
				ChannelPipeline pipeline = sc.pipeline();
				//ProtobufEncoder仅仅负责解码，不支持读半包，因此在ProtobufDecoder之前还需要添加处理读半包的解码器，有三种方式可选：
				//1.使用netty提供的ProtobufVarint32FrameDecoder，2.使用netty提供的LengthFieldBasedFrameDecoder，3.继承ByteToMessageDecoder,自己处理半包问题
				//pipeline.addLast(new LengthFieldBasedFrameDecoder(1024*1024,0,3,0,3));
				pipeline.addLast(new ProtobufVarint32FrameDecoder());
				//添加解码器
				pipeline.addLast(new ProtobufDecoder(ProtoRequest.Request.getDefaultInstance()));
				//添加编码器
				pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
				pipeline.addLast(new ProtobufEncoder());
				//心跳控制，心跳控制必须放在定义的处理handler之前
				pipeline.addLast(new IdleStateHandler(120,120,360,TimeUnit.SECONDS));
				//处理类
				pipeline.addLast(new DiscardServerHandler());
				
			}
		});
		/**option()的配置是提供给bossGroup的，而childOption()的配置是提供给workerGroup的*/
		bs.option(ChannelOption.SO_BACKLOG,128);
		bs.childOption(ChannelOption.SO_KEEPALIVE, true);
		
		//绑定端口
		ChannelFuture cf = bs.bind(port);
		try {
			//同步等待成功
			cf.sync();
			//等待服务端监听端口关闭
			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
			
		}
		
		
	}
}
