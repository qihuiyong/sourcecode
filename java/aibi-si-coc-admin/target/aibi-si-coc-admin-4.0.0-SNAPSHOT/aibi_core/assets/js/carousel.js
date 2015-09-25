// JavaScript Document for carousel
/*
Aisainfolinkage All copyright reserved
Date: 2010.10.16
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/


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
