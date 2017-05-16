/**
 * 流程列表管理
 */

function processConfig(){
	
	var self=this;
	var mTable;
	
	var BpmnViewer = window.BpmnJS;
	var viewer = new BpmnViewer({ container: '#canvas' });
	var xhr = new XMLHttpRequest();

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
		                {"data": "diagramResourceName"}
		              ]
		});
		
		$("#processList").on("click","tr",function(){
			var data=myTable.row(this).data();

			/*xhr.onreadystatechange = function() {
			    if (xhr.readyState === 4) {
			        viewer.importXML(xhr.response, function(err) {
			          if (!err) {
			            console.log('success!');
			            viewer.get('canvas').zoom('fit-viewport');
			          } else {
			            console.log('something went wrong:', err);
			          }
			        });
			    }
			};*/

			xhr.open('GET', 'test.bpmn', true);
			xhr.send(null);
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
		
	}
	
	this.jobList=function(){}
	
	self.init();
	
}

