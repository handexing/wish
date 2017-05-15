<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
	    <div class="layui-inline">
	   		<input type="hidden" id="userId">
	        <label class="layui-form-label" style="padding: 9px 10px;width: 30px;">账号</label>
	        <div class="layui-input-inline" style="width: 300px;">
	            <input type="text" id="account" placeholder="请输入账号" autocomplete="off" class="layui-input">
	        </div>
	
	        <button class="layui-btn" id="search"><i class="layui-icon">&#xe615;</i>搜索</button>
	        <button class="layui-btn" id="adding"><i class="layui-icon">&#xe654;</i>新增</button>
	        <button class="layui-btn" id="exportData"><i class="layui-icon">&#xe654;</i>导出</button>
	    </div>
	    
	    <table class="table table-border table-bordered table-bg table-hover table-sort" id="userList" style="margin-top: 25px;">
	        <thead>
	        <tr class="text-c">
	            <th>昵称</th>
	            <th>账号</th>
	            <th>电话</th>
	            <th>状态</th>
	            <th>创建时间</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="createDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 35%;height:120px;margin-top:200px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">新增用户</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
					<div class="modal-body" style="width: 100%;height: 100%;">
							<div class="layui-form-item" style="margin-top:15px;">
								<div class="layui-inline">
									<input type="hidden" id="id"> 
							    	<label class="layui-form-label">昵称</label>
							    	<div class="layui-input-inline" style="width: 150px;">
							      		<input type="text" id="name" placeholder="请输入昵称" autocomplete="off" class="layui-input">
							    	</div>
							    	<div class="layui-form-mid">账号</div>
							    	<div class="layui-input-inline" style="width: 150px;">
							      		<input type="text" id="m_account" placeholder="请输入登陆账号" autocomplete="off" class="layui-input">
							    	</div>
							  	</div>
							</div>
							<div class="layui-form-item" style="margin-top:15px;margin-left:42px;">
								<div class="layui-inline">
							    	<div class="layui-form-mid">联系方式</div>
							    	<div class="layui-input-inline" style="width: 150px;">
							      		<input type="text" id="phone" placeholder="请输入联系方式" autocomplete="off" class="layui-input">
							    	</div>
							    	<div class="layui-form-mid">角色</div>
								    <div class="layui-input-inline" style="width: 150px;">
									    <span class="select-box">
										  <select class="select" style="height: 28px;" id="m_role">
										  </select>
										</span>
								    </div>
							  	</div>
							</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="addUserBtn" value="提交">
						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
					</div>
			</div>
		</div>
	</div>
	
	<div id="updateDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 35%;height:80px;margin-top:200px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" id="modelTitle"></h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
					<div class="modal-body" style="width: 100%;height: 80px;">
						<div class="layui-form-item" style="margin-top:15px;">
							<div class="layui-inline">
								<input type="hidden" id="id"> 
							    <label class="layui-form-label">昵称</label>
							    <div class="layui-input-inline" style="width: 150px;">
							      	<input type="text" id="userName" placeholder="请输入昵称" autocomplete="off" class="layui-input">
							    </div>
							    <div class="layui-form-mid">角色</div>
							    <div class="layui-input-inline" style="width: 150px;">
								    <span class="select-box">
									  <select class="select" style="height: 28px;" id="roleList">
									  </select>
									</span>
							    </div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="updateUserBtn" value="提交">
						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
					</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/wish/userConfig.js"></script>
	
	<script type="text/javascript">
		$(function(){
			new userConfig();
		});
	</script>
	
</body>
</html>