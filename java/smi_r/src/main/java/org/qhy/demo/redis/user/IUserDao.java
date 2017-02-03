package org.qhy.demo.redis.user;

import java.util.List;

public interface IUserDao {
	public Long addUser(UserModel model);
	public void updateNickname(String nickname);
	public List<UserModel> getUser(String loginName);
}

