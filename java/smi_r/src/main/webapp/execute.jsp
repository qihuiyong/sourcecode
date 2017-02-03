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
.form{background: rgba(255,255,255,0.2);width:800px;margin:100px auto;}
</style>
<title>执行命令的群</title>
</head>
<body>
	<div class="container">
		<div class="form row">  
               <h3 class="form-title">执行相关json操作</h3>
               <div class="form-group">
                   	选择操作：
                   	<select class="form-control" id="optSelect" style="width: 30%">
	                   	<option value="nil">请选择操作</option>
	                   	<option value="userRegister">注册用户</option>
                   	</select> 
               </div>  
               <div class="form-group">  
                    <textarea id="submitJsonData" class="form-control" rows="8" cols="30"></textarea>
               </div>  
               <div class="form-group">  
                   <input type="button" id="btnExecute" class="btn btn-success pull-right" value="执行 "/>     
               </div>  
        </div> 
    </div>
<!-- jQuery and bootstrap-->
	<script src="${contextPath}/jquery/jquery.min.js"></script>
	<script src="${contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		var action="nil";
		var template="{}";
		var baseAction = "${contextPath}/controller/";
		$(document).ready(function(){
			$("#optSelect").change(function(){
				var selectVal = $(this).val();
				if("userRegister" == selectVal){
					action =baseAction+"user/createUser";
					template = "{\"loginName\":\"uname\",\"userPwd\":\"pwd\",\"nickname\":\"nick\"}";
				}else{
					action="nil";
					template="{}";
				}
				$("#submitJsonData").val(template);
				
			});
			$("#btnExecute").click(function(){
				var jsonData = $("#submitJsonData").val();
				alert("action>>>:"+action);
				alert("jsonData>>>:"+jsonData);
				jsonData = eval("("+jsonData+")");
				alert("jsonData-Object>>>:"+jsonData);
				$.ajax({
		             type: "POST",
		             url: action,
		             data: jsonData,
		             dataType: "json",
		             success: function(data){
		            	 alert(data);
		            }
		        });
			});
			
		});
	</script>
</body>
</html>