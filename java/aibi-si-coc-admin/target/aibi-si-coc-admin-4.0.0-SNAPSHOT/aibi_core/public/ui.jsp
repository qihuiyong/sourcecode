<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.asiainfo.biframe.common.SysCodes" %>
<%
String theme = SysCodes.getSysParameter("SYS_SHOW_THEME");
String currentTheme = (String)session.getAttribute("theme");
if(currentTheme == null){
	if(theme==null || theme.trim().length()==0){
		theme = "default";
		session.setAttribute("theme", "default");
	}else{
		session.setAttribute("theme", theme);
	}
}else{
	theme = currentTheme;
}

String country="_"+request.getLocale().getCountry();
if(country.trim().length()==1 || request.getLocale().getCountry()==null){
	country="";
}
String locale=request.getLocale().getLanguage()+country;
%>
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/accordion.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/article.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/flow.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/form.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/icon.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/list.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/location.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/login.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/navtree.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/pagination.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/panel.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/popup.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/progress.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/reset.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/tab.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/table.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/tips.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/common.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/layout.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/jquery-ui-1.7.1.custom.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/skins/<%=theme%>/css/appearance.css"	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/jqueryUI/jquery-ui-1.7.1.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/accordion.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/login.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/panel.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/tab.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/table.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/tips.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/layout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/blockUI/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/jqueryUI/jquery.dialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/i18n/<%=locale%>/language.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/form/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/form/jquery.additional-methods.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/form/jquery.contextmenu.r2.js"></script>

<script language="javaScript" src="<%=request.getContextPath()%>/aibi_<%=request.getParameter("componentCode")%>/js/locale/<%=request.getParameter("componentCode")%>_messageBundle_<%=request.getLocale().getLanguage()+country%>.js"></script>
<%if ( request.getLocale().getLanguage().equals("en") ) {%>
<script language="javaScript" src="<%=request.getContextPath()%>/aibi_<%=request.getParameter("componentCode")%>/js/locale/<%=request.getParameter("componentCode")%>_messageBundle_<%=request.getLocale().getLanguage()%>.js"></script>
<%}%>
