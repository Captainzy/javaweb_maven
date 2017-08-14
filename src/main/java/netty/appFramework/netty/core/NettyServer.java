package netty.appFramework.netty.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
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
import netty.appFramework.common.AppContextSingle;
import netty.appFramework.netty.handler.NettyServerHandler;
import netty.appFramework.netty.proto.ProtoRequest;

public abstract class NettyServer {
	private static Logger logger = LoggerFactory.getLogger(NettyServer.class);
	
	private static EventLoopGroup bossGroup;
	private static EventLoopGroup workerGroup;
	private static ServerBootstrap bs;
	private static Map<String,Channel> sessionMap;
	static{
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();
		bs = new ServerBootstrap();
		sessionMap = new HashMap<String,Channel>();
	}
	public static void start(int port){
		//初始化spring容器和相关配置
		AppContextSingle.APPCONTEXT.getInstantce();
		//开始开启服务端
		bs.group(bossGroup,workerGroup);
		bs.channel(NioServerSocketChannel.class);
		bs.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				ChannelPipeline pipeline = sc.pipeline();
				pipeline.addLast(new ProtobufVarint32FrameDecoder());
				pipeline.addLast(new ProtobufDecoder(ProtoRequest.Request.getDefaultInstance()));
				pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
				pipeline.addLast(new ProtobufEncoder());
				pipeline.addLast(new IdleStateHandler(120, 5, 360, TimeUnit.SECONDS));
				pipeline.addLast(new NettyServerHandler());
			}
		});
		
		bs.option(ChannelOption.SO_BACKLOG,128);
		bs.childOption(ChannelOption.SO_KEEPALIVE, true);
		ChannelFuture cf = bs.bind(port);
		logger.info(" server started at port {}", port);
		try {
			cf.sync();
			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
 		}
	}
}
