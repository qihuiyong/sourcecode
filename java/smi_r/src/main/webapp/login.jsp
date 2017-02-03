<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" scope="session" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"/>
<style type="text/css">
.form{background: rgba(255,255,255,0.2);width:500px;margin:100px auto;}
</style>
<title>登录</title>
</head>
<body>
	<div class="container">
		<div class="form row">  
            <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="login_form">  
                <h3 class="form-title">登录论坛</h3>  
                <div class="col-sm-9 col-md-9">  
                    <div class="form-group">  
                       	登录名：<input class="form-control required" type="text" name="username" autofocus="autofocus" maxlength="20"/>  
                    </div>  
                    <div class="form-group">  
                                                密码：<input class="form-control required" type="password" name="password" maxlength="8"/>  
                    </div>  
                    <div class="form-group">  
                        <a href="${contextPath}/execute.jsp" id="register_btn" class="" target="_blank" >注册一个用户名</a>  
                    </div>  
                    <div class="form-group">  
                        <input type="submit" class="btn btn-success pull-right" value="Login "/>     
                    </div>  
                </div>  
            </form>  
        </div> 
    </div>
<!-- jQuery and bootstrap-->
	<script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>