<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务管理</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
	
	    <table class="table table-border table-bordered table-bg table-hover table-sort" id="taskList">
	        <thead>
		        <tr class="text-c">
					<th width="100">任务ID</th>
					<th width="100">任务名称</th>  
					<th width="100">创建时间</th> 
					<th width="100">办理人</th> 
					<th width="100">操作</th> 
		        </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="showImgDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 50%;height:500px;margin-top:100px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">详细流程</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<div class="modal-body" style="height: 500px;">
					<!-- 1.获取到规则流程图-->
					<img id="processImg" style="position: absolute;top: 0px;left: 0px;">

					<!-- 2.根据当前活动的坐标，动态绘制DIV -->
					<div id="taskNode"></div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/wish/taskConfig.js"></script>
	
	<script type="text/javascript">
		$(function(){
			new taskConfig();
		});
	</script>
	
</body>
</html>