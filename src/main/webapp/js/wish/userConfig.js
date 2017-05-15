/**
 * user page js
 */

function userConfig(){
	
	var self=this;
	var userId;
	var jstree;
	var mTable;

	this.init=function(){
		
		self.userList();
		
		$('#search').bind('click',function(){
			mTable.ajax.reload();
		});
		
		$('#addUserBtn').bind('click',function(){
			self.addUser();
		});
		
		$('#adding').bind('click',function(){
			$("#createDialog").modal("show");
			$.get("getRoleList", {}, function(data, status) {
				console.log(data);
				$('#m_role').text("");
				$.each(data, function(index, itemobj) {
					var option = "<option value="+data[index].id+">"+data[index].title+"</option>";
					$('#m_role').append(option);
				});
			});
		});
		
		$('#updateUserBtn').bind('click',function(){
			self.updateUser();
		});
		
		$('#exportData').bind('click',function(){
			window.location.href="exportData";
		});
		
	}
	
	this.addUser=function(){

		var name = $.trim($("#name").val());
		var account = $.trim($("#m_account").val());
		var roleId = $.trim($("#m_role").val());
		var phone = $.trim($("#phone").val());
		
		if(name==null || name==""){
			layer.msg('昵称不能为空！', {icon: 7});
			return;
		}
		
		if(account==null || account==""){
			layer.msg('登陆账号不能为空！', {icon: 7});
			return;
		}
		
		if(phone==null || phone==""){
			layer.msg('联系方式不能为空！', {icon: 7});
			return;
		}
		
		$.post("addUser",{"name":name,"account":account,"roleId":roleId,"phone":phone}, function(data) {
			if(data.success){
				mTable.ajax.reload();
				layer.msg('添加成功！', {icon: 1});
				$("#createDialog").modal("hide");
			}else{
				layer.msg('添加失败！', {icon: 7});
			}
		});
	}
	
	this.updateUser=function(){

		var userName = $.trim($("#userName").val());
		var roleId = $.trim($("#roleList").val());
		var userId = $("#userId").val();
		
		if(userName==null || userName==""){
			layer.msg('昵称不能为空！', {icon: 7});
			return;
		}
		
		$.post("updateUser",{"id":userId,"name":userName,"roleId":roleId}, function(data) {
			if(data.success){
				mTable.ajax.reload();
				layer.msg('修改成功！', {icon: 1});
				$("#updateDialog").modal("hide");
			}else{
				layer.msg('修改失败！', {icon: 7});
			}
		});
	}
	
	this.userList=function(){
		
		 mTable = $('#userList').DataTable({
             "processing": true,
             "ordering": false,
             "searching": false,
             "serverSide": true,
             "ajax": {
                 "url": "userList",
                 "data": function (d) {
                     d.account = $('#account').val();
                 }
             },
             "dataType": "json",
             "aLengthMenu": [10, 20, 30],
             "columns": [
                 {"data": "name"},
                 {"data": "account"},
                 {"data": "phone"},
                 {"data": "n","render":function( data, type, row ) {
	                if(row.status == 0){
	                	return "<span class=\"label label-success radius\">有效</span>";
	                }else{
	                	return "<span class=\"label label-warning radius\">冻结</span>";
	                }
	              }},
                 {"data": "createTime"},
                 {"data": "c","render":function( data, type, row ) {
	                	return '<input class="btn btn-primary radius" onClick="updateUser(\''+row.name+'\',\''+row.id+'\',\''+row.roleUser.groupId+'\')" type="button" value="修改">&nbsp;&nbsp;';
	                }}
             ]
         });
		
	}

	self.init();
	
}

function updateUser(name,id,groupId){
	$("#updateDialog").modal("show");
	$("#modelTitle").text("修改用户【"+name+"】");
	$("#userName").val(name);
	$("#userId").val(id);
	getRoleList(groupId);
}

function getRoleList(groupId){
	$.get("getRoleList", {}, function(data, status) {
		$('#roleList').text("");
		$.each(data, function(index, itemobj) {
			if(groupId == data[index].id){
				var option = "<option value="+data[index].id+" selected>"+data[index].title+"</option>";
			}else{
				var option = "<option value="+data[index].id+">"+data[index].title+"</option>";
			}
			$('#roleList').append(option);
		});
		
	});
}
