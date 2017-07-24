<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>比价管理</title>

<%@include file="/common/common.jsp"%>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
	    <div class="layui-inline">
	        <button class="layui-btn" id="adding"><i class="layui-icon">&#xe654;</i>新增</button>
	    </div>
	    
	    <table class="table table-border table-bordered table-bg table-hover table-sort" id="parityList" style="margin-top: 25px;">
	        <thead>
		        <tr class="text-c">
		            <th>sku编码</th>
		            <th>URL</th>
		            <th>创建时间</th>
		            <th>操作</th>
		        </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="createDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"  style="width: 30%;height:145px;margin-top: 200px;">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">添加url</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
					<div class="modal-body" style="width: 100%;height: 145px;">
						<div class="row cl">
							<label class="form-label col-xs-4 col-sm-3">SKU编码：</label>
							<div class="formControls col-xs-8 col-sm-9"> 
								<span class="btn-upload form-group">
									<input class="input-text upload-url" id="skuCode" type="text" style="width:250px">
								</span> 
							</div>
						</div>
						<div class="row cl" style="margin-top:10px;">
							<label class="form-label col-xs-4 col-sm-3">URL地址：</label>
							<div class="formControls col-xs-8 col-sm-9"> 
								<span class="btn-upload form-group">
									<input class="input-text upload-url" type="text" style="width:250px" id="url">
								</span> 
							</div>
						</div>
						<div class="row cl" style="margin-top:10px;">
							<label class="form-label col-xs-4 col-sm-3">选择框：</label>
							<div class="formControls col-xs-8 col-sm-9"> 
							  	<span class="select-box"  style="width:250px">
								  <select class="select" size="1" id="platfmCode">
								    <option value="" selected>请选择平台</option>
								    <option value="1">京东</option>
								    <option value="2">淘宝</option>
								    <option value="3">亚马逊</option>
								  </select>
								</span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" id="savaSkuSrcBtn" aria-hidden="true">保存</button>
						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
					</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/wish/parityConfig.js"></script>
	
	<script type="text/javascript">
		$(function(){
			new parityConfig();
		});
	</script>
	
</body>
</html>