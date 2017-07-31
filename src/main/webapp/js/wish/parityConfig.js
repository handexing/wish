/**
 * 商品比价管理
 */

function parityConfig(){
	
	var self=this;
	var mTable;
	var skuInfoTable;
	var myChart;

	this.init=function(){
		
		mTable = $('#parityList').DataTable({
            "processing": true,
            "ordering": false,
            "searching": false,
            "serverSide": true,
            "ajax": {
                "url": "goodsList"
            },
            "dataType": "json",
            "aLengthMenu": [10, 20, 30],
            "columns": [
                {"data": "c","render":function( data, type, row ) {
                        	console.log(row);
                        	if(row.platfmCode == 1){
                        		return "京东";
                        	}else if(row.platfmCode == 2){
                        		return "淘宝";
                        	}else if(row.platfmCode == 3){
                        		return "亚马逊";
                        	}
                        }},
                {"data": "skuCode"},
                {"data": "url"},
                {"data": "createTime"}
            ]
        });
		
		
		
		$('#adding').bind('click',function(){
			$("#createDialog").modal("show");
		});
		
		$('#savaSkuSrcBtn').bind('click',function(){
			var skuCode = $.trim($("#skuCode").val());
			var url = $.trim($("#url").val());
			var platfmCode = $.trim($("#platfmCode").val());
			
			if(skuCode==null || skuCode==""){
				layer.msg('skuCode不能为空！', {icon: 7});
				return;
			}
			
			if(url==null || url==""){
				layer.msg('URL不能为空！', {icon: 7});
				return;
			}
			
			if(platfmCode==null || platfmCode==""){
				layer.msg('平台不能为空！', {icon: 7});
				return;
			}
			
			$.post("createSkuSrc", {"skuCode":skuCode,"url":url,"platfmCode":platfmCode}, function(data, status) {
				if(data.success){
					mTable.ajax.reload();
					layer.msg('保存成功！', {icon: 1});
					$("#createDialog").modal("hide");
				}else{
					layer.msg('保存失败！', {icon: 5});
				}
			});
			
		});
		
		skuInfoTable = $('#skuInfoList').DataTable({
			"processing": true,
			"ordering": false,
			"searching": false,
			"serverSide": true,
			"ajax": {
				"url": "skuInfoList"
			},
			"dataType": "json",
			"aLengthMenu": [10, 20, 30],
			"columns": [
			            {"data": "title"},
			            {"data": "subtitle"},
			            {"data": "price"},
			            {"data": "dateId"}
			        ]
		});
		$("#skuInfoList").on("click","tr",function(){
			var data=skuInfoTable.row(this).data();
			if(data){
				
				$("#skuInfoDialog").modal("show");
				myChart = echarts.init(document.getElementById('main'));
				
				myChart.setOption({
		        	title: {
		        		text: data.title
		        	},
		        	tooltip: {
						trigger: 'axis'
					},
					grid: {
						left: '3%',
						right: '4%',
						bottom: '3%',
						containLabel: true
					},
					xAxis: {
						type: 'category',
						boundaryGap: false,
						data: []
					},
					yAxis: {
						type: 'value'
					},
		        	series: [{
		        		name: '价格',
		        		type: 'line',
		        		data: []
		        	}]
		        });
				
				$.post("getSkuInfoList", {"skuSrcId":data.skuSrcId}, function(data, status) {
					if(data.success){
						console.log(data.data);
						myChart.setOption({
						    xAxis: {
						        data: data.data.categories
						    },
					        series: [{
					            data: data.data.data
					        }]
					    });
					}else{
						layer.msg('获取失败！', {icon: 5});
						myChart.hideLoading();   
					}
				});
			}
		});
		
	}
	
	self.init();
	
}

