/**
 * 正在运行的流程
 */

function runnintProcessConfig(){
	
	var self=this;
	var mTable;
	
	this.init=function(){
		
		mTable=$('#runProcessList').DataTable({
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
		                {"data": "processDefinitionKey"},
						{"data": "processDefinitionName"}
		              ]
		});
		
	}
	
	self.init();
	
}
