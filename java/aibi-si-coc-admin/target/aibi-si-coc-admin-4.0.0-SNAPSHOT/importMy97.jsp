
<%
  String my97DatePicker = "";
  StringBuffer url = new  StringBuffer();
  url.append(request.getRequestURL());
  if(url.indexOf("localhost")>=0){
		my97DatePicker = "http://localhost:"+request.getLocalPort()+request.getContextPath()+"/jscript/My97DatePicker/";
  }else{
	  my97DatePicker = "http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/jscript/My97DatePicker/";	    
  }
%>
<script>
var my97DatePickerPath_asiainfo = '<%=my97DatePicker%>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscript/My97DatePicker/WdatePicker.js"></script>