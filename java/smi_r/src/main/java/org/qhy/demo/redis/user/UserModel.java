package org.qhy.demo.redis.user;

import java.util.HashMap;
import java.util.Map;


public class UserModel {
	private String id;
	private String loginName;
	private String userPwd;
	private String nickname;
	
	public UserModel() {
	}
	public UserModel(String loginName,String userPwd,String nickName) {
		this.loginName = loginName;
		this.userPwd = userPwd;
		this.nickname = nickName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Map<String, String> toMap(){
		Map<String, String> result = new HashMap<String, String>();
		result.put("id", this.getId());
		result.put("loginName", this.getLoginName());
		result.put("userPwd", this.getUserPwd());
		result.put("nickname", this.getNickname());
		return result;
	}

}

