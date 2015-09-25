<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.logging.Log" %>
<%@ page import="org.apache.commons.logging.LogFactory" %>
<%@ page import="com.asiainfo.biframe.action.BaseAction" %>
<% 
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
Log log = LogFactory.getLog(BaseAction.class);

String errStack = "";
if ( request.getAttribute("exceptionStack")!=null ) {
	errStack = (String)request.getAttribute("exceptionStack");
	log.error( errStack );
	errStack = errStack.replaceAll("at ","<br>at ");
}

String errMessage = "";
if ( request.getAttribute("exception")!=null ) {
	Exception err = (Exception)request.getAttribute("exception");
	errMessage = err.getMessage();
	errMessage = errMessage.replaceAll("\"","'");
	errMessage = errMessage.replaceAll("\n"," ");
	log.error( errMessage );
}
%>

<html>
	<head>
		<title>错误页面</title>

<script type="text/javascript" language="javascript">
if ( window.parent!=null && window.parent.frames!=null ) {
	for ( i=0;i<window.parent.frames.length;i++ ) {
		if ( window.parent.frames[i].name=='ifSubmit' && typeof(window.parent.resultSubmit)!="undefined" ) {
			window.parent.resultSubmit("error","<%=errMessage%>");
			//alert("<%=errMessage%>");
		}
	}
}
</script>

	</head>
	<body>
	  <div>
		<H2>
			${exception.message}
			
		</H2>
	  </div>
	  <%=errStack%>
	</body>
</html>
