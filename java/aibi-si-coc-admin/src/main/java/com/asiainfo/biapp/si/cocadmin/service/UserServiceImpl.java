package com.asiainfo.biapp.si.cocadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.biapp.si.cocadmin.mapper.UserMapper;
import com.asiainfo.biapp.si.cocadmin.mapper.UserMapper2;
import com.asiainfo.biapp.si.cocadmin.model.DemoUser;
import com.asiainfo.biapp.si.cocadmin.model.DemoUser2;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserMapper2 userMapper2;
	
	@Override
	public void findByName(String name) {
		try {
			DemoUser user = userMapper.selectUser(name);
			System.out.println(user.getUname());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	@Transactional
	public void testTx() throws Exception {
		DemoUser user = new DemoUser();
		user.setUname("____");
		user.setUsex(66666);
		userMapper.insertUser(user);
		DemoUser2 user2 = new DemoUser2();
		user2.setUname("22222222222222");
		user2.setUsex(5555);
		userMapper2.insertUser(user2);
	}
	

}
