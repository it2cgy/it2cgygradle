var i = 0;
var pointsData = [];
var selectPointsData = [];


var pointList = curveDetailVO.pointList;
for (var i = 0; i < pointList.length; i++) {
	var selectData={};
	selectData
	var point = pointList[i];
	selectData.yaxisType=point.yaxisType;
	selectData.id = point.analoginputId;
	selectData.measurementtypeDescription=point.pointName;
	selectData.colorCode=point.colorCode;
	if(!selectData.yaxisType){
		selectData.yaxisType = 0;
	}
	selectPointsData.push(selectData);
}


function onSubmit(){
	var id = $("[name='id").val();
	var name = $("[name='name").val();
	var type = $("[name='type").val();
	var firstYaxisName = $("[name='firstYaxisName").val();
	var secondYaxisName = $("[name='secondYaxisName").val();
	var timeSpan = $("[name='timeSpan").val();
	if(secondYaxisName==""||type==1){
		secondYaxisName==null;
	}
	
	var pointData = [];
	for(var j=0;j<selectPointsData.length;j++){
		var selectData = selectPointsData[j];
		var point = {};
		point.analoginputId=selectData.id;
		point.name=selectData.measurementtypeDescription;
		point.colorCode=selectData.colorCode;
		if(point.colorCode==null || point.colorCode==""){
			point.colorCode="#15bda4"; //预防bug给个默认颜色
		}
		point.yaxisType=selectData.yaxisType;
		pointData.push(point);
	}
	if(pointData.length<=0){
		message.alert( window.ynz.local.tip,window.ynz.local.curve_select_curve,3);
		return;
	}
	console.log(pointData);
	ynzAjax.post(
			window.ynz.path+"/curveInfo/updateCurve.do",
			{'id':id,
			 'name':name,
			 'type':type,
			 'firstYaxisName':firstYaxisName,
			 'secondYaxisName':secondYaxisName,
			 'timeSpan':timeSpan,
			 'pointData':pointData,
			 'powerStationId':window.ynz.longieb.powerStationId
			},
			function(response){ 
				message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
					//window.location.href=window.ynz.basePath+"curvePage/curveList.do?powerStationId="+window.ynz.longieb.powerStationId;

				});
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
	    );
}

//function editCurve(id){//根据电站id添加测点id和名称
//	ynzAjax.post(
//		window.ynz.path+"/pointInfo/getPowerStationAllPointNOPage.do",
//		{'powerStationId':id},
//		function(response){ 
//			var data = response.data;
//			
//			var pointList = curveDetailVO.pointList;
//			for (var int = 0; int < data.length; int++) {
//				pointsData.push(data[int]);
//				for(var j=0;j<pointList.length;j++){
////					if(data[int].id==pointList[j].analoginputId){
////						//通过pointList就可以初始化了，没必要在这里
////						
////						//selectPointsData.push(data[int]);
////						break;
////					}
//				}
//				//var value = data[int].id+"_"+data[int].name;
//				//$('.form-control.select3me').append("<option value="+value+">"+data[int].name+"</option>");
//			}
//			
//			//初始化selectpointData
//			//curveDetailVO={"id":30,"powerStationId":21,"name":"呵呵","timeSpan":5,"type":2,"firstYaxisName":"22","secondYaxisName":"33","createTime":null,"pointList"
//			
//			refreshPointSelect();
//        },
//        function(e){ 
//            console.log("--------error------"+JSON.stringify(e));
//        }
//    );
//}

function refreshPointSelect(){
	$('#pointSelect').children("option").remove();
	for(var i=0; i<pointsData.length; i++){
		var point = pointsData[i];
		var haveAdd = false;
		for(var j=0;j<selectPointsData.length;j++){
			if(selectPointsData[j].id==point.id){
				haveAdd = true;
				break;
			}
		}
		if(!haveAdd){
			$('#pointSelect').append("<option value="+pointsData[i].id+">"+pointsData[i].measurementtypeDescription+"</option>");
		} 
	} 
	
	$("#pointSelect").select2({ 
        width: "100%"
    });
}

function saveCurve(){
	var selectDataId = $('#pointSelect').val();
	var selectData;
	for(var i=0; i<pointsData.length; i++){
		var haveAdd = false; 
		if(pointsData[i].id==selectDataId){
			selectData = pointsData[i];
			break;
		}  
	} 
	
	if(selectData){
		selectData.colorCode = $("#colorCode").val();
		selectData.yaxisType = $('.form-control.select4me').val();
		$("#pointSelectId").append("<tr><td>"+selectData.measurementtypeDescription+"</td><td><div style='width:30px;height: 15px;display:inline-block;background-color:"+selectData.colorCode+"' ></div>"+" <span>"+selectData.colorCode+"</span></td><td>"+(selectData.yaxisType=="0"?"左Y轴" :"右Y轴")+"</td><td><a onclick='deleterow(this,"+selectData.id+")'>"+ window.ynz.local.del +"</a></td></tr>");
		$("#stack1Close").click();
		selectPointsData.push(selectData);
	}
	$('#stack1alertsuccess').hide();
	refreshPointSelect();
}
function deleterow(row,id){
	var pointData = [];
	for(var j=0;j<selectPointsData.length;j++){
		var selectData = selectPointsData[j];
		if(selectData.id==id){
			selectPointsData.splice(j,1);
			break;
		}
	}
  
	
	$(row).parent().parent().remove();//删除该行
}
var FormFileUpload = function () {
    return {
        //main function to initiate the module
        init: function () {

             // Initialize the jQuery File Upload widget:
            $('#fileupload').fileupload({
                disableImageResize: false,
                autoUpload: false,
                disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
                maxFileSize: 5000000,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                // Uncomment the following to send cross-domain cookies:
                //xhrFields: {withCredentials: true},                
            });

            // Enable iframe cross-domain access via redirect option:
            $('#fileupload').fileupload(
                'option',
                'redirect',
                window.location.href.replace(
                    /\/[^\/]*$/,
                    '/cors/result.html?%s'
                )
            );

            // Upload server status check for browsers with CORS support:
            if ($.support.cors) {
                $.ajax({
                    type: 'HEAD'
                }).fail(function () {
                    $('<div class="alert alert-danger"/>')
                        .text('Upload server currently unavailable - ' +
                                new Date())
                        .appendTo('#fileupload');
                });
            }

            // Load & display existing files:
            $('#fileupload').addClass('fileupload-processing');
            $.ajax({
                // Uncomment the following to send cross-domain cookies:
                //xhrFields: {withCredentials: true},
                url: $('#fileupload').attr("action"),
                dataType: 'json',
                context: $('#fileupload')[0]
            }).always(function () {
                $(this).removeClass('fileupload-processing');
            }).done(function (result) {
                $(this).fileupload('option', 'done')
                .call(this, $.Event('done'), {result: result});
            });
        }

    };

}();
var FormValidation = function () {
    // advance validation
    var handleValidation1 = function() {
        // for more info visit the official plugin documentation: 
        // http://docs.jquery.com/Plugins/Validation

            var form_1 = $('#form_1');
            var error3 = $('.alert-danger', form_1);
            var success3 = $('.alert-success', form_1);

            //IMPORTANT: update CKEDITOR textarea with actual content before submit

            form_1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                rules: {
                	name: {
                        required: true
                    }, 
                    type: {
                        required: true
                    },
                    firstYaxisName: {
                        required: true
                    },
//                    secondYaxisName: {//动态添加
//                        required: true
//                    },
                    timeSpan: {
                        required: true
                    } 
                },

                messages: { // custom messages for radio buttons and checkboxes
                	name: {
                        required: window.ynz.local.curve_required_name
                    }, 
                    type: {
                        required: window.ynz.local.curve_required_type
                    },
                    firstYaxisName: {
                        required: window.ynz.local.curve_required_yname
                    },
                    secondYaxisName: {
                        required: window.ynz.local.curve_required_yname
                    },
                    timeSpan: {
                        required: window.ynz.local.curve_required_timespan
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
 
            $('#type').change(function() {
            	if($('.form-control.select5me').val()==1){
            		$("#curvesecond").attr("style");
            		$("#curvesecond").css("display","none");
            		
            		$("#secondYaxisName").rules("remove");
            	}else{
            		$("#curvesecond").css("display","block");
            		$("#curvesecond").removeAttr("style");
            		$("#secondYaxisName").rules("add",{required:true,messages:window.ynz.local.curve_required_yname});
            	}
            })
    	}
    
    
    var handleValidation2 = function() {
        // for more info visit the official plugin documentation: 
        // http://docs.jquery.com/Plugins/Validation

            var form_2 = $('#form_2');
            var error3 = $('.alert-danger', form_2);
            var success3 = $('.alert-success', form_2);

            //IMPORTANT: update CKEDITOR textarea with actual content before submit

            form_2.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                rules: {
                	form_2_pointid: {
                        required: true
                    },
                	colorCode: {
                        required: true
                    }, 
                     
                    yaxisType: {
                        required: true
                    } 
                },

                messages: { // custom messages for radio buttons and checkboxes
                	form_2_pointid: {
                        required:  window.ynz.local.curve_required_pointcolor
                    }, 
                    colorCode: {
                        required: window.ynz.local.curve_required_pointcolor
                    },
                    yaxisType: {
                        required: window.ynz.local.curve_required_pointxy
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
                    
                    $("#stack1").css("height",400);
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
                   // alert("aaa");
                    saveCurve();
                }

            });
             //apply validation on select2 dropdown value change, this only needed for chosen dropdown integration.
//            $('.select2me', form_2).change(function () {
//                form_2.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
//            });

            //initialize datepicker
//            $('.date-picker').datepicker({
//                rtl: App.isRTL(),
//                autoclose: true
//            });
//            $('.form-control.select2me').change(function() {
//            	id = $('.form-control.select2me option:selected').val();
//            	editCurve(id);
//            })
            $('.form-control.select3me').change(function() {
            	$("[name=form_2_pointid]").val($('.form-control.select3me').val());
            })
//            $('.form-control.select5me').change(function() {
//            	if($('.form-control.select5me').val()==1){
//            		$("#curvesecond").attr("style");
//            		$("#curvesecond").css("display","none");
//            	}else{
//            		$("#curvesecond").css("display","block");
//            		$("#curvesecond").removeAttr("style");
//            	}
//            })
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
        //main function to initiate the module
        init: function () {
            handleWysihtml5();
            handleValidation1();
            handleValidation2(); 
        }

    };

}();


var ComponentsColorPickers = function() {
	var handleColorPicker = function() {
		jQuery().colorpicker && ($(".colorpicker-default").colorpicker({
			format: "hex"
		}), $(".colorpicker-rgba").colorpicker())
	},
	o = function() {
		$(".demo").each(function() {
			$(this).minicolors({
				control: $(this).attr("data-control") || "hue",
				defaultValue: $(this).attr("data-defaultValue") || "",
				inline: "true" === $(this).attr("data-inline"),
				letterCase: $(this).attr("data-letterCase") || "lowercase",
				opacity: $(this).attr("data-opacity"),
				position: $(this).attr("data-position") || "bottom left",
				change: function(t, o) {
					t && (o && (t += ", " + o), "object" == typeof console && console.log(t))
				},
				theme: "bootstrap"
			})
		})
	};
	return {
		init: function() {
			o(),
			handleColorPicker()
		}
	}
} ();
jQuery(document).ready(function() {
	
	$("#pointSelect").select2({ 
        width: "100%"
    });
	setSelectMenuRoot("menucustomlinemanager");
    FormValidation.init();
    ComponentsColorPickers.init();
    addPoints();
});
function loadPoint(){//根据电站id添加测点id和名称
	ynzAjax.post(
		window.ynz.path+"/pointInfo/getPowerStationAllPointNOPage.do",
		{'powerStationId':window.ynz.longieb.powerStationId},
		function(response){ 
			var data = response.data;
			for (var int = 0; int < data.length; int++) {
				//var value = data[int].name;
				pointsData.push(data[int]);
			}
			
			refreshPointSelect();
        },
        function(e){ 
            console.log("--------error------"+JSON.stringify(e));
        }
    );
}
/**
 * 曲线添加测点事件
 * @param id
 */
function addPoints(){
    var commonData = new CommonDatas();
	$("#equipmenttype").empty();
	$("#equipmenttype").append("<option value='-1'>"+window.ynz.local.curve_pleaseselectequipmenttype+"</option>");
	for(var da in commonData.powerStationEquipmentType){
		$("#equipmenttype").append("<option value="+commonData.powerStationEquipmentType[da].id+">"+commonData.powerStationEquipmentType[da].name+"</option>");
	}
	 $('#equipmenttype').change(function() {
			var equipmentType = $('#equipmenttype').val();
	    	getequipments(equipmentType);
	});
	getPoints();
}
/**
 * 获取指定设备类型的设备
 * @param equipmentType
 */
function getequipments(equipmentType){
	ynzAjax.get(
    		basePath+"pointInfo/getEquipments.do?powerStationId="+window.ynz.longieb.powerStationId+"&equipmentType="+equipmentType,
    		function(response){ 
    			$("#equipmentnum").empty();
    			$("#equipmentnum").append("<option value='-1'>"+window.ynz.local.curve_pleaseselectequipmentnumber+"</option>");
    			for(var da in response.data){
    				$("#equipmentnum").append("<option value="+response.data[da].equipmentId+">"+response.data[da].equipmentcontainerName+"</option>");
    			}
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
    	)
    	 $('#equipmentnum').change(function() {
    		 getPoints();
    	 });
 	
}
/**
 * 获取指定设备的所有测点
 */
function getPoints(){
	var checkedPoints = getHavePoints();
	var equipmentcontainerTableid = $("#equipmenttype").val();
	var equipmentcontainerId = $("#equipmentnum").val();
	var measurementtypeName = $("#pointTypeName").val();
	ynzAjax.post(
    		basePath+"pointInfo/getPointsNOPage.do",
    		{
    			"powerStationId":window.ynz.longieb.powerStationId,
    			"equipmentcontainerTableid":equipmentcontainerTableid,
    			"equipmentcontainerId":equipmentcontainerId,
    			"measurementtypeName":measurementtypeName,
    			"checkedPoints":checkedPoints
    		},
    		function(response){ 
    			loadPoint(response.data)
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
    	)
}
/**
 * 获取指点曲线已经拥有的测点id
 * @param id
 * @returns {String}
 */
function getHavePoints(){
	var checkedPoint ="";
	for(var ii in selectPointsData){
		if(ii==0){
			checkedPoint+=selectPointsData[ii].id;
		}else{
			checkedPoint+=","+selectPointsData[ii].id;
		}
			
	}
	return checkedPoint;
}

function loadPoint(data){//根据电站id添加测点id和名称
	pointsData = [];
	for (var int = 0; int < data.length; int++) {
		pointsData.push(data[int]);
	}
	refreshPointSelect();
}