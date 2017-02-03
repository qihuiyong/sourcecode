package org.qhy.demo.redis.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.qhy.demo.redis.incr.IncrTools;
import org.qhy.demo.redis.user.IUserDao;
import org.qhy.demo.redis.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Repository
public class UserDao implements IUserDao {
    private static final String USER_TABLE_PREFIX ="user:";
    private static final String USER_GENID_PREFIX ="userGenId";
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private IncrTools incrTools;
	public Long addUser(UserModel user) {
		Long userId=null;
		Jedis jedis = null;
		try {
		    userId = incrTools.genId(USER_GENID_PREFIX);
			user.setId(userId+"");
			String userKey = getUserKey(userId);
			jedis = jedisPool.getResource();
			jedis.hmset(userKey, user.toMap());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				jedisPool.returnResourceObject(jedis);
			}
		}
		return userId;
		
	}
	
	public List<UserModel> getUser(String loginName) {
		List<UserModel> result = new ArrayList<UserModel>();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Set<String> keys = jedis.keys(USER_TABLE_PREFIX+"*");
			if(CollectionUtils.isNotEmpty(keys)){
				for (String key : keys) {
					List<String> valList =  jedis.hmget(key, "id","loginName","userPwd","nickname");
					UserModel model = new UserModel(valList.get(1), valList.get(2),valList.get(3));
					model.setId(valList.get(0));
					result.add(model);
					System.out.println(model);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				jedisPool.returnResourceObject(jedis);
			}
		}
		return result;
	}
	
	private String getUserKey(Long id){
		//user:3
		return USER_TABLE_PREFIX+id;
	}
	public void updateNickname(String nickname) {
		
		// TODO Auto-generated method stub
		
	}

}

