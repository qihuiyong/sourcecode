<%@ page contentType="text/html; charset=gb2312" %>
<%@page import="com.asiainfo.biframe.log.ILogService"%>
<%@ page import="com.asiainfo.biframe.utils.spring.SystemServiceLocator" %>
<%@ page import="com.asiainfo.biframe.log.LogLevelEnum" %>
<%@ page import="com.asiainfo.biframe.log.OperResultEnum" %>
<html>
<head>
<script type="text/javascript">
function testHistSum(){
	<%
	ILogService logService = (ILogService)SystemServiceLocator.getInstance().getService("logService");
		String sql = "select /*+ parallel( tab ,4) */ table_0.month_time,table_0.city_id,table_0.county_id,table_0.channel_id,table_0.new_busi_type_code,table_0.channel_type_code,table_0.position_type_code,table_0.deal_count,table_0.reward,table_0.busi_deal_type,table_0.reward_type_code,table_0.avg_reward from (select op_time month_time,city_id ,county_id ,channel_id ,new_busi_type_code ,channel_type_code ,position_type_code ,deal_count ,reward ,busi_deal_type ,reward_type_code ,reward/deal_count avg_reward from St_AI_Chl_Phy_Rwd_New_Busi_MM where 1=1  and (to_char(op_time,'yyyy-mm') ='2009-03')) table_0";
		logService.log("111","mutouli","木头李","130.1.1.1","140.1.1.1",
				logService.getLogDefineValue(1,"LOG_OPERATION_ADD"),logService.getLogDefineValue(2,"LOGTYPE_USER"),
				"reourceId","resourceName","测试msg",null,null,OperResultEnum.Failure,LogLevelEnum.Risk);
	%>
}

</script>
</head>
<body>

<table>
	<tr>
		<td>
			测试操作日志记录
		</td>
		<td>
			<input type="button" oncilik="testHitSum()" value="测试"/>
		</td>
	</tr>

</table>
</body>

</html>