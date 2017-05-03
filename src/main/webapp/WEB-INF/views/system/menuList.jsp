<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div style="padding: 10px;">
			<div class="layui-form-item">
				<div class="layui-inline">
					<input type="hidden" id="parentId">
					<input type="hidden" id="isParent">
			    	<label class="layui-form-label" style="padding: 9px 10px;width: 30px;">标题</label>
			    	<div class="layui-input-inline" style="width: 150px;">
			      		<input type="text" id="title" placeholder="请输入标题" autocomplete="off" class="layui-input">
			    	</div>
			    	<div class="layui-form-mid">URL</div>
			    	<div class="layui-input-inline" style="width: 150px;">
			      		<input type="text" id="href" placeholder="请输入请求路径" autocomplete="off" class="layui-input">
			    	</div>
			    	<div class="layui-form-mid">ICON</div>
			    	<div class="layui-input-inline" style="width: 150px;">
			      		<input type="text" id="icon" placeholder="请输入ICON" autocomplete="off" class="layui-input">
			    	</div>
			    	<div class="layui-input-inline" style="width: 150px;">
						<button class="layui-btn" id="createMenuBtn"><i class="layui-icon">&#xe61f;</i> 新建</button>
			    	</div>
			  	</div>
			</div>
		
		<div class="panel panel-default">
			<div class="panel-header">菜单列表</div>
			<div class="panel-body">
				<div id="menu" style="padding: 20px;"></div>
			</div>
		</div>
		
	</div>
	
	<div id="editDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 35%;height:80px;margin-top:200px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">编辑菜单</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<!-- <form action="checkBpmn" method="post" class="layui-form"> -->
					<div class="modal-body" style="width: 100%;height: 80px;">
							<div class="layui-form-item" style="margin-top:15px;">
								<div class="layui-inline">
									<input type="hidden" id="id"> 
							    	<label class="layui-form-label">标题</label>
							    	<div class="layui-input-inline" style="width: 150px;">
							      		<input type="text" id="titleTxt" placeholder="请输入标题" autocomplete="off" class="layui-input">
							    	</div>
							    	<div class="layui-form-mid">URL</div>
							    	<div class="layui-input-inline" style="width: 150px;">
							      		<input type="text" id="hrefTxt" placeholder="请输入请求路径" autocomplete="off" class="layui-input">
							    	</div>
							    	<!-- <div class="layui-form-mid">ICON</div>
							    	<div class="layui-input-inline" style="width: 150px;">
							      		<input type="text" id="iconTxt" placeholder="请输入ICON" autocomplete="off" class="layui-input">
							    	</div> -->
							  	</div>
							</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="updateMenuBtn" value="提交">
						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
					</div>
				<!-- </form> -->
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/wish/menuConfig.js"></script>
	
	<script type="text/javascript">
		$(function(){
			new menuConfig();
		});
	</script>
</body>
</html>