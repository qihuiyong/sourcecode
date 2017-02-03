package org.qhy.demo.redis.incr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class IncrTools {
	@Autowired
	private JedisPool jedisPool;
	
	public Long genId(String key) {
		Long result = null;
		Jedis jedis = null;
		try {
		    jedis = jedisPool.getResource();
		    result = jedis.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				jedisPool.returnResourceObject(jedis);
			}
		}
		System.out.println("key["+key+"] incr===>"+result);
		return result;
	}

}

