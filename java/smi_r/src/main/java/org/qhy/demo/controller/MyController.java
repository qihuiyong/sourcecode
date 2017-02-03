package org.qhy.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.qhy.demo.redis.IRedisTest;
import org.qhy.demo.redis.incr.IncrTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myc")
public class MyController {
	
	@Autowired
	private IRedisTest redisTest;
	@Autowired
	private IncrTools incrTools;
	
	@ModelAttribute
	@RequestMapping("/helloWorld")
    public String helloWorld(Model model) throws Exception {
		String fooVal = redisTest.getFoo();
		Long id = incrTools.genId("table:user");
        model.addAttribute("message", fooVal);
        return "helloWorld";
    }
//	@ModelAttribute
	@RequestMapping("/clearRedis")
    public String clearRedis(HttpServletRequest request,HttpServletResponse response ) throws Exception {
		 redisTest.clearKeys();
		 request.setAttribute("message", "操作完成!");
        return "myc/helloWorld";
    }
	

}
