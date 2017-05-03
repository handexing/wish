<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>

<%@include file="/common/common.jsp"%>
<link rel="stylesheet" href="${ctx}/css/login.css" />

</head>
<body class="beg-login-bg">
		<div class="beg-login-box">
			<header>
				<h1 style="margin-top:-15px;">WISH后台登录</h1>
			</header>
			<div class="beg-login-main">
					<div class="layui-form-item">
						<label class="beg-login-icon"><i class="layui-icon">&#xe612;</i></label>
						<input type="text" id="account" placeholder="这里输入登录账号" class="layui-input">
					</div>
					<div class="layui-form-item">
						<label class="beg-login-icon"><i class="layui-icon">&#xe642;</i></label>
						<input type="password" id="password" placeholder="这里输入密码" class="layui-input">
					</div>
					<div class="layui-form-item">
						<button class="layui-btn layui-btn-primary" id="loginBtn" style="width: 125px;">登录</button>
						<button class="layui-btn layui-btn-primary" id="cancelBtn" style="width: 125px;">清空</button>
					</div>
			</div>
			<footer>
				<p>copyright © handexing</p>
			</footer>
		</div>
		
		<script>
			$(function(){
				
				$('#cancelBtn').bind('click',function(){
					$("#account").val("");
					$("#password").val("");
				});
				
				$('#loginBtn').bind('click',function(){
					
					var parms={"account":$("#account").val(),"password":$("#password").val()};
					
					$.post("user/login",parms,function(data){
						console.log(data);
						if(data.success){
							window.location.href = "main.jsp";
						} else {
							layer.msg("登陆失败，请检查用户名与密码!", {icon: 7});
						}
					}).fail(function(data){
							layer.msg("系统异常，请联系系统管理员!", {icon: 7});
					});
					
				});
				
			});
		</script>
	</body>
</html>