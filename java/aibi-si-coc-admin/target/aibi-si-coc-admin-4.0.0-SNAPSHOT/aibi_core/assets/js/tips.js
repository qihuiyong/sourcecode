// JavaScript Document for tips
/*
Aisainfolinkage All copyright reserved
Date: 2010.10.18
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/
$(function(){
	if($(".aibi_icon_close").length!=0){
		aibiTipsClose();
	}
})

// tips start
function aibiTipsClose(){//基本提示，关闭	 
	$(".aibi_icon_close").click(function(){
		$(this).parent().fadeOut(500);
	})
}
// tips end