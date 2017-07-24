/**
 * 商品比价管理
 */

function parityConfig(){
	
	var self=this;
	var mTable;

	this.init=function(){
		
		self.parityList();
		
		$('#adding').bind('click',function(){
			$("#createDialog").modal("show");
		});
		
		$('#savaSkuSrcBtn').bind('click',function(){
			var skuCode = $.trim($("#skuCode").val());
			var url = $.trim($("#url").val());
			var platfmCode = $.trim($("#platfmCode").val());
			
			if(skuCode==null || skuCode==""){
				layer.msg('skuCode不能为空！', {icon: 7});
				return;
			}
			
			if(url==null || url==""){
				layer.msg('URL不能为空！', {icon: 7});
				return;
			}
			
			if(platfmCode==null || platfmCode==""){
				layer.msg('平台不能为空！', {icon: 7});
				return;
			}
			
			$.post("createSkuSrc", {"skuCode":skuCode,"url":url,"platfmCode":platfmCode}, function(data, status) {
				if(data.success){
					mTable.ajax.reload();
					layer.msg('保存成功！', {icon: 1});
					$("#createDialog").modal("hide");
				}else{
					layer.msg('保存失败！', {icon: 5});
				}
			});
			
		});
		
	
	}
	
	this.parityList=function(){
		
		 mTable = $('#parityList').DataTable({
             "processing": true,
             "ordering": false,
             "searching": false,
             "serverSide": true,
             "ajax": {
                 "url": "goodsList"
             },
             "dataType": "json",
             "aLengthMenu": [10, 20, 30],
             "columns": [
                 {"data": "skuCode"},
                 {"data": "url"},
                 {"data": "createTime"},
                 {"data": "c","render":function( data, type, row ) {
	                	return '<input class="btn btn-primary radius" onClick="preview(\''+row.id+'\')" type="button" value="爬取">';
	                }}
             ]
         });
		
	}

	self.init();
	
}

