package redis.cluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import properties.ReadPropertiesUtil;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestJedisCluster {

	public static void main(String[] args) throws IOException {
		Set<HostAndPort> set = new HashSet<HostAndPort>();
		String filepath = TestJedisCluster.class.getClassLoader().getResource("").getPath()+"redis-cluster.properties";
		Map<String,String> map = ReadPropertiesUtil.readByProperties(filepath);
		String host = map.get("ip");
		for(Entry<String,String> entry:map.entrySet()){
			if(entry.getKey().startsWith("address")){
				HostAndPort hap = new HostAndPort(host, Integer.valueOf(entry.getValue()));
				set.add(hap);
			}
		}
		String path = TestJedisCluster.class.getClassLoader().getResource("").getPath()+"redis.properties";
		Map<String,String> m = ReadPropertiesUtil.readByProperties(path);
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(Integer.valueOf(m.get("redis.maxIdle")));
		poolConfig.setMaxTotal(Integer.valueOf(m.get("redis.maxActive")));
		poolConfig.setMaxWaitMillis(Integer.valueOf(m.get("redis.maxWaitMillis")));
		
		JedisCluster jedisCluster = new JedisCluster(set, Integer.valueOf(m.get("redis.maxWaitMillis")), poolConfig);
		System.out.println(jedisCluster.get("name"));
		jedisCluster.set("client", "test jedis cluster");
		jedisCluster.set("jedisCluster", "this is cluster test");
		jedisCluster.close();
		
	}

}
