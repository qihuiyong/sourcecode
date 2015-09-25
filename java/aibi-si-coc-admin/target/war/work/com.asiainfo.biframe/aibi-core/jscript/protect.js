
//关闭右键
document.oncontextmenu=function(){event.returnValue=false;}

//禁止拷贝
document.oncopy=function(){event.returnValue=false;}

//禁止选择
document.onselectstart=function(){event.returnValue=false;}

//禁止粘贴
document.onpaste=function(){event.returnValue=false;}

//禁止打印
window.onbeforeprint=function(){window.document.body.style.display="none"}


//关闭shift+点击链接
document.onclick=function(){if(event.shiftKey){event.returnValue=false;}}
//&&(event.srcElement.tagName=="A")

//关闭 f1帮助
//document.onhelp=function(){event.returnValue=false;}

//禁止 退格键的  前进后退功能
document.onkeydown = function() {if (event.keyCode==8 || 111<event.keyCode<123 ||event.keyCode==17 || event.keyCode==18) {event.keyCode=0;event.returnValue=false;}}
//

//关闭所有f2-f12快捷键  屏蔽 Ctrl+n  
//document.onkeydown=function(){if((111<event.keyCode<123)||(event.ctrlKey&&(event.keyCode==78))||(event.ctrlKey&&(event.keyCode==83)){event.keyCode=0;event.returnValue=false;}}
//document.onkeydown = function(){if(Event.keyCode==42||Event.keyCode==42&&Event.altKey){event.keyCode=0;event.returnValue=false;}}




