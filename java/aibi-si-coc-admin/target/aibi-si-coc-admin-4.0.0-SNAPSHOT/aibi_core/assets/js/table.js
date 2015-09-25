// JavaScript Document for table
/*
Aisainfolinkage All copyright reserved
Date: 2010.10.18
Version: v1.0
Creator: Wulei
E-mail: wulei@asiainfo-linkage.com
*/
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