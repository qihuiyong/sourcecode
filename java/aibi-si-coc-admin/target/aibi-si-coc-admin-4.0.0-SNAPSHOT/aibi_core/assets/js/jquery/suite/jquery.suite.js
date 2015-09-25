jQuery.suite= {
  ajax:function(link) {
		//alert(link);
		$.ajax({
  		url: link,
  		cache: false,
  		async: false,
  		success: function(data){
  			if ( data.indexOf('finish')!=-1 ) {
    		  resultSubmit('success',$(data).find('H2').html());
    	  } else {
    		  resultSubmit('error',$(data).find('H2').html());
    	  }
  		}
		});
	},
  alert:function(msg){
		//var msg = options.msg ? options.msg :'haha';
		alert(msg);

		//impl depend jquery.dialog.js
		/*
		$.dialog({
			type:0,//与dialogTypeList内容对应,必选项
			title:"消息",//弹出框的title
			tipMsg:msg,
			//imgTip:"fda",
			showHelp:true,//是否显示帮助
			showMail:false//是否显示联系我们
		});
		*/
		
		return 100;
	},
  confirm: function(msg){
		var re = 0;
		
		re = confirm(msg);
		
		/*
		$.dialog({
			title:'confirm 使用方法',
			type:2,//与dialogTypeList内容对应
			showHelp:false,//是否显示帮助
			showMail:false,//是否显示联系我们
			yesCall:function(){
				alert("你点击了确定");
				re = 1;
			},
			noCall:function(){
				alert("你点击了取消");
			    re = 2;
			}
		});
		 */
		return re;
	},
	//获得table中选择行的某列的值
	selectedValue: function(tableId,columnId){
		rows = AIBI_JQTable.getSelectedRowsByTableId(tableId);

		id = null;
		for( i=0;i<rows.length;i++ ){
			id = AIBI_JQTable.getData( $(rows[i]),columnId.toUpperCase() );
		}
		return id;
	},
	showModalDialog: function(url){
		window.showModalDialog(url, "", "center:yes;resizable:yes;dialogHeight:600px;dialogWidth:800px;")
	},
	showModelessDialog: function(url){
		window.showModelessDialog(url, "", "center:yes")
	}
}