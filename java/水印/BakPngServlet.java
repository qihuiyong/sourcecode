package com.ai.cq12582.system;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class BakPngServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException { 
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException { 

		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ByIcon.markImage("2015-12-12 hello", response.getOutputStream(), -30,400,300);
	}
	
}
