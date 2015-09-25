// JavaScript Document for basis
/*
Aisainfolinkage All copyright reserved
Date: 2010.12.13
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/
$(function(){
	aibiPanelStretch();
	aibiPanelWithTab();//tab，点击效果
	aibiInputPulldown();// 点击输入框弹出层
	if($(".aibi_tab_click").length!=0){
	aibiTabClick(".aibi_tab_click");//鼠标点击TAB
	}
	if($(".aibi_tab_secondary").length!=0){
		aibiTabSecondaryClick(".aibi_tab_secondary");//子tab点击效果
	}
	if($(".aibi_icon_close").length!=0){
	aibiTipsClose();
	}
	if($(".aibi_layout_Classic_coat").length!=0){ //布局， 高度减2，为边框让出2px的高度
		$(".aibi_layout_Classic_coat").height($(".aibi_layout_Classic_coat").height()-2);
	}

})
// ===carousel start===
function aibiCarousel(x,t){
	var _t=this;
	var i=0;
	var interAuto;
	var len;
	_t.auto=function(t){
		i=$(".adv_nums ul li").index($(".adv_nums ul li.on")[0]);
		//alert(i);
		interAuto=setInterval(function(){
			_t.showjQueryFlash(i);
			_t.switchNum(i);
			i++;
			if(i>(len-1)){i=0}
		}
		,parseInt(t));
	}
	this.switchNum=function(i){
		$(".adv_nums ul li.on").removeClass("on");
		$(".adv_nums ul li").eq(i).addClass("on");
	}
	this.clickchange=function(){
		clearInterval(interAuto);
		$(this).addClass("on").siblings().removeClass("on");
		i=$(".adv_nums ul li").index($(".adv_nums ul li.on")[0]);
		_t.showjQueryFlash(i);
	}
	this.showjQueryFlash=function(i){
		var tmpli=$(".adv_nums ul li").eq(0);
		$(".aibi_carousel .adv_imgs li").eq(i).animate({opacity: 1},1000).css({"z-index": "1"}).siblings().animate({opacity: 0},1000).css({"z-index": "0"});
		//$("#flow").animate({ right: i*tmpli.width()+8+10 +"px"}, 700 ); //滑块滑动	
	}
	_t.init=function(t){
		var _ul=$("<ul class=\"adv_imgs\"></ul>");
		var _ul2=$("<div class=\"adv_nums\"><ul></ul></div>");
		for(var y=0;y<aibiImgary.length;y++){
			//var img=$(aibiImgary[y]);
			var tmp="<a target=\"blank\" href=\""+aibiImgurl[y]+"\"><img src=\""+aibiImgary[y]+"\" /></a>";
			//var tmp=url.append(img)
			if(y==0){
				var _li="<li class=\"first\">"+tmp+"</li>";
				var _li2="<li class=\"on\">1</li>";
			}
			else{
				var _li="<li>"+tmp+"</li>";
				var _li2="<li>"+(y+1)+"</li>";
			}
			_ul.append(_li);			
			_ul2.find("ul").append(_li2);
			
		}
		var advflash=$("<div class=\"aibi_carousel\"></div>");
		advflash.append(_ul).append(_ul2);
		$(x).append(advflash);
		//alert(_ul.html());
		len=$(".aibi_carousel .adv_imgs li").length;
		_t.auto(t);
		$(".aibi_carousel .adv_imgs li img").hover(
			function(){
				clearInterval(interAuto);
			},
			function(){
				_t.auto(t);
			}
		);
		$(".adv_nums ul li").bind("click",_t.clickchange);
	}
	_t.init(t);
}
// ===carousel end===
// === list start
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
// ===list end

// ===login start
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

// ===login end// panel start
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
// tab start
function aibiTabClick(obj){//鼠标点击TAB，obj是类名或者是id名
	$( obj +" .tab_caption li").click(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var temp_id = $(this).parent().children("li").index($(this));
		$(this).parent().parent().next().children("div").removeClass("on");
		$(this).parent().parent().next().children("div").eq(temp_id).addClass("on");
	})
}

function aibiTabSecondaryClick(obj){
	$( obj +" .tab_caption_secondary li").click(function(){
		$(this).parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var temp_id = $(this).parent().children("li").index($(this));
		$(this).parent().parent().next().children("div").removeClass("on");
		$(this).parent().parent().next().children("div").eq(temp_id).addClass("on");
	})
}
// tab end 
// ===table start
//一般table隔行换色start
function aibiTableChangeColor(obj,num){//obj为 类名或者id, num为even、odd
	$(obj+" tr:"+num ).addClass("aibi_tr_changecolor");
}
//一般table隔行换色end


//一般table的效果start
function aibiTableDataHover(obj){//obj为 类名或者id，鼠标悬浮整行变色
	$( obj+ " tr").hover(function(){
		$(this).addClass('aibi_tr_hoverd');
	},function(){
		$(this).removeClass('aibi_tr_hoverd');
	})
}

function aibiTableDataClickTd(obj){//obj为类名或者ID， 单元格点击效果
	$(obj +" tr td").click(function(){
		if($(this).attr('class')!='aibi_td_selected'){
			$(".aibi_table_data tr td").removeClass('aibi_td_selected');	
			$(this).addClass('aibi_td_selected');
		}
		else{
			$(this).removeClass('aibi_td_selected');
		}
	})
	
	$(".aibi_table_data tr td input").click(function(e){
		if (e.stopPropagation) e.stopPropagation();
		else e.cancelBubble = true;
	})
}

function aibiTableDataClickTr(obj){//obj为类名或者id 单元格点击效果整行变色
	$(obj+" tr").click(function(){
		
		if($(this).hasClass('aibi_tr_selected')){
			$(this).removeClass('aibi_tr_selected');
		}
		else{
			$(".aibi_table_data tr").removeClass('aibi_tr_selected');	
			$(this).addClass('aibi_tr_selected');
		}
	})
	
	$(".aibi_table_data tr input").click(function(e){
		if (e.stopPropagation) e.stopPropagation();
		else e.cancelBubble = true;
	})
}

//一般table的效果end
// ===table end 
// tips start
function aibiTipsClose(){//基本提示，关闭	 
	$(".aibi_icon_close").click(function(){
		$(this).parent().fadeOut(500);
	})
}
// tips end
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