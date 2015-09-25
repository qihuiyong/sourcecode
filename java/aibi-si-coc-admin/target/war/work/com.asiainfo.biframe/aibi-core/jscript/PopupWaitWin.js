<script>
//lummy++ 2004-4-9 吉林
var m_wndWait4Me=window.createPopup();
var m_PopupDisplayFlag=true;
var m_message = "";
function showWait4MeWindow(message)
{
	if(m_PopupDisplayFlag==false)
	{
		m_wndWait4Me.hide();
		return;
	}
	var nWidth=310;
	var strHtml ="";
	strHtml+="<table border=1 cellpadding=0 style='border-collapse: collapse' cellspacing=0 width=300 bordercolorlight='#C0C0C0' bordercolordark='#666699'>";
	strHtml+="<tr><td width=100% >";
	strHtml+="<table border=1 cellpadding=0 style='border-collapse: collapse' cellspacing=0 width=100% bordercolorlight='#FFFFFF' bordercolordark='#666699'>";
	strHtml+="<tr><td width=100% >";
	strHtml+="<table border='0' width=100% style='border-collapse: collapse' cellpadding=0 cellspacing=0 height=31>";
	strHtml+="<tr><td width='100%' bgcolor='#000080' height='18'><b><font color='#FFFFFF' size='2'>"+aibi_core_messageBundle.label_waitMsg+"</font></b></td></tr>";
	strHtml+="<tr><td width='100%' bgcolor='menu' height='13'>　</td></tr> ";
	strHtml+="<tr> <td width='100%' bgcolor='menu' height='13'> <font size=2><p align='left' id=TIMEA>&nbsp&nbsp"+aibi_core_messageBundle.label_wait +message+"</font></td> </tr>";
	strHtml+="<tr><td width='100%' bgcolor='menu' height='13'>　</td></tr> ";
	strHtml+="</table>";
	strHtml+="</td></tr></table></td></tr></table>";
	m_wndWait4Me.document.body.innerHTML = strHtml; 
	var popupBody = m_wndWait4Me.document.body;
  m_wndWait4Me.show(0, 0,nWidth , 0);
  var realHeight = popupBody.scrollHeight;
  m_wndWait4Me.hide();
  m_wndWait4Me.show(150,150,nWidth,realHeight,document.body);
}
//showWait4MeWindow("123");

var bar = 0 
var line = "||" 
var amount ="" 
count() ;

function count(){ 
	bar= bar+2 
	amount =amount + line 
	//document.loading.chart.value=amount 
		if (bar<25) 
		{
		} 
		else 
		{
		bar=0;
		amount="";
		} 
		showWait4MeWindow(amount);
		setTimeout("count()",500);
} 

</script> 

<script>
m_PopupDisplayFlag=false;
</script>