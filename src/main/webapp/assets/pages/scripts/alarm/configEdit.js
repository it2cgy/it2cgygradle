var i = 0;
var pointsData = [];
var measurementtypeList = [];  
var eqarr=[];
var disabled = '';
var equipmentcontainerName;
var measurePointDiscription;
 
function onSubmit(){
	var mess = $("#messageTemplate").val();
	var type = $("#alarmType").val();
	var valueOne = $("#valueOne").val();
	var valueTwo = $("#valueTwo").val();
	var valueThree = $("#valueThree").val(); 
	var selectarr=$("#pointId").val(); 
	
	ynzAjax.post(
			window.ynz.path+"/alarmConfig/configEdit.do",
			{
			  'id':id,
			  'type':type,
			  'message':mess,
			  'valueOne':valueOne,
			  'valueTwo':valueTwo,
			  'valueThree':valueThree,
			  'pointid':selectarr,
			},
			function(response){ 
				message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function (){
					window.location.href=window.ynz.basePath+"alarmPage/configList.do?powerStationId="+window.ynz.longieb.powerStationId;
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
	
//	for(var i=0; i<pointsData.length; i++){
//		var point = pointsData[i];
//		var measurementtypeName = $("#measurementtype").val(); 
//		if(measurementtypeName == point.measurementtypeId){
//			var obbj = new Object();
//			obbj.id = point.id;
//			obbj.measurementtypeDescription =point.equipmentcontainerName;
//			out.push(obbj);
//		}
//		 
//	} 
//	for(var i=0; i<out.length; i++){
//		if(measurePointDiscription!=null){
//		if(measurePointDiscription == point.equipmentcontainerName)
//		$("#pointId").append("<option selected value='"+point.id+"'>"+point.equipmentcontainerName+"</option>");
//	}else{
//		$("#pointId").append("<option value='"+point.id+"'>"+point.equipmentcontainerName+"</option>");
//	}
//	}
	$("#pointId").empty();
	for(var i=0; i<pointsData.length; i++){
		var point = pointsData[i];
		var measurementtypeName = $("#measurementtype").val(); 
		if(measurementtypeName == point.measurementtypeId){
			var obbj = new Object();
			if(equipmentcontainerName!=null){
				if(equipmentcontainerName == point.equipmentcontainerName){
					$("#pointId").append("<option selected value='"+point.id+"'>"+point.equipmentcontainerName+"</option>");
				}else{
					$("#pointId").append("<option value='"+point.id+"'>"+point.equipmentcontainerName+"</option>");
				}
			}else{
				$("#pointId").append("<option value='"+point.id+"'>"+point.equipmentcontainerName+"</option>");
			}
			obbj.id = point.id;
			obbj.measurementtypeDescription =point.equipmentcontainerName;
//			out.push(obbj);
		}
	}  
//	var table = $('#sample_1');  
//    table.dataTable().fnClearTable(); 
//    if(out.length!=0){
//    	table.dataTable().fnAddData(out);
//    }
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
                	messageTemplate: {
                        required: true
                    }

            
                },

                messages: { // custom messages for radio buttons and checkboxes
                	messageTemplate: {
                		required: " "
                	} ,
                	valueOne: {
                		required: " "
                	} ,
                	valueTwo: {
                		required: " "
                	} ,
                	valueThree: {
                		required: " "
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
 
            $('#measurementtype').change(function() {
            	refreshPointSelect();
            });
            
            $('#equipmentType').change(function() {
            	 getPoints();
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
	var equipmentcontainerTableid=$("#equipmentType").val(); 
	var equipmentcontainerId=""; 
	var checkedPoints = "";
	var measurementtypeName = "";
 
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
 

function loadPoint(data){//根据电站id添加测点id和名称
	console.log(data);
	pointsData = [];
	measurementtypeList = [];
	if(data!=null){
		for (var int = 0; int < data.length; int++) {
			pointsData.push(data[int]);
			var point = data[int];
			var have=false;
			for(var da in measurementtypeList){
				if(measurementtypeList[da].measurementtypeId==point.measurementtypeId){
					have = true;
					break;
				}
			}
			if(!have){ 
				measurementtypeList.push(point);
			}
		}

		$('#measurementtype').empty();
		for(var da in measurementtypeList){
			if(measurePointDiscription == measurementtypeList[da].measurementtypeDescription){
				$('#measurementtype').append("<option selected value="+measurementtypeList[da].measurementtypeId+">"+measurementtypeList[da].measurementtypeDescription+"</option>");
			}
			$('#measurementtype').append("<option value="+measurementtypeList[da].measurementtypeId+">"+measurementtypeList[da].measurementtypeDescription+"</option>");
		} 
	}
	refreshPointSelect();
}

 
 
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
        	   "sTitle": window.ynz.local.iv_equipment_number ,
       	 		"sClass": "center", //列对齐方式等 
       	 		"sWidth": "47%" //列对齐方式等 
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

function getconfig(id){
	ynzAjax.get(
			basePath+"alarmConfig/getconfigById/"+id,
			function(response){ 
				console.log(response.data);
				$("#messageTemplate").val(response.data.message);
				$("#alarmType").val(response.data.type);
				$("#valueOne").val(response.data.valueOne);
				$("#valueTwo").val(response.data.valueTwo);
				$("#valueThree").val(response.data.valueThree); 
//				$("#equipmentType").val(response.data.equipmentcontainerTableid);
				var option = $("#equipmentType")[0].options;
				for(var i=0;i<option.length;i++){
					if(option[i].value == response.data.equipmentcontainerTableid){
//						option[i].selected = 'selected';
						$("#equipmentType")[0].options[i].selected = true;
					}
				}
//				$("#pointId").val(response.data.pointId); 
				getPoints();
				
				equipmentcontainerName = response.data.equipmentcontainerName;
				refreshPointSelect();
				measurePointDiscription = response.data.measurePointDiscription;
			},
			function(e){ 
				console.log("--------error------"+e);
			}
	) 
}
jQuery(document).ready(function() {
	
	$("#pointSelect").select2({ 
        width: "100%"
    });
	setSelectMenu("menualarmmanager","alarmmanager_config");
	
//	TableDatatablesButtons.init();
    
	FormValidation.init();
    ComponentsColorPickers.init(); 
    var commonData = new CommonDatas();
	for(var da in commonData.powerStationEquipmentType){
		if(window.ynz.localType == "en_US"){
   		 commonData.powerStationEquipmentType[da].name = commonData.powerStationEquipmentType[da].english;
   	 }
		$('#equipmentType').append("<option value="+commonData.powerStationEquipmentType[da].id+">"+commonData.powerStationEquipmentType[da].name+"</option>");
	}
    getPoints()
    getconfig(id)
});
