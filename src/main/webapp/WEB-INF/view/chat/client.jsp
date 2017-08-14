<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

    <div style="width:500px;height:500px;margin:auto auto;border:1px solid cornflowerblue;">
        <div id="content">
        </div>
        <div style="width:100%;height:20%;position:relative; top:450px;">
            <textarea id="message" style="width:80%;resize:none;" rows="2"></textarea>
            <button type="button" id="submit">发送</button>
            <button type="button" id="quit">退出</button>
        </div>
    </div>

    <p>当前在线：</p>
</body>
<script>
    var websocket = new Object();
    websocket.socket = null;

    websocket.connect = (function(url){
        if("WebSocket" in window){
            websocket.socket = new WebSocket(url);
        }else if("MozWebSocket" in window){
            websocket.socket = new MozWebSocket(url);
        }else{
            alert("该浏览器不支持websocket");
        }

        websocket.socket.onopen = function (){
            document.getElementById("submit").addEventListener("click",function(){
                websocket.sendMessage();
            });
            console.log("有人登陆了");
        }

        websocket.socket.onclose = function(){
            alert("已经退出");
        }

        websocket.socket.onmessage = function(message){
            var p = document.createElement("p");
            p.innerHTML = message.data;
            document.getElementById("content").appendChild(p);
        }
    });

    websocket.initalization = function(){
        if (window.location.protocol == 'http:') {
            websocket.connect('ws://' + window.location.host + '/javaweb/websocketServer');
        } else {
            websocket.connect('wss://' + window.location.host + '/javaweb/websocketServer');
        }
        document.getElementById("quit").addEventListener("click",function(){
            websocket.socket.close();
        });
    }

    websocket.sendMessage = function(){
        var message = document.getElementById("message").value;
        if(message != "") {
            websocket.socket.send(message);
            document.getElementById("message").value="";
        }
    } 

    websocket.initalization();
</script>
</html>