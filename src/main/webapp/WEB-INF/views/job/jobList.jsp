<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动态任务调度管理</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
	
	   <div class="layui-inline">
	        <button class="layui-btn" id="adding"><i class="layui-icon">&#xe654;</i>新增</button>
	    </div>
	     
	    <table class="table table-border table-bordered table-bg table-hover table-sort" id="jobList">
	        <thead>
		        <tr class="text-c">
					<th>任务名称</th>
					<th>任务组</th>
					<th>状态</th>
					<th>cron表达式</th>
					<th>类路径</th>
					<th>springName</th>
					<th>方法名</th>
					<th>描述</th>
					<th>创建时间</th>
					<th>修改时间</th>
					<th>操作</th>
		        </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="updateDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 20%;height:80px;margin-top:200px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" id="modelTitle">修改cron表达式</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
					<div class="modal-body" style="width: 100%;height: 80px;">
						<div class="layui-form-item" style="margin-top:15px;">
							<div class="layui-inline">
								<input type="hidden" id="jobId"> 
							    <label class="layui-form-label">cron表达式</label>
							    <div class="layui-input-inline" style="width: 150px;">
							      	<input type="text" id="cron" placeholder="请输入cron表达式" autocomplete="off" class="layui-input">
							    </div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="updateCronBtn" value="提交">
						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
					</div>
			</div>
		</div>
	</div>
	
	<div id="addJobDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 55%;height:300px;margin-top:100px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">新增定时任务</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
					<div class="modal-body" style="width: 100%;height: 100%;">
						<div class="layui-form-item" style="margin-top:15px;">
							<div class="layui-inline">
								<input type="hidden" id="id"> 
							    <label class="layui-form-label">任务名称</label>
							    <div class="layui-input-inline" style="width: 180px;">
							      	<input type="text" id="jobName" placeholder="请输入名称" autocomplete="off" class="layui-input">
							    </div>
							    <div class="layui-form-mid">任务分组</div>
							    <div class="layui-input-inline" style="width: 180px;">
							      	<input type="text" id="jobGroup" placeholder="请输入任务分组名称" autocomplete="off" class="layui-input">
							    </div>
							    <div class="layui-form-mid">CRON表达式</div>
							    <div class="layui-input-inline" style="width: 220px;">
							      	<input type="text" id="cronExpression" placeholder="请输入cron表达式" autocomplete="off" class="layui-input">
							    </div>
							</div>
						</div>
						<div class="layui-form-item" style="margin-top:15px;margin-left:39px;">
							<div class="layui-inline">
							    <div class="layui-form-mid">类名称</div>
							    <div class="layui-input-inline" style="width: 235px;">
							      	<input type="text" id="beanClass" placeholder="请输入类名称（包名+类名）" autocomplete="off" class="layui-input">
							    </div>
							    <div class="layui-form-mid">SpringName</div>
							    <div class="layui-input-inline" style="width: 180px;">
							      	<input type="text" id="springId" placeholder="请输入SpringName" autocomplete="off" class="layui-input">
							    </div>
							    <div class="layui-form-mid">方法名称</div>
							    <div class="layui-input-inline" style="width: 180px;">
							      	<input type="text" id="methodName" placeholder="请输入方法名称" autocomplete="off" class="layui-input">
							    </div>
							</div>
						</div>
						<div class="layui-form-item" style="margin-top:15px;margin-left:40px;">
							<div class="layui-inline">
							    <div class="layui-form-mid">描述</div>
							    <div class="layui-input-inline" style="width:790px;">
							      	<input type="text" id="description" placeholder="请输入描述" autocomplete="off" class="layui-input">
							    </div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="addjobBtn" value="提交">
						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
					</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/wish/jobConfig.js"></script>
	
	<script type="text/javascript">
		$(function(){
			new jobConfig();
		});
	</script>
	
</body>
</html>