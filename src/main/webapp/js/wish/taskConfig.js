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
			            	return '<input class="btn btn-success-outline radius" onClick="transactionTask(\''+row.id+'\')" type="button" value="办理任务">&nbsp;&nbsp;'+
			            	'<input class="btn btn-secondary-outline radius" onClick="showImg(\''+row.id+'\')" type="button" value="查看">';
			            }}
			            ]
		});
		
	}
	
	self.init();
	
}

//查看图片
function showImg(id){
	$.post("viewCurrentImage",{"taskId":id}, function(res) {
		if(res.status == 1){
			$("#processImg").attr("src","viewImage?deploymentId="+res.data.deploymentId+"&imageName="+res.data.imageName+"");
			$("#taskNode").attr("style","position: absolute;border:1px solid red;top:"+res.data.acs.y+"px;left:"+res.data.acs.x+"px;width:"+res.data.acs.width+"px;height:"+res.data.acs.height+"px;");
			$("#showImgDialog").modal("show");
		}else{
			layer.msg('程序异常！', {icon: 2});
		}
	 });
}

//办理任务
function transactionTask(id){
	$.post("viewTaskForm",{"taskId":id}, function(res) {
		console.log(res);
		if(res.success){
			window.location.reload(true);
		}
	 });
}

