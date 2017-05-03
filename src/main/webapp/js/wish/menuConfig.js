/**
 * menu page js
 */

function menuConfig(){
	
	var self=this;
	var userId;
	var jstree;

	this.init=function(){
		
		self.initMenu();
		
		$('#createMenuBtn').bind('click',function(){
			self.saveMenu();
		});
		
		$('#updateMenuBtn').bind('click',function(){
			self.updateMenu();
		});
		
	}
	
	this.initMenu=function(){
		
		 $.ajax({  
             type : "POST",  
             dataType : 'json',  
             url : "menuList",  
             error : function(data) {  
            	 layer.msg('获取菜单失败！', {icon: 5});
             },  
             success : function(data) {  
            	 jstree = $('#menu').jstree({
         			'core' : {
         				'data' : data,
         				'state':{"opened":true}
         			},
         			"plugins" : [ "contextmenu" ],
         			 contextmenu: {items: context_menu},
         		}).bind("select_node.jstree", function(e, data) {
         			console.log(data);
         			$("#id").val(data.node.id);
         			$("#titleTxt").val(data.node.original.title);
         			$("#hrefTxt").val(data.node.original.href);
         			$("#hrefTxt").val(data.node.original.href);
         			
         			$("#parentId").val(data.selected[0]);
         			$("#isParent").val(data.node.original.pid);
                }).bind("ready.jstree", function(e, data) {
         			self.initIcon();
         			$('#menu').jstree().open_all();
                }).bind("open_node.jstree", function(e, data) {
         			self.initIcon();
                });
             }  
         });
		 
	}
	
	this.initIcon=function(){
		$.post("menuList",{}, function(data) {
    		var children;
    		for(var i = 0;i < data.length;i++){
    			$("#"+data[i].id+"_anchor i").replaceWith(data[i].icon); 
    			children = data[i].children;
    			if(children.length > 0){
    				for(var j = 0;j < children.length;j++){
    					$("#"+children[j].id+"_anchor i").replaceWith(children[j].icon); 
    				}
    			}
    		}
    	});
	}
	
	this.saveMenu=function(){
		
		var parm = {};
		var isParent = $("#isParent").val();
		
		if(isParent != 0){
			layer.msg('请选择一级菜单级菜单！', {icon: 5});
			return;
		}
		
		var pid = $("#parentId").val();
		var title = $.trim($("#title").val());
		var href = $.trim($("#href").val());
		var icon = $.trim($("#icon").val());
		
		if(title==null || title==""){
			layer.msg('标题不能为空！', {icon: 7});
			return;
		}
		
		if(pid==null || pid==""){
			parm.pid = 0;
		}else{
			parm.pid = pid;
		}
		
		//parm.id = $("#id").val();
		parm.title = title;
		parm.href = href;
		parm.icon = icon;
		
		$.ajax({
			url:"saveMenu",
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
					window.location.reload(true);
					layer.msg('保存成功！', {icon: 1});
				}else{
					$.messager.alert('提示','出错了');
				}
			}
		});
	}
	
	this.updateMenu=function(){
		var parm = {};
		
		var title = $.trim($("#titleTxt").val());
		var href = $.trim($("#hrefTxt").val());
		
		if(title==null || title==""){
			layer.msg('标题不能为空！', {icon: 7});
			return;
		}
		
		parm.id = $("#id").val();
		parm.title = title;
		parm.href = href;
		parm.icon = $.trim($("#icon").val());;
		
		$.ajax({
			url:"saveMenu",
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
					$("#editDialog").modal("hide");
					layer.msg('修改成功！', {icon: 1});
					var text = $("#"+$("#id").val()+"_anchor").html(); 
					var arrs = text.split("</i>");
					arrs[1]=title;
					$("#"+$("#id").val()+"_anchor").html(arrs); 
				}else{
					$.messager.alert('提示','出错了');
				}
			}
		});
	}
	
	self.init();
	
}


function context_menu(node){
	 var tree = $('#menu').jstree(true);
	    var items = {
	        "Edit": {
	            "separator_before": false,
	            "separator_after": false,
	            "label": "编辑",
	            "action": function (obj) {
	            	$("#editDialog").modal("show");
	            }
	        },                         
	        "Remove": {
	            "separator_before": true,
	            "separator_after": false,
	            "label": "删除",
	            "action": function (obj) { 
	            	console.log(node);
	            	
	            	if(node.children.length > 0){
	            		layer.msg('包含子菜单！', {icon: 5});
	            		return;
	            	}
	            	
	            	layer.confirm('确定要删除吗？', {
	          		  btn: ['确定','取消']
		          	}, function(){
		          		$.post("delMenu",{"id":node.id}, function(data) {
		        			if(data.success){
		        				window.location.reload(true);
		        				layer.msg('删除成功！', {icon: 1});
		        			}else{
		        				layer.msg('删除失败！', {icon: 7});
		        			}
		        		});
		          		
		          	}, function(){
		          	});
	            }
	        }
	    };
	return items;
}


