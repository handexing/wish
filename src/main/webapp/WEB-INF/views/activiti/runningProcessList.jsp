<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运行中的流程管理</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
	
	    <table class="table table-border table-bordered table-bg table-hover table-sort" id="runProcessList">
	        <thead>
		        <tr class="text-c">
					<th width="100">流程名称</th>
					<th width="100">版本号</th>  
					<th width="100">资源文件名称</th> 
					<th width="100">图片资源名称</th> 
					<th width="100">操作</th> 
		        </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="openProcessDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%;height:500px;margin-top:100px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">详细流程</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<div class="modal-body" style="height: 500px;">
					<div id="canvas" style="width: 100%;height: 100%"></div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/wish/processConfig.js"></script>
	<script type="text/javascript" src="${ctx}/js/wish/bpmnConfig.js"></script>
	
	<script type="text/javascript">
		$(function(){
			new processConfig();
		});
	</script>
	
</body>
</html>