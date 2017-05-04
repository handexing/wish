<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二维码</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
	    <div class="layui-inline">
	        <label class="layui-form-label" style="padding: 9px 10px;width: 30px;">内容</label>
	        <div class="layui-input-inline" style="width: 300px;">
	            <input type="text" id="content" placeholder="请输入要生成二维码的内容" autocomplete="off" class="layui-input">
	        </div>
	        <button class="layui-btn" id="okBtn">确认</button>
	    </div>
	</div>

	<div style="margin-left: 90px">
		<img id="qrcode" src="">
	</div>
	
	<script type="text/javascript" src="${ctx}/js/wish/zxingConfig.js"></script>
	
	<script type="text/javascript">
		$(function(){
			new zxingConfig();
		});
	</script>
	
</body>
</html>