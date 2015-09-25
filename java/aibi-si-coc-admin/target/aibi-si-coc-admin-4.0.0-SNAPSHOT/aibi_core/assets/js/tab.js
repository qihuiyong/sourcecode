// JavaScript Document for tab
/*
Aisainfolinkage All copyright reserved
Date: 2010.10.18
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/
$(function(){
	if($(".aibi_tab_click").length!=0){
		aibiTabClick(".aibi_tab_click");//鼠标点击TAB
	}
	if($(".aibi_tab_secondary").length!=0){
		aibiTabSecondaryClick(".aibi_tab_secondary");//子tab点击效果
	}
})

function aibiTabClick(obj){//鼠标点击TAB，obj是类名或者是id名
	$( obj +" .tab_caption li").click(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var temp_id = $(this).parent().children("li").index($(this));
		$(this).parent().parent().next().children("div").removeClass("on");
		$(this).parent().parent().next().children("div").eq(temp_id).addClass("on");
		return false;
	})
}

function aibiTabSecondaryClick(obj){
	$( obj +" .tab_caption_secondary li").click(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var temp_id = $(this).parent().children("li").index($(this));
		$(this).parent().parent().next().children("div").removeClass("on");
		$(this).parent().parent().next().children("div").eq(temp_id).addClass("on");
		return false;
	})
}