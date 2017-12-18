jQuery(document).ready(function() {
	setSelectMenuRoot("equipmentmanager");
    FormValidation.init();
    if($("#producedDate").val()!=null&&$("#producedDate").val()!=''&&$("#producedDate").val()!=' '){
    	$("#producedDate").val(timeUtil.timeToString($("#producedDate").val(),"yyyy-MM-dd"));
    }
    if($("#factoryDate").val()!=null&&$("#factoryDate").val()!=''&&$("#factoryDate").val()!=' '){
    	$("#factoryDate").val(timeUtil.timeToString($("#factoryDate").val(),"yyyy-MM-dd"));
    }
    getPowerstation();
    checkEquipmentNumber();
});
var i = 1;
function checkEquipmentNumber(){
	$('#equipmentNumber').blur(function() {
		if($(this).val()!=null&&$(this).val()!=''){
//			var flag = 0;
//			if($(this).val().contain("#")){
//				var number = $(this).val().split("#");
//				flag = 1;
//			}
			var url = window.ynz.basePath+"equipment/checkEquipmentNumber.do";
			ynzAjax.post(
					url,
					{
						'number':$(this).val(),
						'id':$("#id").val()
					},
					function(response){ 
						if(response.data>0){
							$("#waning").show();
							i=0;
						}else{
							$("#waning").hide();
							i=1;
						}
					},
					function(e){ 
						console.log("--------error------"+e);
					}
			);
		}
    });
}
function getPowerstation(){
	if(isSubStation){
		ynzAjax.get(
			window.ynz.path+"/powerStation/getPowerStationById/"+window.ynz.longieb.powerStationId+".do",
			function(response){ 
				var data = response.data;
				$("#powerstationid").append("<option value='"+data.id+"' selected>"+data.name+"</option>");
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
		);
	}else{
		ynzAjax.post(
			window.ynz.path+"/powerStation/getPowerStationBaseInfo.do",
			{
			},
			function(response){ 
				var data = response.data;
				for ( var i in data) {
					if(powerstationid == data[i].id){
						$("#powerstationid").append("<option value='"+data[i].id+"' selected>"+data[i].name+"</option>");
					}else{
						$("#powerstationid").append("<option value='"+data[i].id+"' >"+data[i].name+"</option>");
					}
				}
			},
			function(e){ 
				console.log("--------error------"+e);
			}
		);
	}
}
function fileUpload(row,ID){
	if(row!=null&&row!=''){
		var form = $("#fileform");
		var forma = $("#file")[0].value;
		forma = forma.replace(/\\/g,'/');
		forma = forma.substring(forma.lastIndexOf("/")+1,forma.length);
		var AllImgExt = ".jpg|.jpeg|.gif|.bmp|.png|";
		var extName = forma.substring(forma.lastIndexOf(".")).toLowerCase();
		if(AllImgExt.indexOf(extName+"|")==-1){
			message.alert( window.ynz.local.tip,window.ynz.local.image_error,3,null,null);
			return false;
		}
		form.ajaxSubmit(function(respense){
			console.log(JSON.stringify(respense));
			message.alert( window.ynz.local.tip,respense.message,3);
			$("#filenamepath"+ID).attr("alt",forma.substring(forma.lastIndexOf("/")+1,forma.length));
			$("#filenamepath"+ID).attr("title",forma.substring(forma.lastIndexOf("/")+1,forma.length));
			$("#filenamepath"+ID).attr("src",respense.url);
			$("#filenamepath"+ID).css('margin-top','22px');
			$("#remarks"+ID).val(forma);
			$("#butoon"+ID).css('margin-top','42px');
			$("#filepath"+ID).val(respense.url);
			$("#fileImg"+ID).show();
			$("#btnCamera"+ID).hide();
			$("#fileImg"+ID).attr("href",respense.url);
			$("#butoon").show();
		});
	}
}
function deleterow(row){
	$(row).parent().remove();//删除该行
}

var tempfileid=0;
function addFileButton(){
	var newid=tempfileid++;
	$("#filePortion").append( "<div class='form-group' id='filemodel'>"+
    "<div class='col-md-6 col-sm-6'>"+
    "<label class='control-label col-md-6'>"+
    "</label>"+
    "<div class='col-md-6' style='padding-left:30px;margin-top: 10px;'>"+
    window.ynz.local.equipment_file_name+"： <input type='text' id='filename' name='filename' data-required='1' class='form-control' style='margin-top:20px;' />"+
   " </div>"+
   "</div>"+
   " <input id='filepath"+newid+"' hidden name='filepath' class='filepath'>"+
   " <input id='remarks"+newid+"' hidden name='remarks' class='filepath'>"+
   "<a id='btnCamera"+newid+"' class='fileBtn borderRadius5' onclick='fileclick("+newid+")'>"+
   "<i class='fa fa-camera btnCamera'></i></a>" +
   "<button id='butoon"+newid+"' style='margin-left:10px;' type='button' class='btn green' onclick='deleterow(this)'>"+ window.ynz.local.del +"</button>"+    
   "<a href='' style='display:none' name='fileImg' id='fileImg"+newid+"'><img id='filenamepath"+ newid +"'  title='' style='width:70px;height:70px;margin-left:10px;'/></a>"+
	"</div>");
}
function fileclick(fileid){
	$("[name='file']").remove();
	$("#fileform").append("<input type='file' id='file' name='file'  accept='image/jpg,image/png,image/jpeg,image/jpeg' onchange='fileUpload(this,"+fileid+")'  />");
	$("#file").click();
}
function onSubmit(){
	var filename="";
	var filepath="";
	var remarks="";
	$("[name='filename']").each(function(){
		var value = $(this).val();
		if(value==null||value==''){
			value='0';
		}
		filename+=value+",";
	});
	$("[name='filepath']").each(function(){
		filepath+=$(this).val()+",";
	});
	$("[name='remarks']").each(function(){
		remarks+=$(this).val()+",";
	});
	remarks = remarks.substring(0,remarks.length-1);
	filename = filename.substring(0,filename.length-1);
	filepath = filepath.substring(0,filepath.length-1);
	ynzAjax.post(
			window.ynz.path+"/equipment/updateEquipment.do",
			{
				"id":$("#id").val(),
				"equipmentType":$("#equipmentType").val(),
				"equipmentModel":$("#equipmentModel").val(),
				"equipmentNumber":$("#equipmentNumber").val(),
				"factory":$("#factory").val(),
				"powerstationid":$("#powerstationid").val(),
				"producedDate":$("#producedDate").val(),
				"factoryDate":$("#factoryDate").val(),
				"lifetime":$("#lifetime").val(),
				"serviceExpense":$("#serviceExpense").val(),
				"price":$("#price").val(),
				"deprecition":$("#deprecition").val(),
				"remark":$("#remark").val(),
				"filename":filename,
				"filepath":filepath,
				"remarks":remarks,
			},
			function(response){ 
				message.alert( window.ynz.local.tip,window.ynz.local.action_success,3);
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
	    );
}

var FormValidation = function () {
    var handleValidation1 = function() {
            var form_1 = $('#equipmentForm');
            var error3 = $('.alert-danger', form_1);
            var success3 = $('.alert-success', form_1);

            form_1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                rules: {
                	equipmentType: {
                        required: true,
                        maxlength:15
                    },
                    equipmentModel: {
                        required: true,
                        maxlength:15
                    },
                    lifetime:{
                    	number:true
                    },
                    serviceExpense:{
                    	number:true
                    },
                    price:{
                    	number:true
                    },
                    deprecition:{
                    	number:true
                    },
                },

                messages: { // custom messages for radio buttons and checkboxes
                	equipmentType: {
                        required: window.ynz.local.equipment_require_type,
                        maxlength: window.ynz.local.equipment_max_15
                    },
                    equipmentModel: {
                    	required: window.ynz.local.equipment_require_model,
                    	maxlength: window.ynz.local.equipment_max_15
                    },
                    lifetime:{
                    	number:window.ynz.local.equipment_pleaseinputnumber
                    },
                    serviceExpense:{
                    	number:window.ynz.local.equipment_pleaseinputnumber
                    },
                    price:{
                    	number:window.ynz.local.equipment_pleaseinputnumber
                    },
                    deprecition:{
                    	number:window.ynz.local.equipment_pleaseinputnumberordecimals
                    },
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
                    if(i==0){
        	    		return;
        	    	}
                    onSubmit();
                }

            });
 
    	}
    
    
    var handleWysihtml5 = function() {
        if (!jQuery().wysihtml5) {
            
            return;
        }

        if ($('.wysihtml5').size() > 0) {
            $('.wysihtml5').wysihtml5({
                "stylesheets": ["../assets/global/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]
            });
        }
    }

    return {
        init: function () {
            handleWysihtml5();
            handleValidation1();
        }

    };

}();

