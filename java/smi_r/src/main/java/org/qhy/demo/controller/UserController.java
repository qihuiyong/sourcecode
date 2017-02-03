package org.qhy.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.qhy.demo.redis.user.IUserDao;
import org.qhy.demo.redis.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserDao  userDao;	
	
	@RequestMapping("/createUser")
	@ResponseBody
    public Object createUser(HttpServletRequest request) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		String loginName = request.getParameter("loginName");
		String userPwd = request.getParameter("userPwd");
		String nickname = request.getParameter("nickname");
		UserModel model = new UserModel(loginName, userPwd, nickname);
		userDao.addUser(model);
		result.put("success", "Y");
		result.put("msg", "注册成功");
        return result;
    }
	
	@RequestMapping("/queryUser")
	@ResponseBody
    public Object queryUser(HttpServletRequest request) throws Exception {
		List<UserModel> list = userDao.getUser(null);
        return list;
    }

}
