// JavaScript Document for accordion
/*
Aisainfolinkage All copyright reserved
Date: 2010.12.02
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/
$(function(){
	aibiList4Slide();
})

//list_4 start
function aibiList4Slide(){//列表4的伸缩功能，只显示当前点击的列表
	$(".aibi_list_4 dt a").click(function(){
		if($(this).parent().next().attr("class")!="on"){
			$(".aibi_list_4 dd").hide().removeClass("on");
			$(this).parent().next().slideDown().addClass("on");
		}
	})
}
//list_4 end
