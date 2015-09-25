<%
	String country="_"+request.getLocale().getCountry();
	if(country.trim().length()==1 || request.getLocale().getCountry()==null){
		country="";
	}
%>
<script language="javaScript"
	src="<%=request.getContextPath()%>/aibi_<%=request.getParameter("componentCode")%>/js/locale/<%=request.getParameter("componentCode")%>_messageBundle_<%=request.getLocale().getLanguage()+country%>.js"></script>