 <%@ page import="com.asiainfo.biframe.common.SysCodes" %>
 <% 
 	String theme=SysCodes.getSysParameter("SYS_SHOW_THEME");
 	if(theme==null || theme.trim().length()==0){
 		theme="default";
 	}
 	session.setAttribute(SysCodes.DEFAULT_THEME,theme);
	
	String country="_"+request.getLocale().getCountry();
	if(country.trim().length()==1 || request.getLocale().getCountry()==null){
		country="";
	}
	String locale=request.getLocale().getLanguage()+country;
 %>
 <link href="<%=request.getContextPath()%>/aibi_core/css/<%=theme%>/locale/<%=locale%>/css/base.css" rel="stylesheet" type="text/css" />
 <link href="<%=request.getContextPath()%>/aibi_core/css/<%=theme%>/locale/<%=locale%>/css/form.css" rel="stylesheet" type="text/css" />
 <link href="<%=request.getContextPath()%>/aibi_core/css/<%=theme%>/locale/<%=locale%>/css/mend.css" rel="stylesheet" type="text/css" />
 <link href="<%=request.getContextPath()%>/aibi_core/css/<%=theme%>/locale/<%=locale%>/css/menu.css" rel="stylesheet" type="text/css" />
 <link href="<%=request.getContextPath()%>/aibi_core/css/<%=theme%>/locale/<%=locale%>/css/module.css" rel="stylesheet" type="text/css" />
 <link href="<%=request.getContextPath()%>/aibi_core/css/<%=theme%>/locale/<%=locale%>/css/panel.css" rel="stylesheet" type="text/css" />
 <link href="<%=request.getContextPath()%>/aibi_core/css/<%=theme%>/locale/<%=locale%>/css/print.css" rel="stylesheet" type="text/css" />
 <link href="<%=request.getContextPath()%>/aibi_core/css/<%=theme%>/locale/<%=locale%>/css/tab.css" rel="stylesheet" type="text/css" />
 <link href="<%=request.getContextPath()%>/aibi_core/css/<%=theme%>/locale/<%=locale%>/css/table.css" rel="stylesheet" type="text/css" />
 
  <%String imagePath=request.getContextPath()+"/aibi_core/css/"+theme+"/locale/"+locale+"/images";%>