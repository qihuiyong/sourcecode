package org.qhy.demo.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import org.qhy.demo.hibernate.dao.UserDao;
import org.qhy.demo.hibernate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;
	public void update() throws Exception{
		User user1 = new User();
		user1.setUsername("test1");
		user1.setPassword("tes");
		userDao.save(user1);
		User user2 = new User();
		user2.setUsername("qqq");
		user2.setPassword("息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:信息:");
		userDao.save(user2);
		if(true){
			throw new Exception();
		}
	}
	public static void main(String[] args) {
		List list=new ArrayList();
		change(list);
		System.out.println(list.size());
	}
	public static void change(List list){
		list.add("1");
	}
}
