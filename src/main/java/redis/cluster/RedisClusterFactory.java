package redis.cluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisClusterFactory implements FactoryBean<JedisCluster>,InitializingBean{

	
	private JedisCluster jedisCluster;
	private String addressPrefix;
	private Resource jedisClusterConfig;
	private GenericObjectPoolConfig jedisPoolConfig;
	
	@Override
	public JedisCluster getObject() throws Exception {
		return jedisCluster;
	}

	@Override
	public Class<? extends JedisCluster> getObjectType() {
		return this.jedisCluster!=null?this.jedisCluster.getClass():JedisCluster.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	private Set<HostAndPort> getHostAndPort(){
		Set<HostAndPort> set = new HashSet<HostAndPort>();
		Properties p = new Properties();

		try {
			p.load(jedisClusterConfig.getInputStream());
			
			String host = p.getProperty("ip");
			for(Object key:p.keySet()){
				String value = p.getProperty(key.toString());
				if(value.startsWith(addressPrefix)){
					HostAndPort hap = new HostAndPort(host, Integer.valueOf(value));
					set.add(hap);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Set<HostAndPort> set = this.getHostAndPort();
		jedisCluster = new JedisCluster(set, jedisPoolConfig);
	}

	public JedisCluster getJedisCluster(){
		return this.jedisCluster;
	}
	
	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}
	
	public void setAddressPrefix(String addressPrefix) {
		this.addressPrefix = addressPrefix;
	}

	public void setJedisClusterConfig(Resource jedisClusterConfig) {
		this.jedisClusterConfig = jedisClusterConfig;
	}

	public void setJedisPoolConfig(GenericObjectPoolConfig jedisPoolConfig) {
		this.jedisPoolConfig = jedisPoolConfig;
	}

}
