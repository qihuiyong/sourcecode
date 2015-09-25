<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ page isELIgnored="false" %>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%//class i18n file include%>
<%@ page import="com.asiainfo.biframe.utils.i18n.LocaleUtil"%>
<%//tag i18n file include%>
<c:set var="localeLanguage" value="${header['accept-language']}" />
<fmt:setLocale value="${fn:split(localeLanguage,',')[0]}"/>
<fmt:setBundle basename ="config.aibi_suitedevbase.suitedevbase-appresources" var="suitedevbaseBundle"/>



