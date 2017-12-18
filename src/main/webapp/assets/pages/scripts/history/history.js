/**
 * 测点列表
 */
var pointsData = [];
var selectPointsData = [];
var downdata = [];
var curveImage="";
var columeImage="";
$(function(){
	
	 if (jQuery().datepicker) {
         $('.date-picker').datepicker({
             rtl: App.isRTL(),
             orientation: "left",
             autoclose: true
         });
         
         //$('#start').datepicker("setDate",new Date()); 
     }
	 
	FormValidation.init();
	ComponentsColorPickers.init();
	addPoints();
	setSelectMenuRoot("menumhistory");
	 $.fn.select2.defaults.set("theme", "bootstrap");
	 var placeholder=window.ynz.local.curve_select_curve;
     $("#pointId").select2({ 
         width: "100%"
     });
     /*$(":checkbox").on("click", function() {
         $(this).parent().nextAll("select").prop("disabled", !this.checked);
     });*/
    
     $("#downloadreport").click(function(){
	  	if(downdata.length>0){
	  		downLoaddataword(downdata);
	  	}else{
	  		message.alert( window.ynz.local.tip,window.ynz.local.history_clickquery,3);
	  	}
	  });
     $("#createreport").click(function(){
    	 var target = $("#createreport").attr("data-target");
    	 if(target==undefined){
    		 message.alert( window.ynz.local.tip,window.ynz.local.history_clickquery,3);
    	 }
     })
     
	var table = $('#datatable');
    var oTable = table.dataTable({
   	    "bFilter":false,//搜索
        "bSort":false,//排序
        "language": {
           	"aria": {
           		"sortAscending": ": activate to sort column ascending",
           		"sortDescending": ": activate to sort column descending"
           	},
           	"emptyTable": window.ynz.local.table_emptyTable,
           	"info": window.ynz.local.table_info,
           	"infoEmpty": window.ynz.local.table_infoEmpty,
           	"lengthMenu": window.ynz.local.table_lengthMenu,
           	"zeroRecords": window.ynz.local.table_zeroRecords,
           	"buttons":{
           		"print":window.ynz.local.table_print,
           		"copy":window.ynz.local.table_copy,
           		"pdf":window.ynz.local.table_pdf,
           		"excel":"excel",
           		"csv":"csv",
           		"colvis":"显示/隐藏列"
           	},
        	"oPaginate":{
           		"sFirst":window.ynz.local.table_sfirst,
           		"sPrevious":window.ynz.local.table_sprevious,
           		"sNext":window.ynz.local.table_snext,
           		"sLast":window.ynz.local.table_slast
           	},
           	"sProcessing": window.ynz.local.table_sProcessing, 
              }, 
        buttons: [
//            { extend: 'pdf', className: 'btn green btn-outline' },
            { extend: 'excel', className: 'btn purple btn-outline ' }
        ],
        scrollY:        300,
        deferRender:    true,
        scroller:       true,
        stateSave:      true,
        bInfo:			true,
        "order": [
            [0, 'asc']
        ],
        "lengthMenu": [
            [10, 15, 20, -1],
            [10, 15, 20, "All"]
        ],
        "pageLength": 10,
        "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
       
    }); 
     
});

/**
 * 表格处理
 */
var charts = {
		/**
		 * 加载测点数据
		 */
		queryData : function(){
			var pointMap = {};
			var startTime = $("#startTime").val()+" 00:00:00";
			var endTime   = $("#endTime").val()+" 23:59:59";
			var timeInterval = $("#timeInterval").val();
			var analoginputIds="" ;//测点id
			var length = selectPointsData.length;
			if(length<=0){
				message.alert( window.ynz.local.tip,window.ynz.local.history_pleaseaddquerypoint,3);
				return;
			}
			
			var pointData = [];
			for(var j=0;j<selectPointsData.length;j++){
				var selectData = selectPointsData[j];  
				var analoginputId = selectData.id;
				pointMap[analoginputId]=selectData;
				if(j==0){
					analoginputIds=analoginputIds+analoginputId;
				}else{
					analoginputIds+=","+analoginputId;
				}
			}
		   
			console.log("测点数据---"+analoginputIds);
			var paramObj = {
					startTime : startTime,
					endTime   : endTime,
					haveReal  : 0,
					analoginputIds:analoginputIds,
					minutesSpan:timeInterval
			}
		   	ynzAjax.post(
		    		basePath+"stationmonitor/historyRedress/points",
		    		paramObj,
		    		function(response){ 
		    			var legendData = [];
		    			var time = [];
		    			var seriesBar=[];
		    			var seriesLine=[];
						//console.log(JSON.stringify(response.data));
						var out = [];
						var dataTableStr ="";
		    			for(var i = 0;i<response.data.length;i++){
		    				var serieDatas=[];
		    				var seriesBarData={};
		    				var seriesLineData={}
		    				var pointInfo = pointMap[response.data[i].analoginputId];

		    				var pointName =pointInfo.measurementtypeDescription;
		    				var color = pointInfo.colorCode;
		    				legendData[i]=pointName;
		    				seriesLineData.name=pointName;
		    				seriesLineData.type="line";
		    				seriesLineData.itemStyle={"normal":{"color":color}};
		    				seriesBarData.name=pointName;
		    				seriesBarData.type="bar";
		    				seriesBarData.itemStyle={"normal":{"color":color}};
		    				for(var j=0;j<response.data[i].historyDatas.length;j++){ 
		    					var timeStr = timeUtil.timeToString(response.data[i].historyDatas[j].time,"yyyy-MM-dd HH:mi");
		    					var value = response.data[i].historyDatas[j].value;
		    					time[j]=timeStr;
		    					serieDatas[j]=toDecimal(value,3);
		    					out.push( [ pointName, timeStr, value] );
		    				}
		    				seriesLineData.data=serieDatas;
		    				seriesBarData.data=serieDatas;
		    				seriesBar[i]=seriesBarData;
		    				seriesLine[i]=seriesLineData;
		    			}
		    			 var table = $('#datatable');  
		    		    table.dataTable().fnClearTable(); 
		    		    table.dataTable().fnAddData(out);
		    		    downdata = out;
		    			charts.chartsBar(legendData,time,seriesBar);
	    				charts.chartsLine(legendData,time,seriesLine);
		    		},
			        function(e){ 
			            console.log("--------error------"+e);
			        }
		    )
		},
		/**
		 * 生成图表
		 * _data 显示的数据列表 曲线集合
		 * _xdata x轴数据
		 * _series 每条曲线的数据
		 */
		chartsBar: function(_legendData,_xdata,_series){
			 var myChart = echarts.init(document.getElementById('echarts_bar'));
			    myChart.setOption({
			        tooltip: {
			            trigger: 'axis'
			        },
			        legend: {//显示的数据列
			            data: _legendData
			        },
			        toolbox: {
			            show: true,
			            feature: {
			                mark: {
			                    show: false
			                },
			                dataView: {
			                    show: false,
			                    readOnly: false
			                },
			                magicType: {
			                    show: false,
			                    type: ['bar']
			                },
			                dataZoom : {show: true},
	        	            restore : {show: true},
	                        restore: {
	                            show: true
	                        },
			                saveAsImage: {
			                    show: true
			                }
			            }
			        },
			        animation:false,
			        calculable: true,
			        xAxis: [{//x轴
			            type: 'category',
			            data: _xdata
			        }],
			        yAxis: [{//y轴
			            type: 'value',
			            splitArea: {
			                show: true
			            }
			        }],
			        series:_series
			    });
			    if(myChart.getDataURL()!=null){
			    	curveImage = myChart.getDataURL();
				}
		},	
		chartsLine : function(_legendData,_xdata,_series){
			 var myChart = echarts.init(document.getElementById('echarts_line'));
			    myChart.setOption({
			        tooltip: {
			            trigger: 'axis'
			        },
			        legend: {//显示的数据列
			            data:_legendData
			        },
			        toolbox: {
			            show: true,
			            feature: {
			                mark: {
			                    show: false
			                },
			                dataView: {
			                    show: false,
			                    readOnly: false
			                },
			                magicType: {
			                    show: false,
			                    type: ['line']
			                },
			                dataZoom : {show: true},
	        	            restore : {show: true},
			                saveAsImage: {
			                    show: true
			                }
			            }
			        },
			        animation:false,
			        calculable: true,
			        xAxis: [{//x轴
			            type: 'category',
			            data: _xdata
			        }],
			        yAxis: [{//y轴
			            type: 'value',
			            splitArea: {
			                show: true
			            }
			        }],
			        series:_series
			    });
			    if(myChart.getDataURL()!=null){
			    	columeImage = myChart.getDataURL();
				}
		}
};



function refreshPointSelect(){
	$('#pointId').children("option").remove();
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
			$('#pointId').append("<option value="+pointsData[i].id+">"+pointsData[i].measurementtypeDescription+"</option>");
		} 
	} 
	
	 $("#pointId").select2({ 
         width: "100%"
     });
}
function savePoint(){
	var selectDataId = $('#pointId').val();
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
		$("#queryTablebody").append("<tr><td>"+selectData.measurementtypeDescription+"</td><td><div style='width:30px;height: 15px;display:inline-block;background-color:"+selectData.colorCode+"' ></div>"+" <span>"+selectData.colorCode +"</span></td><td><a onclick='deleterow(this,"+ selectData.id +")'>"+ window.ynz.local.del +"</a></td></tr>");
	 
		selectPointsData.push(selectData);
	} 
 	$("#stack1Close").click();
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


/**
 * 生成报表
 */
function saveReport(){
	var paramObj={};
	var pointMap = {};
	paramObj.reportname=$("#reportName").val();
	paramObj.powerstationId=window.ynz.longieb.powerStationId;
	var startTime = $("#startTime").val()+" 00:00:00";
	var endTime   = $("#endTime").val()+" 23:59:59"; 
	var timeInterval = $("#timeInterval").val();
	var analoginputIds="" ;//测点id
	var length = selectPointsData.length;
	if(length<=0){
		message.alert( window.ynz.local.tip,window.ynz.local.history_pleaseaddquerypoint,3);
		return;
	}
	paramObj.points=[];
	for(var j=0;j<selectPointsData.length;j++){
		var points = {};
		var selectData = selectPointsData[j];  
		var analoginputId = selectData.id;
		pointMap[analoginputId]=selectData;
		if(j==0){
			analoginputIds=analoginputIds+analoginputId;
		}else{
			analoginputIds+=","+analoginputId;
		}
		points.analoginputId = analoginputId;
		points.analoginputColor = selectData.colorCode;
		points.analoginputName = selectData.measurementtypeDescription;
		paramObj.points[j]=points;
	}
	paramObj.analoginputIds=analoginputIds;
	paramObj.startTime=startTime;
	paramObj.endTime=endTime;
	paramObj.curveImage=curveImage;
	paramObj.columeImage=columeImage;
	paramObj.haveReal=0;
	paramObj.analoginputIds=analoginputIds;
	paramObj.minutesSpan=timeInterval;
	var length = $('.table').children("tbody").find("tr").length;
	if(length<=0){
		message.alert( window.ynz.local.tip,window.ynz.local.history_pleaseaddquerypoint,3);
		return;
	}
	if(paramObj.curveImage==""||paramObj.columeImage==""){
		message.alert( window.ynz.local.tip,window.ynz.local.history_pleaseclickquery,3);
		return;
	}
	ynzAjax.post(
		window.ynz.path+"/reports/saveReport",
		paramObj,
		function(response){
			var id = response.data;
			message.alert( window.ynz.local.tip,window.ynz.local.history_generatedreport,3);
        },
        function(e){ 
            console.log("--------error------"+JSON.stringify(e));
        }
	 );
}
function downLoadword(id){
	var form = $("<form>");
	form.attr('style','display:none');
	form.attr('target','');
	form.attr('method','post');
	form.attr('action',basePath+"reports/downLoadword.do");
	var input = $('<input>');
	input.attr('type','hidden');
	input.attr('name','id');
	input.attr('value',id);
	$('body').append(form);
	form.append(input);
	form.submit();
}
function downLoaddataword(downdata){
	var form = $("<form>");
	form.empty();
	form.attr('style','display:none');
	form.attr('target','');
	form.attr('method','post');
	form.attr('action',basePath+"reports/downLoadwordReport.do");
	$('body').append(form);
	var name = "";
	var time = "";
	var value = "";
	for (var i = 0; i < downdata.length; i++) {
		name+=","+downdata[i][0];
		time+=","+downdata[i][1];
		value+=","+downdata[i][2];
	}
	name = name.substring(1,name.length);
	time = time.substring(1,time.length);
	value = value.substring(1,value.length);
	var inputname = $('<input>');
	inputname.attr('type','hidden');
	inputname.attr('name',"name");
	inputname.attr('value',name);
	form.append(inputname);
	var inputtime = $('<input>');
	inputtime.attr('type','hidden');
	inputtime.attr('name',"time");
	inputtime.attr('value',time);
	form.append(inputtime);
	var inputvalue = $('<input>');
	inputvalue.attr('type','hidden');
	inputvalue.attr('name',"value");
	inputvalue.attr('value',value);
	form.append(inputvalue);
   var inputcurveImage = $('<input>');
   inputcurveImage.attr('type','hidden');
   inputcurveImage.attr('name',"curveImage");
   inputcurveImage.attr('value',curveImage);
   form.append(inputcurveImage);
   var inputcolumeImage = $('<input>');
   inputcolumeImage.attr('type','hidden');
   inputcolumeImage.attr('name',"columeImage");
   inputcolumeImage.attr('value',columeImage);
   form.append(inputcolumeImage);
	form.submit();
}
var FormValidation = function () {
    // advance validation
    var handleValidation1 = function() { 
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
                    startTime: {
                    	required: true
                    },
                    endTime: {
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
                    startTime: {
                        required: window.ynz.local.history_pleaseselectstarttime
                    },
                    endTime: {
                        required: window.ynz.local.history_pleaseselectendtime
                    },
                    timeSpan: {
                        required: window.ynz.local.history_pleaseselecttimeinterval
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
                    charts.queryData();
                    $("#createreport").attr("data-target","#reportModel");
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
            
             //initialize datepicker
            $('.date-picker').datepicker({
                rtl: App.isRTL(),
                autoclose: true
            });
            $('.date-picker .form-control').change(function() {
            	form_1.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
            })
    	}
    
    
    var handleValidation2 = function() { 
        var form_report = $('#form_report');
         
        //IMPORTANT: update CKEDITOR textarea with actual content before submit

        form_report.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "", // validate all fields including form hidden input
            rules: { 
            	reportName: {
                	required: true
                } 
            },

            messages: { // custom messages for radio buttons and checkboxes 
            	reportName: {
                    required: window.ynz.local.history_pleaseinputreportname
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
               // App.scrollTo(error3, -200);
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
            	saveReport();
            	$("#reportModelclose").click();
            	//message.alert( window.ynz.local.tip,"保存成功！",3);
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
        //main function to initiate the module
        init: function () {
            handleWysihtml5();
            handleValidation1();
            handleValidation2();
            
        }

    };

}();
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