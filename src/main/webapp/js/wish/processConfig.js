/**
 * 流程列表管理
 */

function processConfig(){
	
	var self=this;
	var mTable;
	var runningProcessTable;
	
	this.init=function(){
		
		myTable=$('#processList').DataTable({
			 "processing": true,
			 "ordering": false,
			 "searching":false,
		     "serverSide": true,
		     "ajax": {
		        "url":"listProcess"
			 },
		     "dataType":"json",
		     "aLengthMenu": [10, 20, 30],
		     "columns": [
		                {"data": "name"},
		                {"data": "version"},
		                {"data": "resourceName"},
		                {"data": "diagramResourceName"},
		                {"data": "c","render":function( data, type, row ) {
		                	return '<input class="btn btn-danger radius" onClick="del(\''+row.deploymentId+'\')" type="button" value="删除">';
		                }}
		              ]
		});
		
		$("#processList").on("click","tr",function(){
			var data=myTable.row(this).data();
			 $.get("getBpmnSource",{id:data.id}, function(res) {
	    		 process.importXML(res);
	    		 $("#openProcessDialog").modal("show");
	    	 });
		});
		
		$('#adding').bind('click',function(){
			$("#addProcessDialog").modal("show");
			$('#myform')[0].reset();
		});
		
		$('#myform').ajaxForm({
		    success: function(data) {
		    	var sessionVal = data.uuid;
		    	$("#msession").val(sessionVal);
		    	if(data.isExist == true){
		    		layer.confirm('已经存在，是否继续上传更新？', {
		    			btn: ['继续','取消']
		    		}, function(){
		    			$.post("deployProcess",{"uuid":sessionVal}, function(res) {
		    				myTable.ajax.reload();
		    				layer.msg('更新完成！', {icon: 1});
		    				$("#addProcessDialog").modal("hide");
		    				$('#myform')[0].reset();
				    	});
		    		}, function(){
		    			$.post("cancelDeploy",{"uuid":sessionVal}, function(res) {
				    	});
		    		});
		    	} else {
		    		$("#addProcessDialog").modal("hide");
		    		myTable.ajax.reload();
		    		layer.msg('添加完成！', {icon: 1});
		    	}
		    },
		    error:function(){
		    	layer.msg('添加失败！', {icon: 2});
		    }
		});
		
		
		runningProcessTable=$('#runProcessList').DataTable({
			 "processing": true,
			 "ordering": false,
			 "searching":false,
		     "serverSide": true,
		     "ajax": {
		        "url":"runningProcessList"
			 },
		     "dataType":"json",
		     "aLengthMenu": [10, 20, 30],
		     "columns": [
		                {"data": "name"},
		                {"data": "version"},
		                {"data": "resourceName"},
		                {"data": "diagramResourceName"},
		              ]
		});
		
	}
	
	self.init();
	
}

function del(id){
	$.get("delProcess",{id:id}, function(res) {
		console.log(res);
		window.location.reload(true);
	 });
}
