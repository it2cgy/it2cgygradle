$(function(){
	  FormValidation.init();
});


function onSubmit(){
	var formData = $("#mailForm").serializeArray();
	var paramObj ={};
	for(var i in formData){
		paramObj[formData[i].name]=formData[i].value;
	}
	ynzAjax.post(
			window.ynz.path+"/mailConfig/updateMail.do",
			paramObj,
			function(response){ 
				message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
					if(response.data==true){
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
            var form_1 = $('#mailForm');
            var error3 = $('.alert-danger', form_1);
            var success3 = $('.alert-success', form_1);
            form_1.validate({
                errorElement: 'em', 
                errorClass: 'help-block help-block-error',
                focusInvalid: false, 
                ignore: "", 
                rules: {
                	serverPath: {
                        required: true
                    }, 
                    mailType: {
                        required: true
                    },
                    mailPort: {
                        required: true
                    },
                    mailUsername: {
                        required: true
                    } ,
                    mailPassword: {
                        required: true
                    } ,
                    mailAddress: {
                        required: true
                    } ,
                    pushTime: {
                        required: true
                    } 
                },

                messages: {
                	
                	serverPath: {
                        required: "请输入邮件服务器地址！"
                    }, 
                    mailType: {
                        required: "请输入服务器类型！"
                    },
                    mailPort: {
                        required: "请输入服务器端口！"
                    },
                    mailUsername: {
                        required: "请输入登录用户名！"
                    } ,
                    mailPassword: {
                        required: "请输入登录密码！"
                    } ,
                    mailAddress: {
                        required: "请输入邮箱地址！"
                    } ,
                    pushTime: {
                        required: "请选择推送间隔时间！"
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
