// JavaScript Document for login
/*
Aisainfolinkage All copyright reserved
Date: 2010.10.19
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/

//弹出提示start
function aibiLoginPopTips(text){//text弹出提示内容
	$(".aibi_login .content .aibi_login_tips .text").html(text);
	$(".aibi_login .content .aibi_login_tips").fadeIn(500);
}
function aibiLoginCloseTips(){//text弹出提示内容
	$(".aibi_login .content .aibi_login_tips").fadeOut(500);
}
//弹出提示end

//文本框焦点start
function aibiLoginInput(){
	$(".aibi_login .aibi_login_input_text").focus(function(){
		$(this).css("background","#ff9");
	})
	$(".aibi_login .aibi_login_input_text").blur(function(){
		$(this).css("background","#fff");
	})
}
//文本框焦点end
