package netty.basic;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import netty.appFramework.netty.proto.ProtoRequest;

public class DiscardClient {

	private static int port;
	private static String host;
	public static void main(String[] args) {
		new DiscardClient("localhost",8899).run();
	}

	public DiscardClient(){}
	public DiscardClient(String host,int port){
		this.host = host;
		this.port = port;
	}
	public static void run(){
	
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		final Bootstrap bs = new Bootstrap();
		bs.group(workerGroup);
		bs.channel(NioSocketChannel.class);
		//bs.childHandler(new DiscardServerHandler());
		bs.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				//传递数据为对象
//				sc.pipeline().addLast(new ObjectDecoder(1024*1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
//				sc.pipeline().addLast(new ObjectEncoder());
				
				//使用protobuf传送数据
				ChannelPipeline pipeline = sc.pipeline();
				//添加解码器
				pipeline.addLast(new ProtobufVarint32FrameDecoder());
				pipeline.addLast(new ProtobufDecoder(ProtoRequest.Request.getDefaultInstance()));
				//添加编码器
				pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
				pipeline.addLast(new ProtobufEncoder());
				//心跳控制，心跳控制必须放在定义的处理handler之前
				pipeline.addLast(new IdleStateHandler(2,2,5,TimeUnit.SECONDS));
				//处理类
				pipeline.addLast(new DiscardClientHandler(bs,host,port));
			}
		});
		bs.option(ChannelOption.SO_KEEPALIVE, true);
		//下面这种连接服务端的方式，就算连接失败也会继续添加监听器，这样就实现了，连接失败重连的效果，
		ChannelFuture cf = cf = bs.connect(host, port);
		cf.addListener(new ClientChannelFutureListener(bs, host, port));
	}
}
