$(function(){
	 if (jQuery().datepicker) {
         $('.date-picker').datepicker({
             rtl: App.isRTL(),
             orientation: "left",
             autoclose: true
         });
     }
	setSelectMenuRoot("menustationmanager");
    FormValidation.init();
});

function onSubmit(){
	var formData = $("#powerStationForm").serializeArray();
	var paramObj ={};
	paramObj.picUrl="";
	for(var i in formData){
		paramObj[formData[i].name]=formData[i].value;
	}
	var picUrl = $("#faultImg").attr("src");
	if(picUrl!=""){
		paramObj.picUrl = picUrl
	}
	ynzAjax.post(
			window.ynz.path+"/powerStation/addPowerstation.do",
			paramObj,
			function(response){ 
				message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
					if(response.data==true){
						var param ="?a=1";
					 	if(window.ynz.admin){
					 		param  +="&admin=1";
					 	}
					 	
					 	if(window.ynz.longieb.powerStationId>0){
					 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
					 	}
					 	
						window.location.href=window.ynz.path+"/powerstation/powerstationList"+param;
					}
				});
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
	    );
}
var FormValidation = function () {
    var handleValidation1 = function() {
            var form_1 = $('#powerStationForm');
            var error3 = $('.alert-danger', form_1);
            var success3 = $('.alert-success', form_1);
            form_1.validate({
                errorElement: 'em', 
                errorClass: 'help-block help-block-error',
                focusInvalid: false, 
                ignore: "", 
                rules: {
                	powerStationName: {
                        required: true
                    }, 
                    powerstationCode: {
                        required: true
                    },
                    installCapacity: {
                        required: true
                    },
                    lng: {
                        required: true
                    } ,
                    lat: {
                        required: true
                    } ,
                    province: {
                        required: true
                    } ,
                    city: {
                        required: true
                    } ,
                    county: {
                        required: true
                    } ,
                    occupation: {
                        required: true
                    } 
                    ,
                    investFirmContactName: {
                        required: true
                    } 
                    ,
                    investFirmName: {
                        required: true
                    } 
                },

                messages: { 
                	powerStationName: {
                        required: window.ynz.local.powerstation_please_name
                    }, 
                    powerstationCode: {
                        required: window.ynz.local.powerstation_please_number
                    },
                    installCapacity: {
                        required: window.ynz.local.powerstation_please_capacity
                    },
                    lng: {
                        required: window.ynz.local.powerstation_please_lng
                    } ,
                    lat: {
                        required: window.ynz.local.powerstation_please_lag
                    } ,
                    province: {
                        required: window.ynz.local.powerstation_please_province
                    } ,
                    city: {
                        required: window.ynz.local.powerstation_please_city
                    } ,
                    county: {
                        required: window.ynz.local.powerstation_please_county
                    } ,
                    occupation: {
                        required: window.ynz.local.powerstation_please_address
                    } 
                    ,
                    investFirmContactName: {
                        required:  window.ynz.local.powerstation_please_manager
                    } 
                    ,
                    investFirmName: {
                        required: window.ynz.local.powerstation_please_investment
                    } 
                },

                errorPlacement: function (error, element) { // render error placement for each input type
                    if (element.parent(".input-group").size() > 0) {
                        error.insertAfter(element.parent(".input-group"));
                    } else if (element.attr("data-error-container")) { 
                        error.appendTo(element.attr("data-error-container"));
                    } else if (element.parents('.radio-list').size() > 0) { 
                        error.appendTo(element.parents('.radio-list').attr("data-error-container"));
                    } else if (element.parents('.radio-inline').size() > 0) { 
                        error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
                    } else if (element.parents('.checkbox-list').size() > 0) {
                        error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
                    } else if (element.parents('.checkbox-inline').size() > 0) { 
                        error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
                    } else {
                        error.insertAfter(element); // for other inputs, just perform default behavior
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success3.hide();
                    error3.show();
                    App.scrollTo(error3, -200);
                },

                highlight: function (element) { // hightlight error inputs
                   $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },
                
                submitHandler: function (form) {
                    success3.show();
                    error3.hide();
                    onSubmit();
                }

            });
    	}
    return {
        init: function () {
            handleValidation1();
        }

    };

}();


/**
 * 上传电站图片
 */
function fileUpload(){
	$("#file").click();
}
function uploadFile(_obj){
	var form = $("#fileform"); 
	var file =$("#file").val();
	var arr = file.split("\\");
	var fileName = arr[arr.length-1]; 
	var AllImgExt = ".jpg|.jpeg|.gif|.bmp|.png|";
	var extName = file.substring(file.lastIndexOf(".")).toLowerCase();
	if(AllImgExt.indexOf(extName+"|")==-1){
		message.alert( window.ynz.local.tip,window.ynz.local.image_error,3,null,null);
		return false;
	}
	form.ajaxSubmit(function(respense){
		message.alert(window.ynz.local.tip,respense.message,3);
		$("#faultImg").attr("src",respense.url);
		$("#uploadDiv").hide();
		$("#fileDiv").show();
	});
}

/**
 * 删除图片
 * @param _obj
 */
function delFile(_obj){
	$("#faultImg").attr("src","");
	$("#fileDiv").hide();
	$("#uploadDiv").show();
	var file = $("#file");
	file.after(file.clone().val(""));
	file.remove();
}