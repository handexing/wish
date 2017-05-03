/**
 * 二维码
 */

function zxingConfig(){
	
	var self=this;

	this.init=function(){
		
		$('#okBtn').bind('click',function(){
			var content = $.trim($("#content").val());
			var obj=document.getElementById("qrcode");    
	        obj.src = "createQrcode?content=" + content;   
		});
	}

	self.init();
}

