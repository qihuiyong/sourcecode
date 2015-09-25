// JavaScript Document for panel
/*
Aisainfolinkage All copyright reserved
Date: 2010.10.16
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/
$(function(){
/*	if(($(".aibi_panel").length!=0)&&($(".aibi_panel .tab_caption").length==0)){
		aibiPanelStretch();
	}
	if(($(".aibi_panel").length!=0)&&($(".aibi_panel .tab_caption").length!=0)){
		aibiPanelStretch();
		aibiPanelWithTab();//tab，点击效果
	}
	
*/		aibiPanelStretch();
		aibiPanelWithTab();//tab，点击效果
})

// panel start
function aibiPanelStretch(){
	$(".aibi_panel .bar_down").click(function(){
		if($(".caption_common").length==0){
			$(this).toggleClass("bar_up");
			$(this).parent().next().toggle(0);
		}
		if($(".caption_common").length!=0){
			$(this).parent().toggleClass("caption_common_second");
			$(this).toggleClass("bar_up");
			$(this).parent().next().toggle(0);
		}		
	})
	
	$(".aibi_panel .bar_down_sub").click(function(){
		$(this).toggleClass("bar_up_sub");				
		$(this).parent().next().toggle(0);
	})
}

function aibiPanelWithTab(){//tab，点击效果
	$(".aibi_panel .tab_caption li").click(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var temp_id = $(this).parent().children("li").index($(this));
		$(this).parent().parent().parent().next().children("div").removeClass("on");
		$(this).parent().parent().parent().next().children("div").eq(temp_id).addClass("on");
	})
}

// panel end