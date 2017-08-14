package netty.basic;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class ClientChannelFutureListener implements ChannelFutureListener {
	private Bootstrap bs;
	private String host;
	private int port;
	public ClientChannelFutureListener() {}

	public ClientChannelFutureListener(Bootstrap bs,String host,int port) {
		this.bs = bs;
		this.host = host;
		this.port = port;
	}
	@Override
	public void operationComplete(ChannelFuture cf) throws Exception {
		if(cf.isSuccess()){
			System.out.println("连接成功");
		}else{
			System.out.println("连接失败正在重连------------");
			cf.channel().eventLoop().schedule(new Runnable(){
				@Override
				public void run(){
					ChannelFuture cf = bs.connect(host, port);
					cf.addListener(new ClientChannelFutureListener(bs, host, port));
					try {
						cf.sync();
						cf.channel().closeFuture().sync();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, 2, TimeUnit.SECONDS);
			
		}
	}

}
