<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${basePath}/StaticResources/js/jquery-3.1.0.min.js"></script>
  </head>
  
  <body>
    <button type="button" onclick="submit()">发送ajax请求</button>
    <table>
    	<tbody>
    		<tr>
    			<td>姓名</td>
    			<td>${userName }</td>
    		</tr>
    		<tr>
    			<td>密码</td>
    			<td>${password }</td>
    		</tr>
    	</tbody>
    </table>
    <script>
    	$(function(){
    		
    		
    	});
    	
    	function submit(){
    		alert("发送请求");
    		var url = "${basePath}/Test/getUserInfo";
    		window.location.href = url;
    		
    	}
    	
    </script>
  </body>
</html>
