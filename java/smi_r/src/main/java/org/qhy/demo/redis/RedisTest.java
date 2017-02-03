package org.qhy.demo.redis;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Repository
public class RedisTest implements IRedisTest {

	@Autowired
	private JedisPool jedisPool;
	public String getFoo() {
		String result = "";
		Jedis jedis = null;
		try {
		    jedis = jedisPool.getResource();
			result = jedis.get("foo");
			if(StringUtils.isEmpty(result)){
				jedis.set("foo", "中文乱不论是的是的");
				result = jedis.get("foo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				jedisPool.returnResourceObject(jedis);
			}
		}
		System.out.println("获取redis foo===>"+result);
		return result;
	}
	public void clearKeys() {
		Jedis jedis = null;
		try {
		    jedis = jedisPool.getResource();
		    Set<String> keys = jedis.keys("*");
		    if(CollectionUtils.isNotEmpty(keys)){
		    	for (String key : keys) {
		    		System.out.println("redis 删除key===>"+key);
					jedis.del(key);
				}
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				jedisPool.returnResourceObject(jedis);
			}
		}
		
	}

}

