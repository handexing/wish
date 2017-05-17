/**
 * 请假列表
 */

function leaveBillConfig(){
	
	var self=this;
	var mTable;

	this.init=function(){
		
		myTable=$('#leaveBillList').DataTable({
			 "processing": true,
			 "ordering": false,
			 "searching":false,
		     "serverSide": true,
		     "ajax": {
		        "url":"leaveBillList"
			 },
		     "dataType":"json",
		     "aLengthMenu": [10, 20, 30],
		     "columns": [
		                {"data": "c","render":function( data, type, row ) {
		                	return row.user.name;
		                }},
		                {"data": "days"},
		                {"data": "leaveDate"},
		                {"data": "content"},
		                {"data": "state"},
		                {"data": "remark"}
		              ]
		});
		
		$('#adding').bind('click',function(){
			$("#addLeaveBillDialog").modal("show");
			$('#myform')[0].reset();
		});
		
		$('#myform').ajaxForm({
		    success: function(data) {
		    	console.log(data);
		    	if(data.success){
		    		$("#addLeaveBillDialog").modal("hide");
    				$('#myform')[0].reset();
		    		layer.msg('添加完成！', {icon: 1});
		    	}else{
		    		layer.msg('添加失败！', {icon: 2});
		    	}
		    },
		    error:function(){
		    	layer.msg('添加失败！', {icon: 2});
		    }
		});
		
	}
	
	self.init();
	
}

