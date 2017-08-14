package netty.basic;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import netty.appFramework.netty.proto.ProtoRequest;
import netty.basic.Auth.AuthResponse;

public class DiscardClientHandler extends ChannelInboundHandlerAdapter {
	private  Bootstrap bs;
	private  String host;
	private  int port;
	
	public DiscardClientHandler() {}
	public DiscardClientHandler(Bootstrap bs, String host, int port) {
		super();
		this.bs = bs;
		this.host = host;
		this.port = port;
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端与服务器连接断开，正在重连-------------");
		ctx.channel().eventLoop().schedule(new Runnable(){
			@Override
			public void run(){
				ChannelFuture cf = bs.connect(host, port);
				cf.addListener(new ClientChannelFutureListener(bs, host, port));
			}
		}, 2, TimeUnit.SECONDS);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object e) throws Exception {
		if (e instanceof IdleStateEvent){
			switch(((IdleStateEvent)e).state()){
			case READER_IDLE:
				System.out.println("客户端读超时，很久没有收到服务端消息了--------");
				break;
			case WRITER_IDLE:
				System.out.println("客户端写超时，发送心跳消息");
				ProtoRequest.Request request = ProtoRequest.Request.newBuilder().build();
				ctx.writeAndFlush(request);
				break;
			case ALL_IDLE:
				break;
			}
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		/**原始ByteBuf*/
//		String msg = "客户端说：你好";
//		byte[] b = msg.getBytes();
//		ByteBuf bf = Unpooled.buffer(b.length);
//		bf.writeBytes(b);
//		ctx.writeAndFlush(bf);
//		
		/**普通对象*/
//		People sp = new People();
//		sp.setName("客户端");
//		sp.setAge(11);
//		ctx.writeAndFlush(sp);
		
		/**google protobuf*/
//		Auth.AuthRequest.Builder builder = Auth.AuthRequest.newBuilder();
//		builder.setUserId("zouyang");
//		builder.setPassword("111111");
//		ctx.writeAndFlush(builder.build());
		
		/*TestProto.Company.Builder cBuilder = TestProto.Company.newBuilder();
		cBuilder.setName("12313");
		cBuilder.setGuimo("bigbige");
		TestProto.Pig.Builder pBuilder = TestProto.Pig.newBuilder();
		pBuilder.setWeight("1222");
		cBuilder.setPig(pBuilder.build());
		TestProto.Company c = cBuilder.build();
		c.getRankCase();
		TestProto.Animal.Builder aBuilder = TestProto.Animal.newBuilder();
		TestProto.Dog.Builder dBuilder = TestProto.Dog.newBuilder();
		dBuilder.setName("123");
		dBuilder.setType("dog1");
		aBuilder.addDog(dBuilder.build());
		TestProto.Dog.Builder dBuilder2 = TestProto.Dog.newBuilder();
		dBuilder2.setName("456");
		dBuilder2.setType("dog2");
		aBuilder.addDog(dBuilder2.build());
		TestProto.Animal animal = aBuilder.build();
		
		Proto.Pc.Endpoint.Builder epBuilder = Proto.Pc.Endpoint.newBuilder();
		
		Proto.Pc.Config.Builder cfBuilder = Proto.Pc.Config.newBuilder();
		cfBuilder.setEndpointId("1");
		epBuilder.setConfig(cfBuilder.build());
		
		Proto.Pc.Login.Builder lgBuilder = Proto.Pc.Login.newBuilder();
		lgBuilder.setUsername("123132");
		lgBuilder.setPassword("111111");
		epBuilder.setLogin(lgBuilder.build());
		
		ctx.writeAndFlush(epBuilder.build());*/
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		/**原始ByteBuf*/
//		System.out.println("收到信息，作出反应");
//		ByteBuf bf = (ByteBuf) msg;
//		byte[] b = new byte[bf.readableBytes()];
//		bf.readBytes(b);
//		System.out.println(new String(b,"UTF-8"));
//		
//		ReferenceCountUtil.release(msg);
		
		/**普通对象*/
//		People p = (People)msg;
//		System.out.println("服务端的消息："+p.getName() + "   " + p.getAge());
//		ReferenceCountUtil.release(msg);
		
		/**google protobuf*/
//		Auth.AuthResponse resq = (AuthResponse) msg;
//		System.out.println("result_code = "+resq.getResultCode()+"\nresult_message = "+resq.getResultMessage());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("发生异常，作出反应");
		System.out.println(cause.getMessage());
	}
}
