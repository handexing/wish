/**
 * 文章管理
 */

function articleConfig(){
	
	var self=this;
	var mTable;

	this.init=function(){
		
		$("#content").val("");
		self.articleList();
		
		$('#search').bind('click',function(){
			mTable.ajax.reload();
		});
		
		$('#createArticleBtn').bind('click',function(){
			var title = $.trim($("#m_title").val());
			

			if(title==null || title==""){
				layer.msg('标题不能为空！', {icon: 7});
				return;
			}
			
			if(content==null || content==""){
				layer.msg('内容不能为空！', {icon: 7});
				return;
			}
			
			$.post("createArticle", {"title":title,"content":content,"status":1,"id":$("#articleId").val()}, function(data, status) {
				if(data.success){
					mTable.ajax.reload();
					layer.msg('保存成功！', {icon: 1});
					$("#createDialog").modal("hide");
				}else{
					layer.msg('保存失败！', {icon: 5});
				}
			});
			
		});
		
		$('#saveDraftBtn').bind('click',function(){
			var title = $.trim($("#m_title").val());
			var content = $.trim($("#content").val());
			
			if(title==null || title==""){
				layer.msg('标题不能为空！', {icon: 7});
				return;
			}
			
			if(content==null || content==""){
				layer.msg('内容不能为空！', {icon: 7});
				return;
			}
			
			$.post("createArticle", {"title":title,"content":content,"status":0,"id":$("#articleId").val()}, function(data, status) {
				if(data.success){
					mTable.ajax.reload();
					layer.msg('保存成功！', {icon: 1});
					$("#createDialog").modal("hide");
				}else{
					layer.msg('保存失败！', {icon: 5});
				}
			});
			
		});
		
		$('#adding').bind('click',function(){
			$("#content").val("");
			$("#articleId").val("");
			$("#m_title").val("");
			$("#createDialog").modal("show");
			$("textarea").markdown({
		        language: 'zh',
		        fullscreen: {
		            enable: true,
		            debounce: 500
		        },
		        resize: 'vertical',
		        localStorage: 'md',
		        imgurl: 'imgUpload',
		        base64url: 'imgUpload',
		        flowChart : true
		    });
		});
		
	}
	
	this.articleList=function(){
		
		 mTable = $('#articleList').DataTable({
             "processing": true,
             "ordering": false,
             "searching": false,
             "serverSide": true,
             "ajax": {
                 "url": "articleList",
                 "data": function (d) {
                     d.title = $('#title').val();
                 }
             },
             "dataType": "json",
             "aLengthMenu": [10, 20, 30],
             "columns": [
                 {"data": "title"},
                 {"data": "n","render":function( data, type, row ) {
	                if(row.status == 0){
	                	return "<span class=\"label label-secondary radius\">草稿</span>";
	                } else if(row.status == 1){
	                	return "<span class=\"label label-success radius\">发布</span>";
	                } else if(row.status == 2){
	                	return "<span class=\"label label-danger radius\">下架</span>";
	                }
	              }},
                 {"data": "createTime"},
                 {"data": "updateTime"},
                 {"data": "c","render":function( data, type, row ) {
	                	return '<input class="btn btn-primary radius" onClick="preview(\''+row.id+'\')" type="button" value="预览/修改">&nbsp;&nbsp;';
	                }}
             ]
         });
		
	}

	self.init();
	
}

function preview(id){
	$.post("getArticle", {"id":id}, function(data, status) {
		if(data.success){
			console.log(id);
			$("#m_title").val(data.data.title);
			$("#articleId").val(data.data.id);
			$("#content").val(data.data.content);
			$("#createDialog").modal("show");
			$("textarea").markdown({
				language: 'zh',
				fullscreen: {
					enable: true,
					debounce: 500
				},
				resize: 'vertical',
				localStorage: 'md',
				imgurl: 'imgUpload',
				base64url: 'imgUpload',
				flowChart : true
			});
		}else{
			layer.msg('程序异常！', {icon: 5});
		}
	});
	//$("textarea").markdown({hiddenButtons:["Bold","Italic","Heading","cmdUrl","Image","Emoji","cmdList","cmdListO","Code","Quote","fullscreen"]})
}
