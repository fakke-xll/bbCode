<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'testUp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	
	<style type="text/css">
	  input{width:250px}
	
	</style>
 <script type="text/javascript">
 //使table显示在中间
 window.onload=function(){
  var height=document.documentElement.clientHeight;  //==> 可见区域宽度
  var width=document.documentElement.clientWidth     // ==> 可见区域高度 
  var tableWidth=document.getElementById("mytable").width
  document.getElementById("mytable").style.position="absolute"; 
  document.getElementById("mytable").style.top=height/3;
  document.getElementById("mytable").style.left=width/2-tableWidth/2;
  document.getElementById("msg").style.position="absolute"; 
  document.getElementById("msg").style.top=height/3-50;
  document.getElementById("msg").style.left=width/2-tableWidth/2+200;
  document.getElementById("currentFileSize").style.position="absolute"; 
  document.getElementById("currentFileSize").style.top=height/3+100;
  document.getElementById("currentFileSize").style.left=width/2-tableWidth/2+200;
  }
</script>
<script type="text/javascript"> 
var fileRealSize=0; 
var flag=false;
var i=0;
function currentFileSize(){
if(flag==false){
  $.get("${pageContext.request.contextPath}/FileSizeServlet",
		function(data){		
			  
			//alert(data);
			i++;
			var currentSize=parseFloat(data);  
			var realSize=parseFloat(fileRealSize);  
			var remainTime=Math.round(realSize/(currentSize/i));
			if(currentSize==0)  currentSize=1;
			if(remainTime<i){
			    remainTime=i;
			}
            document.getElementById("currentFileSize").innerHTML="文件大小："+Math.round(realSize/1024)+"KB"
            +"<br>已经上传:"+Math.round(currentSize/realSize*100)+"%"
            +"<br>已用时间："+i+"s"
            +"<br>预计剩余时间："+(remainTime-i)+"s";
            
           }
	);   
	//alert(fileRealSize);
	
 }
 setTimeout("currentFileSize()",1000); 
}
function callback(msg)   
{   
    //document.getElementById("file").outerHTML = document.getElementById("file").outerHTML;   
    document.getElementById("msg").innerHTML = "<font color=red>"+msg+"</font>";   
    flag=true;
}   


</script>
  </head>
  
<body>
<span id="msg">文件即将上传...</span> 
<table align="center" id="mytable" style="" width="500">
 <form action="${pageContext.request.contextPath}/upServlet" method="post" enctype="multipart/form-data" target="hidden_frame">
     <tr><td align="right" width="30%">文件名：</td><td width="60%"><input type="text" size="20" name="filename"> </td></tr>
     <tr><td align="right" width="30%">上传文件：</td><td width="60%"><input type="file" name="fileup" onchange="fileChange(this,'fileup')" id="fileup"></td></tr>
    <tr><td></td><td><input type="submit" value="  上    传   " onclick="currentFileSize()"></td></tr>
  </form>
</table>   
<span  id="currentFileSize"></span>
<script type="text/javascript"> 
//currentFileSize();
</script>
<iframe name="hidden_frame" id="hidden_frame" style="display:none"></iframe>

 </body>

</html>
