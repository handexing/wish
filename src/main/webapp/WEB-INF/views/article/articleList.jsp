<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章管理</title>

<%@include file="/common/common.jsp"%>

    <link rel="stylesheet" href="${ctx}/js/plugins/markdown/stylesheets/vendor/font.css"/>
    <link rel="stylesheet" href="${ctx}/js/plugins/markdown/stylesheets/vendor/markdown.css"/>
    <link rel="stylesheet" href="${ctx}/js/plugins/markdown/stylesheets/emoji/nature.css"/>
    <link rel="stylesheet" href="${ctx}/js/plugins/markdown/stylesheets/emoji/object.css"/>
    <link rel="stylesheet" href="${ctx}/js/plugins/markdown/stylesheets/emoji/people.css"/>
    <link rel="stylesheet" href="${ctx}/js/plugins/markdown/stylesheets/emoji/place.css"/>
    <link rel="stylesheet" href="${ctx}/js/plugins/markdown/stylesheets/emoji/Sysmbols.css"/>
    <link rel="stylesheet" href="${ctx}/js/plugins/markdown/stylesheets/emoji/twemoji.css"/>
    <link rel="stylesheet" href="${ctx}/js/plugins/markdown/stylesheets/vendor/font-awesome.css"/>
    <link rel="stylesheet" href="${ctx}/js/plugins/markdown/stylesheets/vendor/sunburst.css"/>

</head>
<body>
	<div class="layui-form-item" style="padding: 10px;">
	    <div class="layui-inline">
	        <label class="layui-form-label" style="padding: 9px 10px;width: 30px;">标题</label>
	        <div class="layui-input-inline" style="width: 300px;">
	            <input type="text" id="title" placeholder="请输入标题" autocomplete="off" class="layui-input">
	        </div>
	        <button class="layui-btn" id="search"><i class="layui-icon">&#xe615;</i>搜索</button>
	        <button class="layui-btn" id="adding"><i class="layui-icon">&#xe654;</i>新增</button>
	    </div>
	    
	    <table class="table table-border table-bordered table-bg table-hover table-sort" id="articleList" style="margin-top: 25px;">
	        <thead>
	        <tr class="text-c">
	            <th>标题</th>
	            <th>状态</th>
	            <th>创建时间</th>
	            <th>修改时间</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="createDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 99.5%;height:87%;">
			<div class="modal-content radius">
				<div class="modal-header">
					<div style="float: left;">
						<label class="layui-form-label" style="padding: 9px 10px;width: 30px;">标题</label>
				        <div class="layui-input-inline" style="width: 300px;">
				        	<input type="hidden" id="articleId">
				            <input type="text" id="m_title" placeholder="请输入标题" autocomplete="off" class="layui-input">
				        </div>
					</div>
					<div style="clear: both;"></div>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<div class="modal-body" style="width: 100%;height: 100%;padding: 0px;">
					<textarea name="content" rows="24" placeholder="这里输入内容,支持Markdown语法." id="content"></textarea>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" id="saveDraftBtn" value="保存草稿">
					<input type="submit" class="btn btn-primary" id="createArticleBtn" value="发布">
					<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/wish/articleConfig.js"></script>
	
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/underscore/underscore-min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/raphael/raphael.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/sequence-diagram/sequence-diagram-min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/flowchart/flowchart.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/flowchart/jquery.flowchart.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/highlight/highlight.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/markdown/he.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/markdown/marked.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/markdown/to-markdown.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/markdown/jsHtmlToText.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/markdown/tab.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/markdown/config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/markdown/emoji.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/markdown/bootstrap-markdown.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/plugins/markdown/javascripts/vendor/markdown/locale/bootstrap-markdown.zh.js"></script>
	        
	
	<script type="text/javascript">
		$(function(){
			new articleConfig();
		});
	</script>
	
</body>
</html>