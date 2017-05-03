/**
 * 用户角色
 */

function roleConfig(){
	
	var self=this;
	var jstree;
	var mTable;

	this.init=function(){
		
		mTable = $('#roleList').DataTable({
            "processing": true,
            "ordering": false,
            "searching": false,
            "serverSide": false,
            "bPaginate": false, 
            "dataType": "json",
            "columns": [
                        {"data": "title"},
                        {"data": "n","render":function( data, type, row ) {
			                if(row.status == 0){
			                	return "<span class=\"label label-success radius\">有效</span>";
			                }else{
			                	return "<span class=\"label label-warning radius\">冻结</span>";
			                }
			              }},
			            {"data": "createTime"},
			            {"data": "c","render":function( data, type, row ) {
		                	return '<input class="btn radius btn-secondary" onClick="settingRole(\''+row.title+'\',\''+row.id+'\')" type="button" value="分配权限">';
		                }}
            ]
        });
		
		self.roleList()
		
		$('#adding').bind('click',function(){
			$("#createDialog").modal("show");
		});
		
		$('#addRoleBtn').bind('click',function(){
			self.saveRole();
		});
		
		$('#saveRoleMenuBtn').bind('click',function(){
			var nodes=$("#allMenu").jstree("get_checked");
			console.log(nodes);
			
			if(nodes == null || nodes.length < 0){
				layer.msg('请选择菜单！', {icon: 7});
				return;
			}
			var ids = []; 
			for(var j = 0;j < nodes.length;j++){
				ids.push(nodes[j]);
			}
			$.post("saveRoleMenu",{"ids":ids,"groupId":$("#groupId").val()}, function(data) {
				if(data.success){
					layer.msg('配置成功！', {icon: 1});
					$("#settingRoleMenuDialog").modal("hide");
				}else{
					layer.msg('配置失败！', {icon: 7});
				}
			});
		});
		
	}
	
	this.roleList=function(){
		$("#roleList").DataTable().ajax.url("getRoleList").load();
	}
	
	this.saveRole=function(){
		var parm = {};
		
		var title = $.trim($("#title").val());
		
		if(title==null || title==""){
			layer.msg('角色名称不能为空！', {icon: 7});
			return;
		}
		
		parm.title = title;
		
		$.ajax({
			url:"saveRole",
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
					$("#createDialog").modal("hide");
					self.roleList()
					layer.msg('保存成功！', {icon: 1});
				}else{
					layer.msg('出错了！', {icon: 2});
				}
			}
		});
	}

	self.init();
	
}

function settingRole(title,id){
	$('#groupMenu').data('jstree', false).empty();
	$('#allMenu').data('jstree', false).empty();
	$("#settingRoleMenuDialog").modal("show");
	$("#groupName").text("设置角色对应菜单【"+title+"】");
	$("#groupId").val(id);
	initRoleMenu(id);
	allMenu(id);
}

function initRoleMenu(id){
	 $.ajax({  
       type : "POST",  
       dataType : 'json',  
       url : "roleMenuList",  
       data:{"roleId":id},
       error : function(data) {  
      	 layer.msg('获取菜单失败！', {icon: 5});
       },  
       success : function(data) { 
      	 jstree = $('#groupMenu').jstree({
   			'core' : {
   				'data' : data,
   				'state':{"opened":true}
   			}
   		}).bind("select_node.jstree", function(e, data) {
   			console.log(data);
          }).bind("ready.jstree", function(e, data) {
        	  initGroupIcon(id);
   			$('#groupMenu').jstree().open_all();
          }).bind("open_node.jstree", function(e, data) {
        	  initGroupIcon(id);
          });
       }  
   });
}

function initGroupIcon(id){
	$.post("roleMenuList",{"roleId":id}, function(data) {
		var children;
		for(var i = 0;i < data.length;i++){
			$("#groupMenu #"+data[i].id+"_anchor i").replaceWith(data[i].icon); 
			children = data[i].children;
			if(children.length > 0){
				for(var j = 0;j < children.length;j++){
					$("#groupMenu #"+children[j].id+"_anchor i").replaceWith(children[j].icon); 
				}
			}
		}
	});
}


function allMenu(id){
	 $.ajax({  
	      type : "POST",  
	      dataType : 'json',  
	      url : "getAllMenu",  
	      data:{"roleId":id},
	      error : function(data) {  
	     	 layer.msg('获取菜单失败！', {icon: 5});
	      },  
	      success : function(data) { 
	     	 jstree = $('#allMenu').jstree({
	  			'core' : {
	  				'data' : data,
	  				'state':{"opened":true}
	  			},
	  			"plugins" : ["checkbox"]
	  		}).bind("ready.jstree", function(e, data) {
	        	 initAllIcon(id);
	  			$('#allMenu').jstree().open_all();
	         }).bind("open_node.jstree", function(e, data) {
	        	 initAllIcon(id);
	         });
	      }  
  });
}

function initAllIcon(id){
	$.post("getAllMenu",{"roleId":id}, function(data) {
		var children;
		for(var i = 0;i < data.length;i++){
			$("#allMenu #"+data[i].id+"_anchor i").eq(1).replaceWith(data[i].icon); 
			children = data[i].children;
			if(children.length > 0){
				for(var j = 0;j < children.length;j++){
					$("#allMenu #"+children[j].id+"_anchor i").eq(1).replaceWith(children[j].icon); 
				}
			}
		}
	});
}
