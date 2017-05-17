//bpmn初始化
var bpmnModeler;

(function(BpmnModeler, $) {
	
  	bpmnModeler = new BpmnModeler({
    	container: '#canvas'
  	});
	
})(window.BpmnJS, window.jQuery);

//公共方法
var process={
	importXML:function(xml){
		$("#canvas > div:nth-child(2)").hide();
		$(".bjs-powered-by").hide();
		bpmnModeler.importXML(xml, function(err) {
	        if (err) {
	        	return console.error('could not import BPMN 2.0 diagram', err);
	        }else {
	        	var canvas = bpmnModeler.get('canvas');
	        }
	    });
	}
}