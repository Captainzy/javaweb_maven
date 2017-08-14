<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" charset="UTF-8" src="../StaticResources/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="../StaticResources/js/webuploader.js"></script>
</head>
<body>
<p>文件上传页面</p>
普通上传<br>
<form action="/javaweb/upload/uploadByStream" enctype="multipart/form-data" method="post">
	<input id="files1" type="file" name="files1" multiple>
	<button type="submit">上传</button>
</form>
<hr>
利用spring解析器上传
<form action="/javaweb/upload/uploadByMultipartFile" enctype="multipart/form-data" method="post">
	<input id="files2" type="file" name="files2" multiple>
	<button type="submit">上传</button>
</form>
<hr>
利用webuploader插件
多文件上传
 <div class="btns">
     <div id="picker">上传文件</div>
     <span id="filesNum">已选择文件数 ：0</span><br>
     <progress id="progress" value="0" max="100"></progress>
     <span id="percent">0%</span><br>
     <button id="ctlBtn" class="btn btn-default" onclick="beginUpload()">开始上传</button><br>
     <button id="ctlBtn" class="btn btn-default" onclick="stopUpload()">中断上传</button><br>
     <span id="error"></span>
 </div>
<script>
	var uploader;
	$(document).ready(function(){
		//var url = "/javaweb/upload/uploadByStream";
		var url = "/javaweb/upload/uploadByMultipartFile"
		uploader = new WebUploader.Uploader({
			 pick:{
				 id:'#picker',
				 multiple:true
			 },
			 server: url,
			 method:'POST'
		});
		
		uploader.on("filesQueued",function(){
			var files = uploader.getFiles();
			$("#filesNum").text("已选择文件数 ："+files.length);
		});
		
		uploader.on("startUpload",function(){
			alert("文件现在开始上传");
		});
		
		uploader.on("uploadProgress",function(file,percentage){
			var percent = parseInt(percentage * 100) + '%';
			$("#percent").text(percent);
			$("#progress").val(parseInt(percentage * 100));
		});
		
		uploader.on("uploadFinished",function(){
			alert("文件上传结束");
			uploader.reset();
			$("#percent").text(0+"%");
			$("#progress").val(0);
			$("#filesNum").text("已选择文件数 ：0");
		});
		uploader.on("uploadError",function(file,reason){
			alert("上传文件出错");
			var str = "";
			reason = reason.toLowerCase();
			if(reason == "server"){
				str = "服务器端发生异常"
			}else if(reason=="http"){
				str = "网络异常";
			}else {
				str = "上传异常终止";
			}
			$("#error").text(str);
		});
		
		uploader.on("stopUpload",function(){
			alert("中断上传");
		});
	});
	
	var beginUpload = function(){
		uploader.upload();
	}	
	var stopUpload = function(){
		uploader.stop(true);
	}


</script>
</body>
</html>