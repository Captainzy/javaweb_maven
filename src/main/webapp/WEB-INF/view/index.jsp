<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    			<td id="userName">${userName }</td>
    		</tr>
    		<tr>
    			<td>密码</td>
    			<td id="password">${password }</td>
    		</tr>
    	</tbody>
    </table>
    <button onclick="ceshi()">测试</button>
    <script>
    	var xmlhttp;
    	function submit(){
    		var url = "${basePath}/Test/getUserInfoNew?userName=ZOUYANG&password=1234";
    		//jquery ajax
   /*  		$.ajax({
    			url:url,
    			data:null,
    			type:"GET",
    			dataType:"json",
    			async:true,
    			success:function(rs){
    			 	alert(rs);
    			},
    			error:function(){
    				alert("出错了");
    			}
    		
    		});   */
    		
    		//原生JS ajax
    		xmlhttp = new XMLHttpRequest();
    		xmlhttp.open("POST",url,true);
    		xmlhttp.onreadystatechange = callback;
    		var data = "username=ZOUYANG&password=123";
    		xmlhttp.send(data);
    		
    	}
    	function callback(){
    		var rs;
    		if(xmlhttp.readyState == 4){
    			rs = xmlhttp.responseText;
    			var userName = "${userName}";
    			alert(userName);
    		}
    		var password = "${password}";
    	}
    	
    	function ceshi(){
    		var url = "${basePath}/Test/testUserInfo";
    		xmlhttp = new XMLHttpRequest();
    		xmlhttp.open("POST",url,true);
    		xmlhttp.send(null);
    		var user = "${userName}";
    	}
    </script>
  </body>
</html>
