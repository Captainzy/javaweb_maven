<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<style>
    body{
        background-image:url("/javaweb/StaticResources/images/bj.jpg");
    }
    #div-scroll{
        border:1px solid red;
        position: absolute;
        top:200px;
        left:300px;
        width:400px;
        height:500px;
        overflow:hidden;
        font-size: 15px;
        color:lightgrey;
    }
    #scroll-list{
        position:absolute;
        bottom: 0px;
        height:auto;
        width:400px;
        color:lightgrey;
        overflow:hidden;
    }

   .scroll-row{
        position: relative;
        width:400px;
        max-height:36px;
        overflow:hidden;
        right:0px;
        transition:max-height 1s linear;
    }
    
	.scroll-row>span{
		display:block;
		margin:2px 0px;
	}
	
    .scroll-row-first{
       animation:heightMove 1s linear;
    }

    .scroll-row-last{
        max-height:0px;
    }
    @keyframes heightMove{
        from{right: -400px;}
        to{right:0px;}
    }


</style>
<body>

<div id="div-scroll" >
    <div id="scroll-list">

    </div>
</div>

<script>
var data = [{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，\"","risk":0},{"NAME":"ossec: Windows audit failure event.","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET INFO - STUN绑定请求\"","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET P2P - 电驴查找请求\"","risk":0},{"NAME":"S: \"GPL SHELLCODE - 64位系统寄存器空操作\"","risk":0},{"NAME":"ossec: Windows audit failure event.","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET POLICY - PE EXE 或 DLL Windows文件下载\"","risk":0},{"NAME":"S: \"ET POLICY - PE EXE 或 DLL Windows文件下载\"","risk":0},{"NAME":"S: \"ET INFO - STUN绑定请求\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET P2P - 电驴连接及服务器列表请求\"","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"ET INFO - STUN绑定请求\"","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"ET P2P - 电驴查找请求\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"ossec: Windows audit failure event.","risk":0},{"NAME":"ossec: Windows Logon Success.","risk":0},{"NAME":"S: \"ET POLICY - 利用RSA加密的TLS证书签名\r\n\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET POLICY - HTTP客户端密码包含明文\"","risk":0},{"NAME":"S: \"ET P2P - 电驴查找请求\"","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"GPL DELETED - Stacheldraht 客户端检查\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET POLICY - 利用RSA加密的TLS证书签名\r\n\"","risk":0},{"NAME":"S: \"ET P2P - 电驴连接及服务器列表请求\"","risk":0},{"NAME":"ossec: Windows audit failure event.","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET INFO - STUN绑定请求\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET POLICY - PE EXE 或 DLL Windows文件下载\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0},{"NAME":"S: \"ET TROJAN - 未定义的DNS地址空间，潜在的恶意威胁 1.1.1.0/24\"","risk":0}];
    var n = 0;
    window.onload = function(){
        for(var i = 0;i<16;i++){
            var node = document.createElement("div");
            node.className = "scroll-row";
            var span = document.createElement("span");
            span.innerHTML = data[n%data.length].NAME;
            node.appendChild(span);

            var parent = document.getElementById("scroll-list");
            parent.insertBefore(node,parent.children[0]);
            n++;
        }

        setInterval(function(){
            add();
            setTimeout(function(){
                remove();
            },1000);
        },2000);

    }

    function add(){

        var parent = document.getElementById("scroll-list");

        var lastNode = parent.lastElementChild;
        lastNode.className += " scroll-row-last";

        var firstNode = document.createElement("div");
        firstNode.className = "scroll-row scroll-row-first";
        var span = document.createElement("span");
        span.innerHTML = data[n%data.length].NAME;
        firstNode.appendChild(span);
        n++;

        parent.insertBefore(firstNode,parent.children[0]);

    }

    function remove(){
        var parent = document.getElementById("scroll-list");
        parent.removeChild(parent.lastChild);
    }

</script>
</body>
</html>