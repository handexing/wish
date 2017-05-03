<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
		<input type="hidden" id="groupId">
	    <div class="layui-inline">
	        <button class="layui-btn" id="adding"><i class="layui-icon">&#xe654;</i>新增</button>
	    </div>
	    
	    <table class="table table-border table-bordered table-bg table-hover table-sort" id="roleList" style="margin-top: 25px;">
	        <thead>
	        <tr class="text-c">
	            <th>名称</th>
	            <th>状态</th>
	            <th>创建时间</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="createDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 25%;height:80px;margin-top:200px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">新增角色</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
					<div class="modal-body" style="width: 100%;height: 80px;">
						<div class="layui-form-item" style="margin-top:15px;">
								<div class="layui-inline">
									<input type="hidden" id="id"> 
							    	<label class="layui-form-label">角色名称</label>
							    	<div class="layui-input-inline" style="width: 200px;">
							      		<input type="text" id="title" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
							    	</div>
							  	</div>
							</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="addRoleBtn" value="提交">
						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
					</div>
			</div>
		</div>
	</div>
	
	<div id="settingRoleMenuDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 70%;height:70%;margin-top:150px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" id="groupName"></h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<div class="modal-body" style="width: 100%;height: 100%px;">
					<blockquote class="layui-elem-quote layui-quote-nm" style="width: 45%;float: left;">
					 	<strong>全部菜单</strong><br>
						<div id="allMenu" style="padding: 20px;"></div>
					</blockquote>
					<blockquote class="layui-elem-quote layui-quote-nm" style="width: 45%;float: left;margin-left:15px;">
					 	<strong>已配置菜单</strong><br>
						<div id="groupMenu" style="padding: 20px;"></div>
					</blockquote>
					<div style="clear: both;"></div>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" id="saveRoleMenuBtn" value="保存">
					<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/wish/roleConfig.js"></script>
	
	<script type="text/javascript">
		var ctx ='${ctx}';
		$(function(){
			new roleConfig();
		});
	</script>
	
</body>
</html>