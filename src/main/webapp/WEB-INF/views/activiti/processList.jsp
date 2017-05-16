<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>activiti管理</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
	
	   	<div class="layui-inline">
	        <button class="layui-btn" id="adding"><i class="layui-icon">&#xe654;</i>新增流程</button>
	    </div>
	     
	    <table class="table table-border table-bordered table-bg table-hover table-sort" id="processList">
	        <thead>
		        <tr class="text-c">
					<th width="100">流程名称</th>
					<th width="100">版本号</th>  
					<th width="100">资源文件名称</th> 
					<th width="100">图片资源名称</th> 
		        </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="addProcessDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"  style="margin-top: 200px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">添加流程</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<form action="checkBpmn" method="post" enctype="multipart/form-data" class="form form-horizontal" id="myform" novalidate="novalidate">
					<div class="modal-body">
							<div class="row cl">
								<label class="form-label col-xs-4 col-sm-3">流程附件：</label>
								<div class="formControls col-xs-8 col-sm-9"> 
									<span class="btn-upload form-group">
										<input class="input-text upload-url" type="text" readonly="readonly" style="width:200px">
										<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont"></i> 浏览文件</a>
										<input type="file" multiple="" name="file" id="file" class="input-file">
									</span> 
								</div>
							</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="deployProcessBtn" value="提交">
						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div id="openProcessDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="margin-top: 100px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">详细流程</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<div class="modal-body">
					<div id="canvas"></div>
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