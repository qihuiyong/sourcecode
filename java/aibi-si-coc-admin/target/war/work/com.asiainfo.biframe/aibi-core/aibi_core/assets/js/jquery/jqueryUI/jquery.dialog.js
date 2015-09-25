 /*
 * Title: jquery.dialog.js. 
 * Description: 

 * Aucthor:chengjia
 * Create Date:2010-11-23
 * Call Method: $.dialog(msg);
 */
( function($) {

	  //配置信息
	var dialogTypeList = [
		{type:0,	className:'aibi_popup_success',width:350,height:'auto'},
		{type:1,	className:'aibi_popup_wrong',width:440,height:'auto'},
		{type:2, className:'aibi_popup_confirm',width:300,height:'auto'}	
	];
	/**
	 * 操作成功、失败等提示框
	 */
	jQuery.dialog =  function(options) {
		var settings = {
			type:0,//与dialogTypeList内容对应
			title:"",//弹出框的title
			contentUrl:"",
			divId:"",
			isIframe:false,
			modal:true,
			tipMsg:"",
			imgTip:"",
			className:dialogTypeList[0].className,
			width:dialogTypeList[0].width,
			height:dialogTypeList[0].height,
			errorCode:'',
			showHelp:false,//是否显示帮助,true
			showMail:false,//是否显示联系我们
			yesCall:false,
			noCall:false,
			selfIconCall:false,//自定义按钮事件
			beforeClose:function(){},//点击叉按钮触发事件
			error : function() {
				alert("对话框加载出错");
			}
		};
		var type = options.type ? options.type :settings.type;
		
		var config_set =dialogTypeList[type];
		
		var opts = jQuery.extend(settings, options,config_set);
	
		//增加popup所在div
	 	var popupDiv =$("<div class='aibi_popup'></div>");
	 	var popupUrl = opts.contentUrl;
	 
	 	//如果使用了自定义的URL,则加载
	 	if(popupUrl!=null && popupUrl!=""){
	 		    //清除之前使用的popup div
					$("div").filter(".jquery_popup").empty().remove();
	 		    popupDiv.appendTo("body");
	 		    popupDiv.addClass("jquery_popup");
	 		    $.dialog.alert(popupDiv,opts);	
					//popup div内容设置
					$.ajax({
							type :'post',
							async :true,
							cache :false,
							url : popupUrl,
							dataType:"html",
							cache :false,
							success : function(html) {
							   install(opts,popupDiv,html);
							   popupDiv.dialog("open");
				 		}
					});
			 
			}else{
			   //弹出本页面的div,处理比较特殊
			   if(opts.divId!=null && opts.divId!=""){
			   	 popupDiv.attr("name",opts.divId);
			   	 $("#"+opts.divId).wrapInner(popupDiv);
			   	 install(opts,$(".aibi_popup"),"");

			   }else{
			   	  //清除之前使用的popup div
					 $("div").filter(".jquery_popup").empty().remove();
					  popupDiv.addClass("jquery_popup");
			   	  var html =loadContent(opts);
			    	popupDiv.appendTo("body");
			    	install(opts,popupDiv,html);
			    	 popupDiv.dialog("open");
			   }
			   
			}
	};
	

	 //弹出消息
		$.dialog.alert = function(popupDiv, opts) {
	    popupDiv.dialog({ 
			autoOpen: false, 
			title: opts.title, 
			modal: opts.modal, 
			resizable:false, 
			draggable: false,
			model:opts.model,
			width:opts.width,
			height:opts.height,
			minHeight:1,
			marginLeft:-(opts.width/2),
			marginTop:-(opts.height),
			position:[50+'%',50+'%'],
			overlay: {  opacity: 0.5 }
	    }); 

	};
	/**打开div对话框*/
	$.fn.open = function() {
		$(".aibi_popup[name='"+this.attr("id")+"']").dialog("open");
	}
	/**关闭div对话框*/
	$.fn.close = function() {
		$(".aibi_popup[name='"+this.attr("id")+"']").dialog("close");
	}
	/**
	 *对话框获取弹出内容后做的操作
	 */
  function install(opts,popupDiv,html){
    popupDiv.addClass(opts.className);
				//根据type为dialog所在DIV增加样式
				popupDiv.append(html);

				//处理[帮助][联系我们]图标
				iconLink(popupDiv,opts);
					if( opts.contentUrl==null ||  opts.contentUrl==""){
							$.dialog.alert(popupDiv,opts);	
					}

				//按钮事件注册
				$(".aibi_btn_confirm[name='sure']").click(function(){
					popupDiv.dialog("close");  
					if(opts.yesCall) opts.yesCall();
				}); 
				$(".aibi_btn_cancel[name='cancel']").click(function(){
					popupDiv.dialog("close");  
					if(opts.noCall) opts.noCall();
				}); 
				//添加窗口关闭事件注册	
				  popupDiv.bind( "dialogbeforeclose", function(event, ui) {
				   opts.beforeClose();
			   });
				//自定义按钮事件
				if(opts.selfIconCall) {
				
					opts.selfIconCall();
				}
  }

	//处理[帮助][联系我们]图标
	function iconLink(popupDiv,opts){
		var left_span = $('<span class="left"></span>');
		var help_link = ' <a href="#" class="aibi_icon_help" title="'+$busiLang.help+'">'+$busiLang.help+'</a>';
		if(opts.showHelp) left_span.append(help_link);
		var email_link = '<a href="#" class="aibi_icon_mail" title="'+$busiLang.mail+'">'+$busiLang.mail+'</a>';
		if(opts.showMail) left_span.append(email_link);
		//popupDiv.find("div").filter(".bottom").prepend(left_span.html());
		if(popupDiv.find("div").filter(".bottom").length==0){
			popupDiv.append("<div class='bottom'></div>");
		}
		left_span.prependTo( popupDiv.find("div").filter(".bottom"));
	}

	jQuery.extend({
 		 getContent: function(a, b) { return (b==null ||b=="") ? a : b; }
	});
	/**设置对话框内容*/
	function loadContent(opts){
		
		var type=opts.type;
		var divHtml=$("<div class='main'><div class='content'><div class='aibi_tips_opeation'></div></div><div class='bottom'></div></div>");
		if(type==0){
			divHtml.find(".aibi_tips_opeation").append('<span class="success" title="'+jQuery.getContent($busiLang.success, opts.imgTip)+'"></span> <span class="text">'+jQuery.getContent($busiLang.opeSuccess, opts.tipMsg)+'</span>');
		  divHtml.find(".bottom").append('<a href="###" name="sure" class="aibi_btn_gray aibi_btn_confirm"><span>'+ $busiLang.confirm +'</span></a>');
		}else if(type==1){
			divHtml.find(".aibi_tips_opeation").append('<span class="failed" title="'+jQuery.getContent($busiLang.fail, opts.imgTip)+'"></span> <span class="text">'+jQuery.getContent($busiLang.opeFail, opts.tipMsg)+'</span>');
		  var warmHtml=$('<div class="aibi_tips_warm"><h6>-' + $busiLang.cause + '-</h6><ul></ul></div>');
		  //如果要从配置表里读取错误，修改下面这行即可
		  var warnLi =$('<li id="0">' + $busiLang.timeout + '</li><li id="1">' + $busiLang.netCon + '</li><li id="2">'+ $busiLang.inputError +'</li><li id="3">'+ $busiLang.opeErr +'</li><li id="4">'+ $busiLang.netCon +'</li><li id="5">'+ $busiLang.inputError +'</li><li id="6">'+ $busiLang.opeErr +'</li>');
		  if(opts.errorCode!=null && opts.errorCode!=""){
		     var codes = opts.errorCode.split(",");
		     for(var i=0;i<codes.length;i++){
		     	 warnLi.filter("#"+codes[i]).appendTo(warmHtml.find("ul"));
		     }
		  }else{
		  	warnLi.appendTo(warmHtml.find("ul"));
		  }
		  divHtml.find(".aibi_tips_opeation").after(warmHtml);
		  divHtml.find(".bottom").append('<a href="###" name="sure" class="aibi_btn_gray aibi_btn_confirm"><span>'+ $busiLang.confirm +'</span></a>');
		}else if(type==2){
			divHtml.find(".aibi_tips_opeation").append('<span class="confirm" title="'+jQuery.getContent($busiLang.attention, opts.imgTip)+'"></span> <span class="text">'+jQuery.getContent($busiLang.cancelOpe, opts.tipMsg)+'</span>');
		  divHtml.find(".bottom").append('<a href="###" name="sure" class="aibi_btn_gray aibi_btn_confirm"><span>'+ $busiLang.confirm +'</span></a>');
		  divHtml.find(".bottom").append('<a href="###" name="cancel" class="aibi_btn_gray aibi_btn_cancel"><span>'+ $busiLang.cancel +'</span></a>');
		  divHtml.find(".bottom").addClass("bottom_center");
		}else{
			if(opts.divId!=null && opts.divId!=""){
			  return $("#"+opts.divId).html();
			}
		}
		return divHtml.get(0).innerHTML;
	}
})(jQuery);
