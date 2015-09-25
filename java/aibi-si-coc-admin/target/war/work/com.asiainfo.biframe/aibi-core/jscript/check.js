	function IsNumeric(sText){
	   var ValidChars = "0123456789-()";
	   var IsNumber=true;
	   var Char;	
	 
	   for (i = 0; i < sText.length && IsNumber == true; i++){ 
	      Char = sText.charAt(i); 
	      if (ValidChars.indexOf(Char) == -1){
	         IsNumber = false;
	      }
	   }
	   return IsNumber;	   
	}
	
	function check_empty(text){
		var str = trim(text);
	  return (str.length > 0);
	}
	
	function isProper(string) {
    if (string.search(/^\w+( \w+)?$/) != -1)
        return true;
    else
        return false;
	}

	function quotesCheck(str){
		if (str.indexOf("'")>=0)
			return true;
	}
	
	function doubleQuotesCheck(str){
		if (str.indexOf('"')>=0)
			return true;
	}
	
	function trim(p_strString){
		var iLen=p_strString.length;
		while(p_strString.substring(0,1)==" "){
			p_strString=p_strString.substring(1,iLen);
		}
		return p_strString;
	}
				
	//显示树
	function onShowDiv(div){
		//结合tree.jsp使用
		if(div.style.display==""){
			div.style.display="none";
			return;
		}
		var leftMar = -4;
		var topMar = 0;
		var space =2;
		vSrc = window.event.srcElement;
		h = vSrc.offsetHeight;
		w = vSrc.offsetWidth;
		l = vSrc.offsetLeft + leftMar+4;
		t = vSrc.offsetTop + topMar + h + space-2;
		vParent = vSrc.offsetParent;
		while (vParent.tagName.toUpperCase() != "BODY"){
			l += vParent.offsetLeft;
			t += vParent.offsetTop;
			vParent = vParent.offsetParent;
		}
		div.style.top = t;
		div.style.left = l;
		div.style.display = "";
	}	
	
	//隐藏树
	function onHideDiv(div) {
		div.style.display="none";
	}

	function MM_swapImgRestore() { //v3.0
	  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
	}
	
	function MM_preloadImages() { //v3.0
	  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
	    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
	    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}
	
	function MM_findObj(n, d) { //v4.01
	  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
	    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	  if(!x && d.getElementById) x=d.getElementById(n); return x;
	}
	
	function MM_swapImage() {
	  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	   if ((x=MM_findObj(a[i]))!=null){
	     document.MM_sr[j++]=x;
	     if(!x.oSrc) x.oSrc=x.src;
	     x.src=a[i+2];
	     }
	}
	
	// 删除选项(s1=>s2)
	function onDeleteOption(pos,s1,s2){
		/*
	    var objAdded =  document.getElementById(s2);
	    var objDelete = document.getElementById(s1);
	   	//alert("2");
	    if (objDelete.selectedIndex == -1)
	    {
	    	alert("请选择要删除的对象");
	    	return;
	    }
	
	    // 取得字段值
	    var strName = objDelete.options[objDelete.selectedIndex].text;
	    var strValue = objDelete.options[objDelete.selectedIndex].value;
	    // 建立Option对象
	    var objOption = new Option(strName,strValue);
	    if (pos == -1 & pos > objAdded.options.length)
	       objAdded.options[objAdded.options.length] = objOption;
	    else
	       objAdded.add(objOption, pos);
	    //删除另一个对话框中的对象
	    objDelete.options[objDelete.selectedIndex] = null;
	    */
	var objSource = document.getElementById(s1);
    var objObject = document.getElementById(s2);
    var sourcelen = objSource.length ;
    for(var i=0;i<sourcelen;i++){
       if (objSource.options[i].selected){
           if (!checkExist(objObject,objSource.options[i].value)){
              addtolist(objObject,objSource.options[i].text,objSource.options[i].value);
            }
       }
    }
    var objLen = objSource.length;
    for (var i=objLen-1;i>=0;i--){
	    if (objSource.options[i].selected){
	          objSource.options.remove(i);
	    }
  	}
	}
	
		// 删除选项(s1=>s2)
	//新闻管理Portlet，新建页面专用方法
	function onDeleteOptionMessage(pos,s1,s2){
		/*
	    var objAdded =  document.getElementById(s2);
	    var objDelete = document.getElementById(s1);
	   	//alert("2");
	    if (objDelete.selectedIndex == -1)
	    {
	    	alert("请选择要删除的对象");
	    	return;
	    }
	
	    // 取得字段值
	    var strName = objDelete.options[objDelete.selectedIndex].text;
	    var strValue = objDelete.options[objDelete.selectedIndex].value;
	    // 建立Option对象
	    var objOption = new Option(strName,strValue);
	    if (pos == -1 & pos > objAdded.options.length)
	       objAdded.options[objAdded.options.length] = objOption;
	    else
	       objAdded.add(objOption, pos);
	    //删除另一个对话框中的对象
	    objDelete.options[objDelete.selectedIndex] = null;
	    */
	 var selectSize = document.getElementById("selectIndex").value;
	  var index = document.getElementById(s1).selectedIndex;
	  
	  if(index <= selectSize-1){
	  	alert(aibi_core_messageBundle.alert_userGroupNotDelete);
	  	return;
	  }
	var objSource = document.getElementById(s1);
    var objObject = document.getElementById(s2);
    var sourcelen = objSource.length ;
    for(var i=0;i<sourcelen;i++){
       if (objSource.options[i].selected){
           if (!checkExist(objObject,objSource.options[i].value)){
              addtolist(objObject,objSource.options[i].text,objSource.options[i].value);
            }
       }
    }
    var objLen = objSource.length;
    for (var i=objLen-1;i>=0;i--){
	    if (objSource.options[i].selected){
	          objSource.options.remove(i);
	    }
  	}
	}
	
	//添加选项(s1=>s2)
	function onAddOption(type,s1,s2){
    var objSource = document.getElementById(s1);
    var objObject = document.getElementById(s2);
    var sourcelen = objSource.length ;
    for(var i=0;i<sourcelen;i++){
       if (objSource.options[i].selected){
           if (!checkExist(objObject,objSource.options[i].value)){
              addtolist(objObject,objSource.options[i].text,objSource.options[i].value);
            }
       }
    }
    var objLen = objSource.length;
    for (var i=objLen-1;i>=0;i--){
	    if (objSource.options[i].selected){
	          objSource.options.remove(i);
	    }
  	}
	}
	
  function addtolist(obj,value,label){
  	obj.add(new Option(value,label));
  }
  
  function checkExist(obj,value){
    var isExist = false;
    for (var i=0;i<obj.length;i++){
      if (obj.options[i].value == value){
        isExist = true;
        break;
      }
    }
  	return isExist;
  }
        	
	function loadMapUser(data){
		var mapUser  = document.all.mapUser;
		mapUser.options.length=0;
		var len = data.length;
		for(var i = 0; i < len; i++) {
			var strValue = data[i++];
			var strName = data[i];
				
			// 建立Option对象
			var objOption = new Option(strName,strValue);
			mapUser.options[mapUser.options.length] = objOption;
		}
	}
	
	function loadNotMapUser(data){
		var notMapUser  = document.all.notMapUser;
		notMapUser.options.length=0;
		var len = data.length;
		for(var i = 0; i < len; i++) {
			var strValue = data[i++];
			var strName = data[i];
				
			// 建立Option对象
			var objOption = new Option(strName,strValue);
			notMapUser.options[notMapUser.options.length] = objOption;
		}
	}
	
	/* 
	 value: 值；
	 byteLength:数据库字节长度
	 title:字段中文名称
	 attribute:属性名称
	*/ 
	function limitLength(value, byteLength, title, attribute) {
		var newvalue = value.replace(/[^\x00-\xff]/g, "**");
	  var length = newvalue.length;
	  // 当填写的字节数小于设置的字节数 
	  if(length<= byteLength){
	  	return ;
	  }
	  var limitDate = newvalue.substr(0, byteLength);
	  var count = 0 ;
	  var limitvalue = "" ;
	  for(var i=0; i<limitDate.length; i++ ) {
	  	var flat  =  limitDate.substr(i,  1 );
	  	if(flat=="*" ) {
	  		count++ ;
	  	}
	  }
	  var size = 0 ;
	  var istar = newvalue.substr(byteLength*1-1, 1); // 校验点是否为“×” 
	  
	  // if 基点是×; 判断在基点内有×为偶数还是奇数 
	  if(count%2==0){
	  	// 当为偶数时 
	  	size = count/2+(byteLength*1-count);
	  	limitvalue = value.substr(0, size);
	  }else{
	  	// 当为奇数时 
	  	size = (count-1 )/2+(byteLength*1-count);
	  	limitvalue = value.substr(0, size);
	  }
	  alert(title+aibi_core_messageBundle.alert_maxInput+byteLength/2+aibi_core_messageBundle.alert_wordCount);
	  document.getElementById(attribute).value = limitvalue;
	  return ;
	}

function checkPwd(m_strLoginUserID,value){
	alert("loginUser = "+m_strLoginUserID);
	alert("pwd = "+value);
	 var ret = '';
	 DWREngine.setAsync(false);
	 MessageService.checkPwd(m_strLoginUserID,value,{callback:function(data){ret = data;},async:'false'});
	 alert(ret);		
}


	/**
		根据内容动态调整select控件的宽度.
		注意:select的宽度必须以em为单位,否则不进行调整.
		id	待调整的select控件id
	*/
	function ProcessSelectWidth(id){
		var defaultLength=23;//默认23/2=11.5em宽度
		var obj = document.getElementById(id);
		if(obj==null){
			return;
		}
		var selWidth=obj.style.width;
		//alert(selWidth+";"+selWidth.indexOf("em"));
		if(selWidth.indexOf("em")<0){//select宽度必须以em表示
			return;
		}
		var maxLength=parseInt(obj.style.width)*2;//em,相当与两个字节(或者一个汉字)的宽度
		if(isNaN(maxLength)){
			return;
		}
		if(maxLength>defaultLength){
			maxLength=defaultLength;
		}
		for(var i=0;i<obj.options.length;i++){
			var selValue = obj.options[i].text;
			var newvalue = selValue.replace(/[^\x00-\xff]/g, "**");//一个中文字符即为两个字节
		  var selLength = newvalue.length;
		  if(selLength%2!=0){
				selLength++;
			}
		  if(selLength>maxLength){
		  	maxLength=selLength;
		  }
		}
		//alert(id+";"+maxLength);
		obj.style.width=maxLength/2+0.5+"em";
		//alert(id+";"+obj.style.width);
		return;
	}
	
//container: 
//设定子项搜索的范围（一个td或一个table等，这样在检查input的时候不用全文档搜索和检测，节省时间）
//如果没有传此参数，则默认全文档搜索
function onClickRes(chk,Container){	
	if(Container == undefined || Container == null){
		Container = document;
	}
	var allitems = Container.all.tags("input");
	for(i = 0; i < allitems.length; i++){
		if(allitems[i].type!="checkbox"){
			continue;
		}

		allitems[i].checked = chk.checked;
	}
	return;
}

//图片，名称
function showTable(img){
	if(null == img)
		return;
	strName=img.name;
	nPos = strName.indexOf("_");
	if(nPos<0)
		return;
	strName = strName.substr(nPos+1);
	strName = "TABLE_"+strName;
	var obj = document.getElementById(strName);
	if(obj.style.display=='none'){
		obj.style.display='';
		if(img.LASTM =="0")
			img.src="/images/treeimg/minus.gif";
		else
			img.src="/images/treeimg/minusbottom.gif";
		
	}else{
		obj.style.display='none';
		if(img.LASTM =="0")
			img.src="/images/treeimg/plus.gif";
		else
			img.src="/images/treeimg/plusbottom.gif";
	}	
}
