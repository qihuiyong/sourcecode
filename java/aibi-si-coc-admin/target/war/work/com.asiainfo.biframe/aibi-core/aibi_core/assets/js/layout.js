// JavaScript Document for layout
/*
Aisainfolinkage All copyright reserved
Date: 2011.01.06
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/
$(function(){
	if($(".aibi_layout_Classic_coat").length!=0){ // 调整树容器的高度
		$(".aibi_layout_Classic_coat").height($(".aibi_layout_Classic_coat").height()-29);
		$(window).resize(); 
	}
})
//aibiLayoutClassic  start
function aibiLayoutClassic(intialiseWidth,intialiseSlide){
	var tempW = intialiseWidth;//intialise the col_side's width
	var tempMargin= tempW+12;//intialise the col_main's width
	var tempSlide = intialiseSlide;
	
	//intial the left_panel SlideUp or SlideDown
	if(tempSlide == true){
		$(".aibi_layout_Classic .col_side").css("width",tempW+"px");
		$(".aibi_layout_Classic .col_extra").addClass("hided");
		//hack the IE6's "3px bug"
		if ($.browser.msie && parseInt($.browser.version,10) < 7){
			$(".aibi_layout_Classic .col_main").css("margin-left",tempMargin-3+'px');
		}
		else{
			$(".aibi_layout_Classic .col_main").css("margin-left",tempMargin+'px');
		}
	}
	else{
		$(".aibi_layout_Classic .col_side").css("width",0);
		$(".aibi_layout_Classic .bar_left").addClass("bar_right");
		if ($.browser.msie && parseInt($.browser.version,10) < 7){
			$(".aibi_layout_Classic .col_main").css("margin-left","10px");
		}
		else{
			$(".aibi_layout_Classic .col_main").css("margin-left","12px");
		}
		$(".aibi_layout_Classic .col_extra").removeClass("hide");
	}
	
	//Change the layout
	$(".aibi_layout_Classic .col_extra").click(function(){
	var $t=$(this);
	$t.children(".bar_left").toggleClass("bar_right");
	if($(this).hasClass("hided")){
		$t.prev(".col_side").animate({width:0},200,function(){$t.removeClass("hided")});
		if ($.browser.msie && parseInt($.browser.version,10) < 7){
			$t.next(".col_main").animate({marginLeft:0},200);
		}
		else{
			$t.next(".col_main").animate({marginLeft:12},200);
		}
	}
	else{
		$t.prev(".col_side").animate({width:tempW},200,function(){$t.addClass("hided")});
			if ($.browser.msie && parseInt($.browser.version,10) < 7){
				$t.next(".col_main").animate({marginLeft:0},200);
			}
			else{
				$t.next(".col_main").animate({marginLeft:tempW+12},200);
			}
		}
	})
	
}
//aibiLayoutClassic  end

//aibiLayoutClassicHover start
function aibiLayoutClassicHover(intialiseWidth,intialiseHide){
		var tempW = intialiseWidth;
		var tempHide = intialiseHide;
		//intial the left_panel hide or show
		if(tempHide == true){
			$(".aibi_layout_Classic_hover > .col_hover_bar > .col_hover_side").addClass("hided").css("width","0");
		}
		else{
			$(".aibi_layout_Classic_hover > .col_hover_bar > .col_hover_side").removeClass("hided").css("width",tempW);
			$(".aibi_layout_Classic_hover > .col_hover_bar > .bar_hover_left").addClass("bar_hover_right");
			$(".aibi_layout_Classic_hover > .col_hover_bar").addClass("col_hover_bar_on");
			if ($.browser.msie && parseInt($.browser.version,10) < 7){
					$(".aibi_layout_Classic_hover > .col_hover_bar").children(".col_hover_side_hack").show();
			}
		}
		
		//intial width
		if ($.browser.msie && parseInt($.browser.version,10) < 7){
			$(".aibi_layout_Classic_hover > .col_hover_bar > .col_hover_side_hack").css("width",tempW);
		}
		//col_hover_bar's hover style
		$(".aibi_layout_Classic_hover .col_hover_bar").hover(function(){
			$(this).addClass("col_hover_bar_hover");														  
		},function(){
			$(this).removeClass("col_hover_bar_hover");	
		})
		//col_hover_bar's click
		$(".aibi_layout_Classic_hover .col_hover_bar").click(function(e){
																	  
			$(this).toggleClass("col_hover_bar_on");
			$(this).children(".bar_hover_left").toggleClass("bar_hover_right");
			
			if (e.stopPropagation) e.stopPropagation();
			else e.cancelBubble = true;
			
			//show or hide the ".col_hover_side"
			if($(this).children(".col_hover_side").hasClass("hided")){
				$(this).children(".col_hover_side").animate({width:tempW},200).removeClass("hided");
				if ($.browser.msie && parseInt($.browser.version,10) < 7){
					$(this).children(".col_hover_side_hack").show();
				}
			}
			else{
				if ($.browser.msie && parseInt($.browser.version,10) < 7){
					$(this).children(".col_hover_side_hack").hide();
				}
				$(this).children(".col_hover_side").animate({width:0},200).addClass("hided");
			}
		})
		
		//hide the ".col_hover_side"
		$(document).click(function(){
			$(".aibi_layout_Classic_hover > .col_hover_bar > .col_hover_side").css("width","0").addClass("hided");
			$(".aibi_layout_Classic_hover > .col_hover_bar > .bar_hover_left").attr("class","bar_hover_left");
			if ($.browser.msie && parseInt($.browser.version,10) < 7){
				$(".aibi_layout_Classic_hover > .col_hover_bar > .col_hover_side_hack").hide();
			}
		})
		
		$(".aibi_layout_Classic_hover > .col_hover_bar > .col_hover_side").click(function(e){
			if (e.stopPropagation) e.stopPropagation();
			else e.cancelBubble = true;
		})
}

//aibiLayoutClassicHover end