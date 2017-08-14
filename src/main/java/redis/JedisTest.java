package redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import properties.ReadPropertiesUtil;
import properties.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisTest {
	public static void main(String[] args){
		String host = "127.0.0.1";
		int port = 6379;
		Jedis jedis = new Jedis(host, port);
		System.out.println(jedis.ping());
		System.out.println(jedis.get("username"));
		jedis.set("username", "zhang3");
		jedis.lpush("user", "zhang3");
		jedis.lpush("user", "li4");
		jedis.lpushx("user", "wang5");
		List<String> userList = jedis.lrange("user", 0, 100);
		System.out.println("userlist -->"+userList);
		
		jedis.sadd("user2", "zhang3");
		jedis.sadd("user2","wang5");
		jedis.sadd("user2", "li4");
		Set<String> userList2 = jedis.smembers("user2");
		System.out.println("userList2 -->"+userList2);
		jedis.rpush("tk", "hahatk");
		jedis.lpop("tk");
		
		jedis.lset("user", 0, "nothing");
		String filepath = Test.class.getClassLoader().getResource("").getPath()+"redis.properties";
		Map<String,String> map = ReadPropertiesUtil.readByProperties(filepath);
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(Integer.valueOf(map.get("redis.maxIdle")));
		config.setMaxWaitMillis(Integer.valueOf(map.get("redis.maxWaitMillis")));
		config.setMaxTotal(Integer.valueOf(map.get("redis.maxActive")));
		config.setTestOnBorrow(Boolean.valueOf(map.get("redis.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(map.get("redis.testOnReturn")));
		
		JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379,Integer.valueOf(map.get("redis.timeout")));
		Jedis j = jedisPool.getResource();
		j.set("test", "this is test of pool");
		
		//关闭对象，获取jedis实例使用后要将对象还回去
		j.close();
		
		JedisShardInfo info = new JedisShardInfo("127.0.0.1", 6379);
		JedisShardInfo info2 = new JedisShardInfo("127.0.0.2",6379);
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(info);
		shards.add(info2);
		ShardedJedisPool sjp = new ShardedJedisPool(config, shards);
		ShardedJedis shardedJedis = sjp.getResource();
		shardedJedis.set("shardedJedis", "shardedJedis test");
		shardedJedis.close();
	}
}
