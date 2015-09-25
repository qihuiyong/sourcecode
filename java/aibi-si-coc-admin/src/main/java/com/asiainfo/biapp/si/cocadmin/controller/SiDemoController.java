package com.asiainfo.biapp.si.cocadmin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.biapp.si.cocadmin.service.IUserService;

/**
 * 
 * @author qihy
 * 
 * @param <DimApproveResourceType>
 */
@Controller
@RequestMapping(value = "/demo")
public class SiDemoController {

	private Logger log = Logger.getLogger(SiDemoController.class);

	private static final String PACKAGE_PATH = "/aibi_si_cocadmin";
	
	@Autowired
	private IUserService userService;
	
	@SuppressWarnings("unchecked")
    @RequestMapping(value="/toIndex")
	public ModelAndView toIndex(HttpServletRequest request) throws Exception {
	    ModelAndView mav = new ModelAndView(PACKAGE_PATH+"/index");
//	    userService.findByName("1");
	    try {
	    	userService.testTx();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return mav;
	}

}