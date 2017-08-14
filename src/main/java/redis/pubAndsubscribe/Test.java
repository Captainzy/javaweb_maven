package redis.pubAndsubscribe;

import java.util.HashMap;
import java.util.Map;

import properties.ReadPropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

/**
 * @author zouyang
 * @time 2016年10月11日 上午10:48:13
 * @description redis的发布订阅测试
 */
public class Test {
	public static void main(String[] args){
		
		JedisPubSub jps = new JedisPubSub() {
			//取得订阅消息后处理
			@Override
			public void onMessage(String channel, String message) {
				System.out.println("接收到订阅信息:"+message);
			}
			@Override
			public void onPMessage(String pattern, String channel, String message) {
				// TODO Auto-generated method stub
				super.onPMessage(pattern, channel, message);
			}
			//按表达式订阅后处理
			@Override
			public void onPSubscribe(String pattern, int subscribedChannels) {
				// TODO Auto-generated method stub
				super.onPSubscribe(pattern, subscribedChannels);
			}
			//安表达式取消订阅候处理
			@Override
			public void onPUnsubscribe(String pattern, int subscribedChannels) {
				// TODO Auto-generated method stub
				super.onPUnsubscribe(pattern, subscribedChannels);
			}
			//订阅后处理
			@Override
			public void onSubscribe(String channel, int subscribedChannels) {
				// TODO Auto-generated method stub
				super.onSubscribe(channel, subscribedChannels);
			}
			
		};

	}
}
