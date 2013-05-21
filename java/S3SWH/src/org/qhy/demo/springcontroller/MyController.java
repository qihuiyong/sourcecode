package org.qhy.demo.springcontroller;

import java.util.List;

import org.qhy.demo.hibernate.dao.UserDao;
import org.qhy.demo.hibernate.entity.User;
import org.qhy.demo.hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myc")
public class MyController {
//	@Autowired
//	private UserDao userDao;
	@Autowired
	private UserService userService;
	@ModelAttribute
	@RequestMapping("/helloWorld")
    public String helloWorld(Model model) throws Exception {
        model.addAttribute("message", "Hello World!");
//        List<User> list = userDao.getUserList();
        userService.update();
        return "helloWorld";
    }

}
