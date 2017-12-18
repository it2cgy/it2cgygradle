var i = 0;
var pointsData = [];
var selectPointsData = [];
var eqarr=[];
var disabled = '';

function refresh(){
	$("#stack1").show();
	if($("#type").val()==1){
		disabled ='disabled="disabled"';
	}else{
		disabled = "";
	}
	 var table = $('#sample_1');//table id
	   var oTable = table.dataTable();
	   oTable.fnAdjustColumnSizing();
	   addPoints();
}
function closeTab(){
	$("#stack1").hide();
}
function addPointTab(){
	$("#stack1").hide();
	saveCurve()
}
function onSubmit(){
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
		point.yaxisType=selectData.yaxisType;
		pointData.push(point);
	}
	if(pointData.length<=0){
		message.alert( window.ynz.local.tip,window.ynz.local.curve_select_curve,3);
		return;
	}
	console.log(pointData);
	ynzAjax.post(
			window.ynz.path+"/curveInfo/addCurve.do",
			{'name':name,
			 'type':type,
			 'firstYaxisName':firstYaxisName,
			 'secondYaxisName':secondYaxisName,
			 'timeSpan':timeSpan,
			 'pointData':pointData,
			 'powerStationId':window.ynz.longieb.powerStationId
			},
			function(response){ 
				message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function (){
					window.location.href=window.ynz.basePath+"dataPage/dataList.do?powerStationId="+window.ynz.longieb.powerStationId;
				});
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
	    );
}

function refreshPointSelect(){
//	$('#pointSelect').children("option").remove();
	var out = [];
	for(var i=0; i<pointsData.length; i++){
		var point = pointsData[i];
		var haveAdd = false;
		for(var j=0;j<selectPointsData.length;j++){
			if(selectPointsData[j].id==point.id){
				haveAdd = true;
				break;
			}
		}
		//加载查询出来的测点
		if(!haveAdd){
			var obbj = new Object();
			obbj.id = pointsData[i].id;
			obbj.measurementtypeDescription =pointsData[i].equipmentcontainerName+" "+pointsData[i].measurementtypeDescription;
			out.push(obbj);
//			$('#pointSelect').append("<option value="+pointsData[i].id+">"+pointsData[i].measurementtypeDescription+"</option>");
		} 
	}  
	var table = $('#sample_1');  
    table.dataTable().fnClearTable(); 
    if(out.length!=0){
    	table.dataTable().fnAddData(out);
    }
    $('.parentChecked').change(function () {
    	var set = jQuery(this).attr("data-set");
        var checked = jQuery(this).is(":checked");
        jQuery(set).each(function () {
            if (checked) {
                $(this).prop("checked", true);
                $(this).parents('tr').addClass("active");
            } else {
                $(this).prop("checked", false);
                $(this).parents('tr').removeClass("active");
            }
        });
    });
}

function saveCurve(){
	var colornum = 0;
	var selectarr=[];
	$('tbody tr .checkboxes').each(function () {
        if ($(this).is(":checked")) {
        	$(this).next().next();
        	console.log($(this)[0]);
            console.log($(this).next().next());
            selectarr.push($(this)[0].value);
        }
    });
//	var selectDataId = $('#pointSelect').val();
	var selectDataarr=[];
	for(var iii=0;iii<selectarr.length;iii++){
		for(var i=0; i<pointsData.length; i++){
			var haveAdd = false; 
			if(pointsData[i].id==selectarr[iii]){
				var selectData1;
				selectData1 = pointsData[i];
				
				selectData1.colorCode =getRandomColor(colornum);
				colornum++;
				$("input[name='testRadio"+selectarr[iii]+"']").each(function(){
					if($(this).is(":checked")){
						if($(this)[0].value=="1"){
							selectData1.yaxisType=0;
						}
						if($(this)[0].value=="2"){
							selectData1.yaxisType=1;
						}
					}
				});
				selectDataarr.push(selectData1);
				break;
			}  
		} 
	}
	if(selectDataarr.length!=0){
		for(ijj=0;ijj<selectDataarr.length;ijj++){
			var selectData = selectDataarr[ijj];
			var yaxisType = ""
			if(selectData.yaxisType=="0"){
				yaxisType="左Y轴";
			}
			if(selectData.yaxisType=="1"){
				yaxisType="右Y轴";
			}
			$("#pointSelectId").append("<tr><td>"+selectData.descriptionType+"</td><td>"+selectData.equipmentcontainerName+"</td><td>"+selectData.measurementtypeDescription+"</td><td><div style='width:30px;height: 15px;display:inline-block;background-color:"+selectData.colorCode+"' >" +
					"</div>"+" <span>"+selectData.colorCode+"</span></td>" +
					"<td>"+yaxisType+"</td>" +
					"<td><a onclick='deleterow(this,"+selectData.id+")'>"+ window.ynz.local.del +"</a></td></tr>");
			$("#stack1Close").click();
			selectPointsData.push(selectData);
		}
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
	refreshPointSelect();
}
var FormValidation = function () {
    // advance validation
    var handleValidation1 = function() {

            var form_1 = $('#form_1');
            var error3 = $('.alert-danger', form_1);
            var success3 = $('.alert-success', form_1);

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

            var form_2 = $('#form_2');
            var error3 = $('.alert-danger', form_2);
            var success3 = $('.alert-success', form_2);

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
                        required: window.ynz.local.curve_required_pointcolor
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

            $('.form-control.select3me').change(function() {
            	$("[name=form_2_pointid]").val($('.form-control.select3me').val());
            })

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
	setSelectMenuRoot("menudatalistmanager");
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
	$("#equipmentTepyTable").empty();
    var commonData = new CommonDatas();
	for(var da in commonData.powerStationEquipmentType){
		if(window.ynz.localType == "en_US"){
			commonData.powerStationEquipmentType[da].name = commonData.powerStationEquipmentType[da].english;
		}
		if(window.ynz.longieb.roleId==4){
			var flag = false;
			for(var ddata in thirdEquipments){
				if(commonData.powerStationEquipmentType[da].id==thirdEquipments[ddata].equipmentType){
					flag = true;
					break;
				}
			}
			if(flag||commonData.powerStationEquipmentType[da].id==8){
				if($("#equipmentTepyTable").find("tr").length==0){
					$("#equipmentTepyTable").append('<tr><td class="mt-radio-list borderR">'+
							'<label class="mt-radio mt-radio-outline">'+
							'<input type="radio" value="'+commonData.powerStationEquipmentType[da].id+'" name="equipmentTypeRadio" checked="checked"><span></span></label></td>'+
							'<td style="padding-left: 12px;">'+commonData.powerStationEquipmentType[da].name+'</td></tr>');
				}else{
					$("#equipmentTepyTable").append('<tr><td class="mt-radio-list borderR">'+
							'<label class="mt-radio mt-radio-outline">'+
							'<input type="radio" value="'+commonData.powerStationEquipmentType[da].id+'" name="equipmentTypeRadio"><span></span></label></td>'+
							'<td style="padding-left: 12px;">'+commonData.powerStationEquipmentType[da].name+'</td></tr>');
				}
			}
			$(".equipType").css("height","170px");
		}else{
			if($("#equipmentTepyTable").find("tr").length==0){
				$("#equipmentTepyTable").append('<tr><td class="mt-radio-list borderR">'+
						'<label class="mt-radio mt-radio-outline">'+
						'<input type="radio" value="'+commonData.powerStationEquipmentType[da].id+'" name="equipmentTypeRadio" checked="checked"><span></span></label></td>'+
						'<td style="padding-left: 12px;">'+commonData.powerStationEquipmentType[da].name+'</td></tr>');
			}else{
				$("#equipmentTepyTable").append('<tr><td class="mt-radio-list borderR">'+
						'<label class="mt-radio mt-radio-outline">'+
						'<input type="radio" value="'+commonData.powerStationEquipmentType[da].id+'" name="equipmentTypeRadio"><span></span></label></td>'+
						'<td style="padding-left: 12px;">'+commonData.powerStationEquipmentType[da].name+'</td></tr>');
			}
			$(".equipType").css("height","270px");
		}
	}
	getequipments($("#equipmentTepyTable").find("tr").eq(0).find("input").val());
	$("input[name='equipmentTypeRadio']").click(function(){
		eqarr=[];
		console.log($(this)[0].value);
		var equipmentType = $(this)[0].value;
    	getequipments(equipmentType);
	});
}
/**
 * 获取指定设备类型的设备
 * @param equipmentType
 */
function getequipments(equipmentType){
	$("#equipmentListTable").empty();
	ynzAjax.get(
    		basePath+"pointInfo/getEquipments.do?powerStationId="+window.ynz.longieb.powerStationId+"&equipmentType="+equipmentType,
    		function(response){ 
    			if(window.ynz.longieb.roleId==4){
    				for(var da = 0;da<response.data.length;da++){
    					for(var ddaa in thirdEquipments){
    						if((response.data[da].equipmentId==thirdEquipments[ddaa].equipmentId&&response.data[da].equipmentTableId==thirdEquipments[ddaa].equipmentType)||response.data[da].equipmentTableId==8){
    							if($("#equipmentListTable").find("tr").length==0){
    								eqarr.push(response.data[da].equipmentId);
    								$("#equipmentListTable").append('<tr><td class="mt-checkbox-list borderR">'+
    										'<label class="mt-checkbox mt-checkbox-outline">'+
    										'<input type="checkbox" id="equipsList'+response.data[da].equipmentId+'" value="'+response.data[da].equipmentId+'" name="equipsList" checked="checked"><span onclick="checkequips(this);"></span></label></td>'+
    										'<td style="padding-left: 12px;">'+response.data[da].equipmentcontainerName+'</td></tr>');
    							}else{
    								$("#equipmentListTable").append('<tr><td class="mt-checkbox-list borderR">'+
    										'<label class="mt-checkbox mt-checkbox-outline">'+
    										'<input type="checkbox" id="equipsList'+response.data[da].equipmentId+'" value="'+response.data[da].equipmentId+'" name="equipsList"><span onclick="checkequips(this);"></span></label></td>'+
    										'<td style="padding-left: 12px;">'+response.data[da].equipmentcontainerName+'</td></tr>');
    							}
    							break;
    						}
    					}
    				}
    			}else{
    				for(var da in response.data){
    					if($("#equipmentListTable").find("tr").length==0){
    						eqarr.push(response.data[da].equipmentId);
							$("#equipmentListTable").append('<tr><td class="mt-checkbox-list borderR">'+
									'<label class="mt-checkbox mt-checkbox-outline">'+
									'<input type="checkbox" id="equipsList'+response.data[da].equipmentId+'" value="'+response.data[da].equipmentId+'" name="equipsList" checked="checked"><span onclick="checkequips(this);"></span></label></td>'+
									'<td style="padding-left: 12px;">'+response.data[da].equipmentcontainerName+'</td></tr>');
						}else{
							$("#equipmentListTable").append('<tr><td class="mt-checkbox-list borderR">'+
									'<label class="mt-checkbox mt-checkbox-outline">'+
									'<input type="checkbox" id="equipsList'+response.data[da].equipmentId+'" value="'+response.data[da].equipmentId+'" name="equipsList"><span onclick="checkequips(this);"></span></label></td>'+
									'<td style="padding-left: 12px;">'+response.data[da].equipmentcontainerName+'</td></tr>');
						}
    				}
    			}
    			getPoints();
    			
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
    	)
    	
 	
}
function checkequips(param){
	var temp =$(param).prev()[0];
	if(temp.checked){
		for(var i=0;i<eqarr.length;i++){
			if(eqarr[i]==temp.value){
				eqarr.splice(i,1);
				break;
			}
		}
	}else{
		eqarr.push(temp.value);
	}
	console.log(eqarr);
	getPoints();
}
/**
 * 获取指定设备的所有测点
 */
function getPoints(){
	var allType = $("input[name='equipmentTypeRadio']");
	var equipmentcontainerTableid;
	for(var type in allType){
		if(allType[type].checked){
			equipmentcontainerTableid = allType[type].value;
		}
	}
	var equipmentcontainerId="";
	for(var ij=0;ij<eqarr.length;ij++){
		if(equipmentcontainerId==""){
			equipmentcontainerId = eqarr[ij];
		}else{
			equipmentcontainerId+=","+eqarr[ij];
		}
	}
	var checkedPoints = getHavePoints();
	var measurementtypeName = $("#pointTypeName").val();
	if(equipmentcontainerId==""){
		loadPoint();
	}else{
		if(window.ynz.longieb.roleId==4){
			if(equipmentcontainerTableid!=8){
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
							var aarr = [];
							for(var jjj in response.data){
								var flag =false;
								for(var iiiaa in thirdEquipments){
									if(response.data[jjj].equipmentcontainerId==thirdEquipments[iiiaa].equipmentId&&response.data[jjj].equipmentcontainerTableid==thirdEquipments[iiiaa].equipmentType){
										flag = true;
										break;
									}
								}
								if(flag){
									aarr.push(response.data[jjj]);
								}else if(response.data[jjj].equipmentcontainerTableid==8){
									for(var ijj in thirdPoints){
										if(response.data[jjj].id==thirdPoints[ijj].pointId){
											aarr.push(response.data[jjj]);
											break;
										}
									}
								}
							}
							loadPoint(response.data);
						},
						function(e){ 
							console.log("--------error------"+e);
						}
				)
			}else{
				var dataarr = [];
				for(var iii in thirdPoints){
					var onj = new Object();
					onj.descriptionType="气象站";
					onj.equipmentcontainerId=equipmentcontainerId;
					onj.equipmentcontainerName="气象站";
					onj.equipmentcontainerTableid=equipmentcontainerTableid;
					onj.id=thirdPoints[iii].pointId;
					onj.measurementtypeDescription=thirdPoints[iii].name;
					onj.measurementtypeName=thirdPoints[iii].pointType;
					onj.powerStationId=redirectStationId;
					dataarr.push(onj);
				}
				loadPoint(dataarr)
			}
		}else{
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
						console.log(response.data);
						loadPoint(response.data)
					},
					function(e){ 
						console.log("--------error------"+e);
					}
			)
		}
	}
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
	if(data!=null){
		for (var int = 0; int < data.length; int++) {
			pointsData.push(data[int]);
		}
	}
	refreshPointSelect();
}

var colorSelectList = ["#62cf8c",
			              "#09aded","#b261c2","#f7c216",
			              "#999999","#dc4257","#0284dc",
			              "#e1741a","#000"];
function getRandomColor(n){
	var colorNoSelect = [];
	for(var k=0;k<colorSelectList.length;k++){
		var haveUser = false;
		var newColor = colorSelectList[k]; 
		for(var j=0;j<selectPointsData.length;j++){
			var selectData = selectPointsData[j]; 
			if(newColor==selectData.colorCode){
				haveUser = true;
				break;
			}
		}
		if(!haveUser){
			colorNoSelect.push(newColor);
		}
	} 
	if(colorNoSelect.length>0){
		if(colorNoSelect[n]==undefined){
			return '#'+('00000'+(Math.random()*0x1000000<<0).toString(16)).substr(-6);
		}
		return colorNoSelect[n];
	}else{
		return '#'+('00000'+(Math.random()*0x1000000<<0).toString(16)).substr(-6);
	}
	
}


/*******************************/
$(function(){
	 TableDatatablesButtons.init();
});
/**
* 表格初始化
*/
var TableDatatablesButtons = function () {

/**
* 初始化表格
*/
var initTable1 = function () {
   var table = $('#sample_1');//table id

   var oTable = table.dataTable({
   	"bAutoWidth":true,//设置自动计算列宽
   	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
       "serverSide":false,//设置服务器端分页方式  false情况下默认使用前端插件分页
       //"sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
     //  "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
       "bFilter":false,
       "bSort":false,
       "paging":false,
       "scrollY":220, 
       "order": [
                 [0, 'asc']
             ],
       /**
        * 指定显示列
        *  mDataProp 对应服务端字段名  
        *  sTitle 列展示名称
        *  visible true时显示当前列 设置false 隐藏当前列  不设置默认true
        *  sClass 对齐方式 
        */
       
      "aoColumns": [
           {
				sTitle:"<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'><input type='checkbox' class='group-checkable parentChecked' data-set='#sample_1 .checkboxes' /><span></span></label>",
				className:"group-checkable", 
				"sWidth": "20%", //列对齐方式等 
				render:function(data,type,row,meta){
						var  content ="";
						content = " <label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'> <input type='checkbox' class='checkboxes' value='"+ row.id +"' /> <span></span></label>";
						return content;
				}
           },
          {
        	   "mDataProp":"measurementtypeDescription",
        	   "sTitle":window.ynz.local.report_sightname ,
       	 		"sClass": "center", //列对齐方式等 
       	 		"sWidth": "47%" //列对齐方式等 
          },
	      {
        	  "mDataProp":"",
	        	 "sTitle": window.ynz.local.curve_pointxy,
	        	 "sClass": "center", //列对齐方式等 
       		 "render":function(data,type,row){//增加行内操作
       			 var left = "左Y轴";
       			 var right = "右Y轴";
       			 if(window.ynz.localType=="en_US"){
       				 left = "left Y-axis";
       				 right = "right Y-axis";
       			 }
   			 	var html ='<label class="mt-radio mt-radio-outline" style="margin:10px 10px 6px 0;">'+left+'<input type="radio" value="1" name="testRadio'+row.id+'" checked="checked"><span></span></label>';
   			 html+='<label class="mt-radio mt-radio-outline" style="margin:10px 10px 6px 0;">'+right+'<input class="checkRadioClass" type="radio" value="2" name="testRadio'+row.id+'"><span></span></label>';
   			 	 	return html;
           	  }
	       }
	       
      ] ,
      /**
       * 设置右上角显示按钮 下列按钮为插件自带
       */
       buttons: [  
       ],
		"bLengthChange":false,//表格是否显示每页显示条数（5，10，15，20）设置为false时 用户无法自行更改页面显示条数
       responsive: true,//？？？？
       "order": [  //排序
           [0, 'asc']
       ],
    "iDisplayLength":5,  //页面显示行数  
       /**
        * 汉化
        */
       "language": {
       	"aria": {
       		"sortAscending": ": activate to sort column ascending",
       		"sortDescending": ": activate to sort column descending"
       	},
       	"emptyTable": "没有数据！",
       	"info": "",
       	"infoEmpty": "没有数据",
       	"lengthMenu": "每页显示 _MENU_ 条记录",
       	"zeroRecords": "没有数据",
       	"buttons":{
       		"print":"打印",
       		"copy":"复制",
       		"pdf":"存储为Pdf",
       		"excel":"表格",
       		"csv":"csv",
       		"colvis":"显示/隐藏列"
       	},
       	"oPaginate":{
	           		"sFirst":"这是首页",
	           		"sLast":"这是尾页"
	           		
	         },
	         "iListLength":4,/*
	         "pageSize":5,
	         "pagelist":[5],*/
       	"sProcessing": "正在加载数据...", 
          },
       /**
        * 每页显示条数
        */
       "lengthMenu": [
           [5, 10, 15, 20, -1],
           [5, 10, 15, 20, "All"] // 在这里可以变换没页显示条数
       ],
      
       "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
   });
   table.find('.parentChecked').change(function () {
       var set = jQuery(this).attr("data-set");
       var checked = jQuery(this).is(":checked");
       jQuery(set).each(function () {
           if (checked) {
               $(this).prop("checked", true);
               $(this).parents('tr').addClass("active");
           } else {
               $(this).prop("checked", false);
               $(this).parents('tr').removeClass("active");
           }
       });
   });

   table.on('change', 'tbody tr .checkboxes', function () {
       $(this).parents('tr').toggleClass("active");
   });
   
   oTable.fnAdjustColumnSizing();
}

function retrieveData(sSource, aoData, fnCallback, oSettings ) {
	/**
	 * 当前页码
	 */
	var page  = parseInt(Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength))+1;
	/**
	 * 每页显示条数
	 */
	var pagesize =  oSettings._iDisplayLength;

}  
return {
   init: function () {
       if (!jQuery().dataTable) {
           return;
       }
       initTable1();
   }
};
}();
