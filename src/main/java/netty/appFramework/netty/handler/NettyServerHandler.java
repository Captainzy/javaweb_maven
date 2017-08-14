package netty.appFramework.netty.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import netty.appFramework.action.user.UserAction;
import netty.appFramework.common.AppContext;
import netty.appFramework.common.AppContextSingle;
import netty.appFramework.model.APIResult;
import netty.appFramework.netty.proto.ProtoRequest;
import netty.appFramework.netty.proto.ProtoRequest.Request;
import netty.appFramework.netty.proto.ProtoResponse;

public class NettyServerHandler extends ChannelInboundHandlerAdapter{
	private Logger log = LoggerFactory.getLogger(getClass());
	private static AppContext appContext = AppContextSingle.APPCONTEXT.getInstantce();

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// 断开连接时要将客户端的登录信息清除
		log.info("channelInactive");
		appContext.getSessionMap().remove(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		if (msg instanceof ProtoRequest.Request) {
			ProtoRequest.Request request = (Request) msg;
			//除登录请求外，都要进行登录校验
			if(!request.getCommandCase().equals(request.getCommandCase().USERLOGIN)){
				if(checkUserLogin(ctx)){
					return;
				}
			}
			switch (request.getCommandCase()) {
			case USERLOGIN:
				userLogin(ctx, request);
				break;
			case USERLOGOUT:
				userLogOut(ctx, request);
				break;
			default:
				break;
			}
		} else {
			ctx.writeAndFlush(new String("参数不符合要求!!!"));
		}
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.info("exceptionCaught");
		//发生异常时，要将客户端的登录信息清除
		cause.printStackTrace();
		
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object e) throws Exception {
		if(e instanceof IdleStateEvent) {
			switch(((IdleStateEvent)e).state()){
			case READER_IDLE:
				System.out.println("----------------很久没有收到客户端消息了,断开连接------------------");
				ctx.close();
				break;
			case WRITER_IDLE:
				System.out.println("-----------------呼叫客户端保持连接状态----------------------");
				ProtoResponse.Response Response = ProtoResponse.Response.newBuilder().build();
				ctx.writeAndFlush(Response);
				break;
			case ALL_IDLE:
				break;
			}
		}
	}
	
	public static boolean checkUserLogin(ChannelHandlerContext ctx){
		if(appContext.getSessionMap().get(ctx)==null){
			APIResult<?> result = new APIResult<>();
			result.setCode(-1);
			result.setMsg("用户未登录，请登录后进行操作！！！");
			ProtoResponse.Response.Builder response = ProtoResponse.Response.newBuilder();
			ProtoResponse.Result.Builder userLoginResult = ProtoResponse.Result.newBuilder();
			userLoginResult.setResult(JSON.toJSONString(result));
			response.setResult(userLoginResult);
			ctx.writeAndFlush(result);
			return true;
		}
		return false;
	}
	
	public static void userLogin(ChannelHandlerContext ctx, Request request) {
		APIResult<?> result = new APIResult<>();
		UserAction userAction = appContext.getApplicationContext().getBean(UserAction.class);
		Map<String, String> reqMap = JSON.parseObject(request.getUserLogin().getReqMap(), Map.class);
		result = userAction.userLogin(ctx,reqMap);
		ProtoResponse.Response.Builder response = ProtoResponse.Response.newBuilder();
		ProtoResponse.UserLogin.Builder userLogin = ProtoResponse.UserLogin.newBuilder();
		userLogin.setResult(JSON.toJSONString(result));
		response.setUserLogin(userLogin);
		ctx.writeAndFlush(response);
	}

	public static void userLogOut(ChannelHandlerContext ctx, Request request) {
		APIResult<?> result = new APIResult<>();
		UserAction userAction = appContext.getApplicationContext().getBean(UserAction.class);
		result = userAction.userLogOut(ctx);
		ProtoResponse.Response.Builder response = ProtoResponse.Response.newBuilder();
		ProtoResponse.UserLogOut.Builder userLogOut = ProtoResponse.UserLogOut.newBuilder();
		userLogOut.setResult(JSON.toJSONString(result));
		response.setUserLogOut(userLogOut);
		ctx.writeAndFlush(response);
	}
}
