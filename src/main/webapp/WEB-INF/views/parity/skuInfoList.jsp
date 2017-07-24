<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品管理</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
	    <table class="table table-border table-bordered table-bg table-hover table-sort" id="skuInfoList" style="margin-top: 25px;">
	        <thead>
		        <tr class="text-c">
		            <th>名称</th>
		            <th>副标题</th>
		            <th>价格</th>
		            <th>创建时间</th>
		            <th>操作</th>
		        </tr>
	        </thead>
	    </table>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/wish/parityConfig.js"></script>
	
	<script type="text/javascript">
		$(function(){
			new parityConfig();
		});
	</script>
	
</body>
</html>