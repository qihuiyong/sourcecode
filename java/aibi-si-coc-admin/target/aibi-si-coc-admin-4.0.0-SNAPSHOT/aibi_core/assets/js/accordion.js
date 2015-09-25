// JavaScript Document for accordion
/*
Aisainfolinkage All copyright reserved
Date: 2010.10.16
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/
$(function(){
	//if($(".aibi_accordion").length!=0){
		//aibiAccordion();
	//}
})

//aibiAccordion点击效果start
function aibiAccordion(){
	$(".aibi_accordion .caption").click(function(){
		//if($(this).hasClass("on")==false){
			$(".aibi_accordion .caption").removeClass("on");
			$(".aibi_accordion .content").removeClass("content_on");
			$(this).children(".bar_down").toggleClass("bar_up");
			$(this).addClass("on");
			$(this).next().addClass("content_on");
		//}
	})
	
	
	//计算高度start
	var tempHeight=$(window).height();
	$(".aibi_accordion").height(tempHeight-1);
	var tempCaptionTotalHeight = ($(".aibi_accordion .caption").length)*30;
	var tempContentHeight = tempHeight-tempCaptionTotalHeight;
	
	$(".aibi_accordion .content").height(tempContentHeight-6);
	$(window).resize(function(){
		var tempHeight=$(window).height();
		$(".aibi_accordion").height(tempHeight-1);
		var tempCaptionTotalHeight = ($(".aibi_accordion .caption").length)*30;
		var tempContentHeight = tempHeight-tempCaptionTotalHeight;
		$(".aibi_accordion .content").height(tempContentHeight-6);
	})

	//计算高度end
}
//aibiAccordion点击效果end

//aibiAccordion2 start
function aibiAccordion2(num){
	//赋最小高度值
	var tempCount=	$(".aibi_accordion_2 .caption").length;
	for(i=0; i<tempCount; i++){
		$(".aibi_accordion_2 .content").eq(i).css('height',aibiAccordionMinAry[i]+'px');
	}
	
	//初始化，使得默认第num个的高度为最大
	$(".aibi_accordion_2 .caption").eq(num).addClass("on");
	$(".aibi_accordion_2 .content").eq(num).css('height',aibiAccordionMaxAry[num]+'px');
	
	//点击
	$(".aibi_accordion_2 .caption").click(function(){
		if($(this).hasClass("on")){
			void(0);
		}
		else{
			$(".aibi_accordion_2 .caption").removeClass("on");
			$(this).addClass("on");
			
			//全体赋最小值
			for(i=0; i<tempCount; i++){
				$(".aibi_accordion_2 .content").eq(i).css('height',aibiAccordionMinAry[i]+'px');
				//$(".aibi_accordion_2 .content").css("overflow","hidden");
			}
			
			//当前点击的赋最大值
			var tempID = $(this).parent().children(".caption").index($(this));
			$(".aibi_accordion_2 .content").eq(tempID).css('height',aibiAccordionMaxAry[tempID]+'px');
			//$(".aibi_accordion_2 .content").eq(tempID).css("overflow","auto");
		}
	})
}
//aibiAccordion2 end