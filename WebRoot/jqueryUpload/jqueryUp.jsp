<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'jqueryUp.jsp' starting page</title>
   <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
   <script src="${pageContext.request.contextPath}/jqueryUpload/jquery.uploadify.min.js" type="text/javascript"></script>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jqueryUpload/uploadify.css">
   <style type="text/css">
   body {
	font: 13px Arial, Helvetica, Sans-serif;
   }
   </style>
   </head>

<body>
<%pageContext.setAttribute("date",new Date().getTime());%>
	<h1>Uploadify Demo</h1>
	<form>
		<div id="queue"></div>
		<input id="file_upload" name="file_upload" type="file" multiple="true">
	</form>

	<script type="text/javascript">
		
		$(function() {
			$('#file_upload').uploadify({
				'formData'     : {
					'timestamp' : '${date}',
					'token'     : '${date}'
				},
				'swf'      : '${pageContext.request.contextPath}/jqueryUpload/uploadify.swf' ,
				'uploader' : '${pageContext.request.contextPath}/upServlet2'
			});
			
		});
function callback(msg)   
{   
    document.getElementById("msg").innerHTML = "<font color=red>"+msg+"</font>";   
}  
</script>
<span id="msg">文件正在上传...${date}</span> 
<input type="button" value="保存" onclick="save()"/>
<script type="text/javascript">
function save(){
alert(${date});
   location.href="${pageContext.request.contextPath}/saveServlet?val="+${date};
}
</script>
  </body>
</html>
