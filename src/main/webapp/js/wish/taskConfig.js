/**
 * 任务管理
 */

function taskConfig(){
	
	var self=this;
	var mTable;

	this.init=function(){
		
		mTable=$('#taskList').DataTable({
			"processing": true,
			"ordering": false,
			"searching":false,
			"serverSide":false,
			"bPaginate": false, //开关，是否显示分页器
			"ajax": {
				"url":"listTask "
			},
			"dataType":"json",
			"columns": [
			            {"data": "id"},
			            {"data": "name"},
			            {"data": "createTime"},
			            {"data": "assignee"},
			            {"data": "c","render":function( data, type, row ) {
			            	return '<input class="btn btn-success-outline radius" onClick="del(\''+row.deploymentId+'\')" type="button" value="办理任务">&nbsp;&nbsp;'+
			            	'<input class="btn btn-secondary-outline radius" onClick="del(\''+row.deploymentId+'\')" type="button" value="查看">';
			            }}
			            ]
		});
		
	}
	
	self.init();
	
}

