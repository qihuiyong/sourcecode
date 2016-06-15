package org.qhy.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.qhy.util.StringUtil;

public class MyServlet extends HttpServlet {
	private Map<String, String> sessionMap = new TreeMap<String, String>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("username", "QIHUIYONG! "+StringUtil.getHelloworld());
		
		
		//登录
		if("login".equals(req.getParameter("opType"))){
			Integer sessionId = UUID.randomUUID().hashCode();
			resp.addCookie(new Cookie("sessionName", sessionId+""));
			sessionMap.put(sessionId+"","已登录");
		}
		Cookie[] cookies = req.getCookies();
		Cookie sessionCookie = null;
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("sessionName")){
					sessionCookie= cookie;
					break;
				}
			}
		}
		if(sessionCookie!=null){
			System.out.println(sessionMap.get(sessionCookie.getValue()));
		}
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
