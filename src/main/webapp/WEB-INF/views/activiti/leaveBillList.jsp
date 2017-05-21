<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假流程列表</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
	
	   	<div class="layui-inline">
	        <button class="layui-btn" id="adding"><i class="layui-icon">&#xe654;</i>新增请假</button>
	    </div>
	     
	    <table class="table table-border table-bordered table-bg table-hover table-sort" id="leaveBillList">
	        <thead>
		        <tr class="text-c">
					<th width="100">姓名</th>
					<th width="100">请假天数</th>  
					<th width="100">开始日期</th> 
					<th width="100">请假说明</th> 
					<th width="100">状态</th> 
					<th width="100">新建时间</th> 
					<th width="100">备注</th> 
		        </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="addLeaveBillDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"  style="margin-top: 200px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">新增假条</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<form action="addLeaveBill" method="post" class="form form-horizontal" id="myform" novalidate="novalidate">
					<div class="modal-body">
							<div class="layui-form-item" style="margin-top:15px;">
							<div class="layui-inline">
								<input type="hidden" id="id"> 
							    <label class="layui-form-label">请假天数</label>
							    <div class="layui-input-inline" style="width: 180px;">
							      	<input type="text" name="days" placeholder="请输入请假天数..." autocomplete="off" class="layui-input">
							    </div>
							    <div class="layui-form-mid">开始日期</div>
							    <div class="layui-input-inline" style="width: 180px;">
							    <input type="text" name="leaveDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input-text Wdate" readonly="readonly">
							    </div>
							</div>
						</div>
						<div class="layui-form-item" style="margin-top:15px;margin-left:40px;">
							<div class="layui-inline">
							    <div class="layui-form-mid">请假原因</div>
							    <div class="layui-input-inline" style="width:500px;">
							      	<input type="text" name="content" placeholder="请输入请假原因..." autocomplete="off" class="layui-input">
							    </div>
							</div>
						</div>
						<div class="layui-form-item" style="margin-top:15px;margin-left:40px;">
							<div class="layui-inline">
							    <div class="layui-form-mid">备注</div>
							    <div class="layui-input-inline" style="width:500px;">
							      	<input type="text" name="remark" placeholder="请输入备注..." autocomplete="off" class="layui-input">
							    </div>
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
	
	<script type="text/javascript" src="${ctx}/js/wish/leaveBillConfig.js"></script>
	
	<script type="text/javascript">
		$(function(){
			new leaveBillConfig();
		});
	</script>
	
</body>
</html>