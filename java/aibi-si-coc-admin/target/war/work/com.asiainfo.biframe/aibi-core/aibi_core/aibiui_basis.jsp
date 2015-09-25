<%@ page import="com.asiainfo.biframe.common.SysCodes" %>
<% 
         String theme = SysCodes.getSysParameter("SYS_SHOW_THEME");
         String currentTheme = (String)session.getAttribute("theme");
         if(currentTheme == null){
                   if(theme==null || theme.trim().length()==0){
                            // 添加下面的代码
// 如果样式为空，则赋予初始值”default”
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

<link href="<%=request.getContextPath()%>/aibi_core/assets/css/aibi_basis.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/jquery-ui-1.8.10.custom.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/skins/<%=theme%>/css/appearance.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/aibi_core/assets/css/i18n/<%=locale%>/language.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/jqueryUI/jquery-ui-1.8.10.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/aibi_basis.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/i18n/<%=locale%>/language.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/blockUI/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/jqueryUI/jquery.dialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/form/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/form/jquery.additional-methods.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/aibi_core/assets/js/jquery/contextmenu/jquery.contextmenu.r2.js"></script>