// JavaScript Document for form
/*
Aisainfolinkage All copyright reserved
Date: 2011.03.09
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/
$(function(){
	aibiInputPulldown();
})
// aibi_input_pulldown start
function aibiInputPulldown(){	 
	$(".aibi_input_pulldown .aibi_input_txt_common").click(function(e){
		$(this).next(".pop_wrap").toggle("fast");
		if (e.stopPropagation) e.stopPropagation();
		else e.cancelBubble = true;
		if ($.browser.msie && parseInt($.browser.version,10) < 7){// ie6下弹出遮盖下拉的iframe
			$(".pop_wrap_hack").toggle("fast");
		}
	})
	$(".aibi_input_pulldown .pop_wrap").click(function(e){
		if (e.stopPropagation) e.stopPropagation();
		else e.cancelBubble = true;
	})
	$(document).click(function(){
		$(".aibi_input_pulldown .pop_wrap").hide();
		if ($.browser.msie && parseInt($.browser.version,10) < 7){// ie6下弹出遮盖下拉的iframe
			$(".pop_wrap_hack").hide();
		}
	})
}
function closeAibiInputPulldown(){
	$(".aibi_input_pulldown .pop_wrap").hide();
		if ($.browser.msie && parseInt($.browser.version,10) < 7){// ie6下弹出遮盖下拉的iframe
			$(".pop_wrap_hack").hide();
		}
}
// aibi_input_pulldown end