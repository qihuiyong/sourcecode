package com.asiainfo.biapp.si.cocadmin.mapper;

import com.asiainfo.biapp.si.cocadmin.model.DemoUser;

public interface UserMapper extends SuperMapper{

	public DemoUser selectUser(String name);
	
	public void insertUser(DemoUser user);
	
}
