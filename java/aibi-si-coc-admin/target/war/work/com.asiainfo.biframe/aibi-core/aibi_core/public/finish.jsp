<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<% 
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String finishMessage = (String)request.getAttribute("finishMessage");
if ( finishMessage==null ) {
	finishMessage = "处理成功";
} else {
	//处理特殊字符
}
request.setAttribute("finishMessage",finishMessage);
%>

<html>
	<head>
		<title>成功页面</title>

<script type="text/javascript" language="javascript">
if ( window.parent!=null && window.parent.frames!=null ) {
	for ( i=0;i<window.parent.frames.length;i++ ) {
		if ( window.parent.frames[i].name=='ifSubmit' && typeof(window.parent.resultSubmit)!="undefined" ) {
			window.parent.resultSubmit("success","${finishMessage}");
			//alert("${finishMessage}");
		}
	}
}
</script>

	</head>
	<body>
	<input type='hidden' name='finish'/>
	  <div>
		<H2>
			${finishMessage}
		</H2>
	  </div>
	</body>
</html>
