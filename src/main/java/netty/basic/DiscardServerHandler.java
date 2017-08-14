package netty.basic;


import static netty.basic.AttributeKeyConstant.KEY_USERNAME;

import com.google.protobuf.MessageOrBuilder;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object e) throws Exception {
		if(e instanceof IdleStateEvent) {
			switch(((IdleStateEvent)e).state()){
			case READER_IDLE:
				System.out.println("----------------很久没有收到客户端消息了，断开连接------------------");
				ctx.close();
				break;
			case WRITER_IDLE:
				System.out.println("-----------------写超时，断开连接----------------------");
				ctx.close();
				break;
			case ALL_IDLE:
				break;
			}
		}
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
	//	
//		String str = "服务端说：你好，我已经收到消息了";
//		byte[] strb = str.getBytes();
//		ByteBuf bbf = Unpooled.buffer(strb.length);
//		bbf.writeBytes(strb);
//		ctx.writeAndFlush(bbf);
		
		/**普通对象*/
//		People p = (People) msg;
//		System.out.println("客户端对象："+p.getName()+"   "+p.getAge());
//		ReferenceCountUtil.release(msg);
	//	
//		People sp = new People();
//		sp.setName("服务端");
//		sp.setAge(132);
//		ctx.writeAndFlush(sp);
		
//		/**google  protobuf*/
//		Auth.AuthRequest res = (AuthRequest) msg;
//		System.out.println("userId = "+res.getUserId()+"\npassword="+res.getPassword());
	//	
//		Auth.AuthResponse.Builder builder = Auth.AuthResponse.newBuilder();
//		builder.setResultCode(123123);
//		builder.setResultMessage("你好，客户端，我是服务端");
//		ctx.writeAndFlush(builder.build());
//		System.out.println("收到消息");
//		System.out.println(endpoint.getLogin());
//		
//		Channel channel = ctx.channel();
//		/**这里需要注意attr(key),其中key要声明为final避免出现名字重复的异常,经过测试，每个channel都是单独的个体,就像不同的session*/
//		channel.attr(KEY_USERNAME).set("zouyang");//类似于session.setAttribute()
//		channel.attr(KEY_USERNAME).get();//类似于session.getAttribute()
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("发生异常，作出反应");
		System.out.println(cause.getMessage());
		ctx.close();
	}
}
	
