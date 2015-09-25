package com.asiainfo.biapp.si.cocadmin.mapper;

import com.asiainfo.biapp.si.cocadmin.model.DemoUser2;

public interface UserMapper2 extends SuperMapper{

	public DemoUser2 selectUser(String name);
	
	public void insertUser(DemoUser2 user);
	
}
