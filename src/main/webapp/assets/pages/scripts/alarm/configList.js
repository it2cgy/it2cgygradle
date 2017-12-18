var ds = 499;
var measurementtypeList = [];  
$(function(){
	setSelectMenu("menualarmmanager","alarmmanager_config");
	 TableDatatablesButtons.init();
	 /**
	  * 增加用户
	  */
	 $("#addConfigButton").click(function(){ 
		  var param ="?a=1";
		 	if(window.ynz.admin){
		 		param  +="&admin=1";
		 	}
		 	
		 	if(window.ynz.longieb.powerStationId>0){
		 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
		 	} 
		  
		 window.location.href=basePath+"alarmPage/configAdd.do"+param;
	 });
	 $("#deleteSelButton").click(function(){ 
		 deleteSel();
	 });
	 

	 $('#equipmentType').change(function() {
    	 getPoints();
	 });
     
     var commonData = new CommonDatas();
     for(var da in commonData.powerStationEquipmentType){
    	 if(window.ynz.localType == "en_US"){
    		 commonData.powerStationEquipmentType[da].name = commonData.powerStationEquipmentType[da].english;
    	 }
    	 $('#equipmentType').append("<option value="+commonData.powerStationEquipmentType[da].id+">"+commonData.powerStationEquipmentType[da].name+"</option>");
     }
});

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
				var data = response.data;
				measurementtypeList = [];
				if(data!=null){
					for (var int = 0; int < data.length; int++) { 
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
					$('#measurementtype').append("<option value=''>---"+window.ynz.local.select_point_type+"---</option> ");
                	
					for(var da in measurementtypeList){
						$('#measurementtype').append("<option value="+measurementtypeList[da].measurementtypeId+">"+measurementtypeList[da].measurementtypeDescription+"</option>");
					} 
				} 
			},
			function(e){ 
				console.log("--------error------"+e);
			}
	)  
}
 

 

function searchdata(){
	var table = $('#sample_1').dataTable();//table id 
	table.fnDraw(false);
}

function deleteSel(){
	var ids = [];
	$(".checkboxes").each(function () {
		var checked = jQuery(this).is(":checked");
		if(checked){ 
			var id= jQuery(this).val();
			ids.push(id);
		}
	});
	if(ids.length<=0){
		message.alert( window.ynz.local.tip,window.ynz.local.user_deletetipsel,3,null,null);
		return;
	}
	
	var idsstr="";
	for (var i = 0; i < ids.length; i++){
		var id=ids[i];
		if(i==0){
			idsstr=id;
		}else{
			idsstr+=","+id;
		}
	}
	if(confirm("确定删除这些配置吗？")){
		if(ids.length>0){
//		 	message.alert( window.ynz.local.tip,window.ynz.local.user_deletetipsel,1,function (){
		 		ynzAjax.get(
		 	    		basePath+"alarmConfig/delete?ids="+idsstr,
		 	    		function(response){ 
		 	    			message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
								location.reload();
		 	    			});
		 		        },
		 		        function(e){ 
		 		            console.log("--------error------"+e);
		 		        });  
//		 	},null);
		}
	}
}

var ComponentsDateTimePickers = function () {

    var handleDatePickers = function () {

        if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl: App.isRTL(),
                orientation: "left",
                autoclose: true
            });
            //$('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
        }

        /* Workaround to restrict daterange past date select: http://stackoverflow.com/questions/11933173/how-to-restrict-the-selectable-date-ranges-in-bootstrap-datepicker */
    
        // Workaround to fix datepicker position on window scroll
        $( document ).scroll(function(){
            $('#form_modal2 .date-picker').datepicker('place'); //#modal is the id of the modal
        });
    }
  
    return {
        //main function to initiate the module
        init: function () {
            handleDatePickers(); 
        }
    };

}();

if (App.isAngularJsApp() === false) { 
    jQuery(document).ready(function() {    
        ComponentsDateTimePickers.init(); 
    });
}

/**
 * 表格初始化
 */
var TableDatatablesButtons = function () {
	var checkbox={
		sTitle:"<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'><input type='checkbox' class='group-checkable' data-set='#sample_1 .checkboxes' /><span></span></label>",
		className:"group-checkable", 
		render:function(data,type,row,meta){
			var  content ="";
			if(row.id!=ds&&row.id!=357){
				content = " <label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'> <input type='checkbox' class='checkboxes' value='"+ row.id +"' /> <span></span></label>";
			}
			return content;
		}
	};
	/**
	 * 初始化表格
	 */
    var initTable1 = function () {
        var table = $('#sample_1');//table id
        var oTable = table.dataTable({
        	"bAutoWidth":true,//设置自动计算列宽
        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
            "serverSide":true,//设置服务器端分页方式  false情况下默认使用前端插件分页
            "sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
            "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
            "bFilter":false,
            "ordering":false,
            /**
             * 指定显示列
             *  mDataProp 对应服务端字段名  
             *  sTitle 列展示名称
             *  visible true时显示当前列 设置false 隐藏当前列  不设置默认true
             *  sClass 对齐方式 
             */
           "aoColumns": [
               checkbox,
	           {"mDataProp":"equipmentcontainerName",
            	 "sTitle": window.ynz.local.index_equipmentname
               }, 
	           {"mDataProp":"measurePointDiscription", 
	        	   "sTitle": window.ynz.local.curve_pointname  
	           },
	           {"mDataProp":"type", 
	        	   "sTitle": window.ynz.local.calculation_type,
	        	   "render":function(data,type,row){//增加行内操作 
	        		   if(row.type == 1){
	        			   return window.ynz.local.greater_than_alarm_value;
	        		   }else if(row.type == 2) {
	        			   return window.ynz.local.less_than_alarm_value;
	        		   }else if(row.type == 3) {
	        			   return window.ynz.local.equal_to_alarm_value;
	        		   }else if(row.type == 4) {
	        			   return window.ynz.local.not_equal_to_alarm_value;
	        		   }
	        		   
	            	}
	           },
	           
	           {"mDataProp":"valueOne",
	        	   "sTitle":window.ynz.local.I_level
	           }, 
	           {"mDataProp":"valueTwo",
	        	   "sTitle": window.ynz.local.II_level
	           },
	           {"mDataProp":"valueThree",
	        	   "sTitle":window.ynz.local.III_level
	           },
	           {"mDataProp":"message",
		        	 "sTitle": window.ynz.local.inverter_alarminfo
		       },
	           {"mDataProp":"createDate",
	        	   "sTitle": window.ynz.local.user_createtime,
	        	   "sClass": "center", //列对齐方式等 
	        	   "render":function(data,type,row){//增加行内操作 
	        		   if(row.id == ds){
	        			   return "";
	        		   }else{
	        			   return timeUtil.timeToString(row.createDate,"yyyy-MM-dd");
	        		   }
	            	}
	           },
	           {"mDataProp":function(obj){
		    	   var _parmsmsg = obj.id;
		    	   var html ="--";
		    	   if(menuConfig==1){
		    		  html="<a href='"+window.ynz.basePath+"alarmPage/configEditList.do?powerStationId="+ window.ynz.longieb.powerStationId +"&id="+ obj.id +"'>"+ window.ynz.local.edit +"</a>";
		    	   }
		    	   return html;
		       },
		       "sTitle": window.ynz.local.action,
	        	 "sClass": "center" //列对齐方式等 
		       },
           ] ,
           /**
            * 设置右上角显示按钮 下列按钮为插件自带
            */
            buttons: [  
//                { extend: 'print', className: 'btn dark btn-outline' },
//                { extend: 'copy', className: 'btn red btn-outline' },
//                { extend: 'pdf', className: 'btn green btn-outline' },
//                { extend: 'excel', className: 'btn yellow btn-outline ' },
//                { extend: 'csv', className: 'btn purple btn-outline ' } ,
//                { extend: 'colvis', className: 'btn dark btn-outline', text: 'Columns'}//显示或隐藏某列
            ],
			"bLengthChange":false,//表格是否显示每页显示条数（5，10，15，20）设置为false时 用户无法自行更改页面显示条数
            responsive: false,//？？？？
            "order": [  //排序
                [0, 'asc']
            ],
//          "iDisplayLength":5,  //页面显示行数  
//          "sPaginationType": "full_numbers",  //翻页界面类型    太丑
            /**
             * 汉化
             */
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
	           		"excel":window.ynz.local.table_excel,
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
            /**
             * 每页显示条数
             */
	       "pageLength": 15,
            "lengthMenu": [
                [5, 10, 15, 20, -1],
                [5, 10, 15, 20, "All"] // 在这里可以变换没页显示条数
            ],
            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
        });
        
        table.find('.group-checkable').change(function () {
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
    }
    function retrieveData(sSource, aoData, fnCallback, oSettings ) {
    	var equipmentType  = $.trim($("#equipmentType").val());
    	var measurementtype  = $.trim($("#measurementtype").val());
    	var equipKey = $.trim($("#equipKey").val());
    	
    	var param = {
    			"equipmentType":equipmentType,
    			"measurementtype":measurementtype,
    			"equipKey":equipKey,
    			"powerstationid":window.ynz.longieb.powerStationId
    	};
    	
    	/**
    	 * 当前页码
    	 */
    	var page  = parseInt(Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength))+1;
    	/**
    	 * 每页显示条数
    	 */
    	var pagesize =  oSettings._iDisplayLength;
    	var path = basePath+"alarmConfig/configList.do?page="+page+"&pagesize="+pagesize ;
    	ynzAjax.post(
    		path,
    		param,
    		function(response){ 
            var data =response.results;
            console.log(data);
        	var returnData={};
        	returnData.aaData=data;//设置列表数据
        	returnData.iTotalRecords =response.counts;//设置总数
        	returnData.iTotalDisplayRecords =response.counts;//设置显示的数据总数  此属性需要与iTotalRecords相同 并且必须配置
        	returnData.iDisplayLength=pagesize;//设置每页显示条数
            fnCallback(returnData);  
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
    	)
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
function resetPsd(_id){
	$.ajax({  
    	 url: basePath+'user/resetPsd/'+_id+'.do',
		"dataType": 'json',  
        "type": "get",  
        "success" :function(response){ 
        	message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
//				 window.location.href=basePath+"compantUser/userListPage";
			});
//        	if(response.code==1){
//        		window.location.href=basePath+"compantUser/userListPage";
//        	}
        },
        "error" :function(e){ 
            console.log("--------error------"+e);
         }
    });  
}