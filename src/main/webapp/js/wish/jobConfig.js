/**
 * 动态任务调度管理
 */

function jobConfig(){
	
	var self=this;
	var mTable;

	this.init=function(){
		
		self.jobList();
		
		$('#updateCronBtn').bind('click',function(){
			var cron = $("#cron").val();
			var jobId = $("#jobId").val();
			
			$.post("updateCron", {"jobId":jobId,"cron":cron}, function(data, status) {
				console.log(data);
				if(data.success){
					$("#updateDialog").modal("hide");
					mTable.ajax.reload();
					layer.msg('修改成功！', {icon: 1});
				}else{
					layer.msg(data.errorMsg, {icon: 5});
				}
			});
		});
		
		$('#adding').bind('click',function(){
			$("#addJobDialog").modal("show");
		});
		
		$('#addjobBtn').bind('click',function(){
			self.saveJob();
		});
		
	}
	
	this.jobList=function(){
		
		 mTable = $('#jobList').DataTable({
			 "processing": true,
             "ordering": false,
             "searching": false,
             "serverSide": true,
             "ajax": {
                 "url": "jobList"
             },
             "dataType": "json",
             "columns": [
                 {"data": "jobName"},
                 {"data": "jobGroup"},
                 {"data": "n","render":function( data, type, row ) {
 	                if(row.jobStatus == 0){
 	                	return "<span class=\"label label-warning radius\">停止</span>";
 	                }else{
 	                	return "<span class=\"label label-success radius\">运行</span>";
 	                }
 	              }},
                 {"data": "cronExpression"},
                 {"data": "beanClass"},
                 {"data": "springId"},
                 {"data": "methodName"},
                 {"data": "description"},
                 {"data": "createTime"},
                 {"data": "updateTime"},
                 {"data": "c","render":function( data, type, row ) {
                	 if(row.jobStatus == 0){
  	                	return '<input class="btn btn-primary radius" onClick="openJob(\''+row.jobId+'\',\'start\')" type="button" value="开启">&nbsp;&nbsp;'+
  	                	'<input class="btn btn-secondary radius" onClick="updateCron(\''+row.jobId+'\',\''+row.cronExpression+'\')" type="button" value="修改CRON">&nbsp;&nbsp;'+
  	                	'<input class="	btn btn-warning radius" onClick="del(\''+row.jobId+'\')" type="button" value="删除">';
  	                }else{
  	                	return '<input class="btn btn-warning radius" onClick="openJob(\''+row.jobId+'\',\'stop\')" type="button" value="停止">&nbsp;&nbsp;'+
  	                	'<input class="btn btn-secondary radius" onClick="updateCron(\''+row.jobId+'\',\''+row.cronExpression+'\')" type="button" value="修改CRON">&nbsp;&nbsp;'+
  	                	'<input class="	btn btn-warning radius" onClick="del(\''+row.jobId+'\')" type="button" value="删除">';
  	                }
	                }}
             ]
         });
		
	}
	
	this.saveJob=function(){
		var parm = {};
		
		var jobName = $.trim($("#jobName").val());
		var jobGroup = $.trim($("#jobGroup").val());
		var cronExpression = $.trim($("#cronExpression").val());
		var beanClass = $.trim($("#beanClass").val());
		var springId = $.trim($("#springId").val());
		var methodName = $.trim($("#methodName").val());
		var description = $.trim($("#description").val());
		
		if(jobName==null || jobName==""){
			layer.msg('任务名称不能为空！', {icon: 7});
			return;
		}
		
		if(jobGroup==null || jobGroup==""){
			layer.msg('任务分组不能为空！', {icon: 7});
			return;
		}
		
		if(cronExpression==null || cronExpression==""){
			layer.msg('CRON表达式不能为空！', {icon: 7});
			return;
		}
		
		if(methodName==null || methodName==""){
			layer.msg('方法名称不能为空！', {icon: 7});
			return;
		}
		
		parm.jobName = jobName;
		parm.jobGroup = jobGroup;
		parm.cronExpression = cronExpression;
		parm.beanClass = beanClass;
		parm.springId = springId;
		parm.methodName = methodName;
		parm.description = description;
		parm.jobStatus = 0;
		parm.isConcurrent = 0;
		
		$.ajax({
			url:"add",
            type: "POST",
            dataType: "json",//跨域ajax请求,返回数据格式为json
            cache: false,
            timeout: 10000,//请求超时时间,单位为毫秒
            async: true,
            global: false,//禁用Jquery全局事件
            scriptCharset: 'UTF-8',
            contentType: 'application/json;charset=UTF-8',//请求内容的MIMEType
			data:JSON.stringify(parm),
			success:function(responseData, status){
				if(responseData.success){
					$("#addJobDialog").modal("hide");
					mTable.ajax.reload();
					layer.msg('保存成功！', {icon: 1});
				}else{
					layer.msg('出错了！', {icon: 2});
				}
			}
		});
	}

	self.init();
	
}

function openJob(jobId,cmd){
	$.post("changeJobStatus", {"jobId":jobId,"cmd":cmd}, function(data, status) {
		if(data.success){
			if(cmd == "stop"){
				layer.msg('定时任务已关闭！', {icon: 2});
			}else{
				layer.msg('定时任务已开启！', {icon: 2});
			}
			location.reload();
		}else{
			layer.msg('程序异常！', {icon: 5});
		}
	});
}

function updateCron(jobId,cronExpression){
	$("#updateDialog").modal("show");
	$("#jobId").val(jobId);
	$("#cron").val(cronExpression);
}

function del(jobId){
	
	layer.confirm('确定要删除吗？', {
		  btn: ['确定','取消']
	}, function(){
		$.post("delJob", {"jobId":jobId}, function(data, status) {
			if(data.success){
				layer.msg('删除成功！', {icon: 2});
				location.reload();
			}else{
				layer.msg('程序异常！', {icon: 5});
			}
		});
	}, function(){
		  
	});
	
	
}
