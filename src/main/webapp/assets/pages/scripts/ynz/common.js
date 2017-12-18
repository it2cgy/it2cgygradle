$(function(){
	 
	function setShowType(localType){
		if(localType=="en_US"){
			$("#chinese").css("color","#646b79");
			$("#english").css("color","#fff");
		}else{
			$("#chinese").css("color","#fff");
			$("#english").css("color","#646b79");
		}
	}
	
	setShowType(window.ynz.localType);
	
	//公共头部中英文切换
	$("#chinese").click(function(){
		setShowType("zh_CN");
		var url = window.ynz.basePath + "global/local/zh_CN";
		ynzAjax.get(
				url, 
	    		function(response){ 
	    			 window.location.reload(); 
		        },
		        function(e){ 
		            console.log("--------error------"+e);
		        }
	    	);
	});
	//安卓、ios按钮切换
	$(".phoneChange li").click(function(){
		$(this).addClass("activePhoneChange").siblings().removeClass("activePhoneChange");
	});
	$("#english").click(function(){ 
		setShowType("en_US");
		var url = window.ynz.basePath + "global/local/en_US";
		ynzAjax.get(
				url,
	    		function(response){ 
	    			 window.location.reload(); 
		        },
		        function(e){ 
		            console.log("--------error------"+e);
		        }
	    	);
	});
	
	//分页切换
	$(".page .pageNum").click(function(){
		$(this).addClass("activeNum").siblings().removeClass("activeNum");
	})
})