<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" scope="session" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- Bootstrap -->
   <link href="${contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <link href="${contextPath}/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"/>
   
</head>
<body>
	<div class="container">
		<div class="page-header">
		   <h1>天涯的论坛
		      <small>欢迎，<c:out value="aasssssss" /> ${servletPath}!</small>
		   </h1>
		</div>
		<nav class="navbar navbar-inverse">
            <div class="navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">我的主页</a></li>
                    <li><a href="#">我的发帖</a></li>
                </ul>
            </div>
        </nav>
        <div id="content" class="row-fluid">
	        <div class="col-md-9">
	            <h2>我的发大幅度</h2>
	            <!-- Secondary, outline button -->
				<button type="button" class="btn btn-primary">Primary</button><br />
				<button type="button" class="btn btn-success">Success</button><br />
				<button type="button" class="btn btn-info">Info</button><br />
				<button type="button" class="btn btn-warning">Warning</button><br />
				<button type="button" class="btn btn-danger">Danger</button><br />
				<br />
	            JSP Request Method: <%= request.getMethod()%><br />
				Request URI: <%= request.getRequestURI()%><br />
				Request Protocol: <%= request.getProtocol()%><br />
				Servlet path: <%= request.getServletPath()%><br />
				Path info: <%= request.getPathInfo()%><br />
				Query string: <%= request.getQueryString()%><br />
				Content length: <%= request.getContentLength()%><br />
				Content type: <%= request.getContentType()%><br />
				Server name: <%= request.getServerName()%><br />
				Server port: <%= request.getServerPort()%><br />
				Remote user: <%= request.getRemoteUser()%><br />
				Remote address: <%= request.getRemoteAddr()%><br />
				Remote host: <%= request.getRemoteHost()%><br />
				Authorization scheme: <%= request.getAuthType()%> <br />
				Locale: <%= request.getLocale()%>
	        </div>
	        <div class="col-md-3">
	            <h2>侧边</h2>  <br />
	            <a href="//www.baidu.com">百度</a><br />
	            <a href="//www.baidu.com">百度</a><br />
	            <a href="//www.baidu.com">百度</a><br />
	            <a href="//www.baidu.com">百度</a><br />
	            <a href="//www.baidu.com">百度</a><br />
	            <a href="//www.baidu.com">百度</a><br />
	            <a href="//www.baidu.com">百度</a><br />
	            <a href="//www.baidu.com">百度</a><br />
	        </div>
   		</div>
    </div>
	<!-- jQuery and bootstrap-->
	<script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<body>
</body>
</html>