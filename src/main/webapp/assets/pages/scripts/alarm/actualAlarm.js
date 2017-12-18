var rowId = 0; 
$(function(){
	 setSelectMenu("menualarmmanager","alarmmanager_alarmreal");
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
        var table = $('#actualTable');//table id
        var oTable = table.dataTable({
        	"bAutoWidth":true,//设置自动计算列宽
        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
            "serverSide":true,//设置服务器端分页方式  false情况下默认使用前端插件分页
            "sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
            "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
            "bFilter":false,
            "bProcessing":true,
            /**
             * 指定显示列
             *  mDataProp 对应服务端字段名  
             *  sTitle 列展示名称
             *  visible true时显示当前列 设置false 隐藏当前列  不设置默认true
             *  sClass 对齐方式 
             */
           "aoColumns": [
			   {"mDataProp":"id",
				 "sTitle":  window.ynz.local.alarm_id
			   },
	           {"mDataProp":"name",
            	 "sTitle": window.ynz.local.powerstationname
               },
               {"mDataProp":function(obj){
		    	   return timeUtil.timeToString(obj.eventTime,"yyyy-MM-dd HH:mi");
		       },
		        	 "sTitle":  window.ynz.local.alarm_alarmtime,
		        	 "sClass": "center" //列对齐方式等 
		       },
	           {"mDataProp":"alarmNumber", 
	        	   "sTitle": window.ynz.local.alarm_equipment },
        	   {"mDataProp":"measurePointDiscription", 
	        	   "sTitle": window.ynz.local.report_sightname },
	           {"mDataProp":"alarmMessage",
	        	 "sTitle": window.ynz.local.alarm_fault,
	        	 "sClass": "center" //列对齐方式等 
	           },
	           {"mDataProp":"alarmValue",
		        	 "sTitle":  window.ynz.local.alarm_value,
		        	 "sClass": "center" //列对齐方式等 
		       },
		       {"mDataProp":function(obj){
		    	   var result;
		    	   switch (obj.alarmGrade) {
		    	   case 1:
		    		   result="I";
		    		   break;
		    	   case 2:
		    		   result="II";
		    		   break;
		    	   default:
		    		   result="III";
		    		   break;
		    	   }
		    	   return result;
		       },
		        	 "sTitle": window.ynz.local.alarm_level,
		        	 "sClass": "center" //列对齐方式等 
		       },
		       {"mDataProp":"",
		        	 "sTitle": window.ynz.local.action,
		        	 "sClass": "center", //列对齐方式等 
	        		 "render":function(data,type,row){//增加行内操作
        			 	var html ="<a href='"+window.ynz.basePath+"alarmPage/affirmAlarm.do?id="+ row.id +"&powerStationId="+window.ynz.longieb.powerStationId+"'><font>"+ window.ynz.local.alarm_confirm +"</font></a>";// onclick='confirmalarm("+row.id+")
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
            responsive: false,//？？？？
            "order": [  //排序
                [0, 'asc']
            ],
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
                             [15, 20, -1],
                             [15, 20, "All"] // 在这里可以变换没页显示条数
                         ],
            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
        });
    }
    function retrieveData(sSource, aoData, fnCallback, oSettings ) {
    	var queryKey = $.trim($("#queryKey").val());
    	var equipmentName = $.trim($("#equipmentName").val());
    	var pointName = $.trim($("#pointName").val());
    	/**
    	 * 当前页码
    	 */
    	var page  = parseInt(Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength))+1;
    	/**
    	 * 每页显示条数
    	 */
    	var pagesize =  oSettings._iDisplayLength;
//    	var searchStr = "";
//    	if(queryKey!=""){
//    		searchStr = "&search="+encodeURI(encodeURI(queryKey));
//    	}
//    	var equipmentNameStr = "";
//    	if(equipmentName!=""){
//    		equipmentNameStr = "&equipmentName="+encodeURI(encodeURI(equipmentName));
//    	}
//    	var pointNameStr = "";
//    	if(pointName!=""){
//    		pointNameStr = "&pointName="+encodeURI(encodeURI(pointName));
//    	}
    	ynzAjax.post(
    		basePath+"alarmInfo/getAlarmListBySearchPost",
    		{
    			"powerStationId":window.ynz.longieb.powerStationId,
    			"status":0,
    			"page":page,
    			"pagesize":pagesize,
    			"search":encodeURI(queryKey),
    			"equipmentName":encodeURI(equipmentName),
    			"pointName":encodeURI(pointName),
    		},
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
	            console.log("--------error------"+JSON.stringify(e));
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

/**
 * basePath+"alarmInfo/confirmAlarmById/"+row.id+"
 * @returns
 */
function messageConfirmalarm(){
	var message = $("#messageContent").val();
	console.log(message);
	console.log(rowId);
	ynzAjax.post(
			basePath+"alarmInfo/msgConfirmAlarmById.do",
			{
				"id":rowId,
				"message":message
			},
			function(response){
				message.alert( window.ynz.local.tip,window.ynz.local.alarm_confirmsuccesss,3,null,function (){
					location.reload();
				});
			},
			function(e){ 
				console.log("--------error------"+e);
			}
		)
	}
//function confirmalarm(_id){
//	rowId = _id;
////	if(confirm("您确定确认这条报警吗？")){
////		ynzAjax.get(
////				basePath+"alarmInfo/confirmAlarmById/"+_id,
////				function(response){
////					alert("确认成功");
////					location.reload();
////				},
////				function(e){ 
////					
////					console.log("--------error------"+e);
////				}
////		)
////	}
//}
/**
 * 查询
 */
function queryData(){
	$("#actualTable").DataTable().ajax.reload();
}
