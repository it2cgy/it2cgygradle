$(function(){
	setSelectMenuRoot("logmanager");
	 TableDatatablesButtons.init();
//	 $('#powerStationId').change(function() {
//     	var id = $('#powerStationId').val();
//     	editEqument(id);
//     })
	 $('#equipmentId').change(function() {
     	editEqument($('#powerStationId').val(),$(this).val());
     })
});
function searchdata(){
	$("#sample_1").DataTable().ajax.reload();
}
function editEqument(powerstationid,equipmentId){
	if(equipmentId==""){
		$('#equipment').empty();
		$('#equipment').append("<option value=''>---"+window.ynz.local.curve_pleaseselectequipmentnumber+"---</option>");
	}else{
		ynzAjax.get(
				window.ynz.path+"/pointInfo/getEquipments.do?powerStationId="+thirdPowerStationId+"&equipmentType="+equipmentId,
				function(response){ 
					var data = response.data;
					$('#equipment').empty();
					$('#equipment').append("<option value=''>---"+window.ynz.local.curve_pleaseselectequipmentnumber+"---</option>");
					for (var int = 0; int < data.length; int++) {
						if(equipmentId==6||equipmentId==8){
							$('#equipment').append("<option value="+data[int].equipmentcontainerName+">"+data[int].equipmentcontainerName+"</option>");
						}else{
							if(window.ynz.longieb.roleId!=4){
								$('#equipment').append("<option value="+data[int].equipmentcontainerName+">"+data[int].equipmentcontainerName+"</option>");
							}else{
								for(var ii in thirdEquipments){
									if(data[int].equipmentId==thirdEquipments[ii].equipmentId&&data[int].equipmentTableId==thirdEquipments[ii].equipmentType){
										$('#equipment').append("<option value="+data[int].equipmentcontainerName+">"+data[int].equipmentcontainerName+"</option>");
									}
								}
							}
						}
					}
				},
				function(e){ 
					console.log("--------error------"+e);
				}
		);
	}
}
function addlogs(){
	var param ="?a=1";
 	if(window.ynz.admin){
 		param  +="&admin=1";
 	}
 	
 	if(window.ynz.longieb.powerStationId>0){
 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
 	}
 	
	var url=window.ynz.path+"/logsPage/toLogsAdd.do"+param;  
 
	location.href=url;
};
function toEditLogsPage(id){ 
	var param ="?a=1";
 	if(window.ynz.admin){
 		param  +="&admin=1";
 	}
 	
 	if(window.ynz.longieb.powerStationId>0){
 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
 	}
 	
	var url=window.ynz.path+"/logsPage/toLogsEdit/"+id+".do"+param; 
	 
	location.href=url;
	
};
function toDetailLogsPage(id){ 
	var param ="?a=1";
 	if(window.ynz.admin){
 		param  +="&admin=1";
 	}
 	
 	if(window.ynz.longieb.powerStationId>0){
 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
 	}
	var url=window.ynz.path+"/logsPage/toLogsDetail/"+id+".do"+param; 
 
	location.href=url;
};
function dispose(id){ 
	var param ="?a=1";
	if(window.ynz.admin){
		param  +="&admin=1";
	}
	
	if(window.ynz.longieb.powerStationId>0){
		param += "&powerStationId="+window.ynz.longieb.powerStationId;
	}
	var url=window.ynz.path+"/logsPage/toLogsDispose/"+id+".do"+param; 
	
	location.href=url;
};
function deletelog(id){
	if(confirm(window.ynz.local.log_dellog)){
	ynzAjax.get(
			window.ynz.path+"/logsInfo/deleteLogs/"+id,
			function(response){ 
				if(response.data==true){
					message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
						location.reload();
 	    			});
				}else{
					message.alert( window.ynz.local.tip,window.ynz.local.action_error,3,null,function(){
						location.reload();
 	    			});
				}
			},
			function(e){ 
				console.log("--------error------"+e);
			}
		);
	}
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
        	"bLengthChange":true,
        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
            "serverSide":true,//设置服务器端分页方式  false情况下默认使用前端插件分页
            "sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
            "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
        	"bLengthChange":false,//表格是否显示每页显示条数（5，10，15，20）设置为false时 用户无法自行更改页面显示条数
        	"responsive": false,//？？？？ 显示不开的列会隐藏为+
        	"bProcessing":true,
            "bFilter":false,//搜索
            "bSort":false,//排序
            "order": [  //排序
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
                {"mDataProp":"id",
            	 "sTitle": window.ynz.local.log_ID ,
            	 "sClass": "center" //列对齐方式等 
               },
               {"mDataProp":"topic",
 	        	  "sTitle": window.ynz.local.log_topic,
 	        	  "sClass": "center" //列对齐方式等 
 	           },
 	           {"mDataProp":"categoryName",
 	        	   "sTitle": window.ynz.local.log_category,
 	        	   "sClass": "center" //列对齐方式等 
 	           },
	           {"mDataProp":"powerStationName",
	        	  "sTitle": window.ynz.local.log_powerStation,
	        	  "sClass": "center" //列对齐方式等 
	           },
        	   {"mDataProp":"companyName",
        		   "sTitle": window.ynz.local.log_company,
        		   "sClass": "center" //列对齐方式等 
        	   },
        	   {"mDataProp":"equipmentName",
        		   "sTitle": window.ynz.local.log_equipment,
        		   "sClass": "center" //列对齐方式等 
        	   },
//        	   {"mDataProp":function(obj){
//	        		   var html = "";
//	        		   if(obj.description.length>16){
//	        			   html = "<div style='width:98%;height:20px;overflow-y:scroll;overflow-x:hidden'>"+obj.description+"</div>";
//	        		   }else{
//	        			   html = obj.description;
//	        		   }
//	        		   return html;
//        	   		},
//        	   "sTitle":window.ynz.local.log_description,
//		       "sClass": "center", //列对齐方式等
//		       "sWidth":"20%"
//		       },
		       {"mDataProp":"nickname",
		    	   "sTitle": window.ynz.local.log_write_username,
		    	   "sClass": "center" //列对齐方式等 
		       },
		       {"mDataProp":function(obj){
		    	   return timeUtil.timeToString(obj.createTime,"yyyy-MM-dd HH:mi");
		       },
		       "sTitle": window.ynz.local.log_write_time,
		       "sClass": "center" //列对齐方式等 
		       },
    		   { "mDataProp":"id",  "sTitle": window.ynz.local.action,
            	   "visible":true, //设置隐藏列
            	   "render":function(data,type,row){//增加行内操作
            		   var jsp= "";
            		   jsp="<div style='white-space:nowrap'><a onclick='toDetailLogsPage("+row.id+")'>"+ window.ynz.local.view +"</a>";
            		   if(menuConfig==1){
            			   if(row.logStatus==0){
            				   jsp+=" | <a onclick='toEditLogsPage("+row.id+")'>"+ window.ynz.local.edit +"</a>";
            				   jsp +=" | <a onclick='dispose("+row.id+")'>"+ window.ynz.local.log_dispose +"</a> | <a onclick='deletelog("+row.id+")'>"+window.ynz.local.del+"</a>";
            			   }else{
            				   jsp+=" | "+window.ynz.local.log_processed+"| <a onclick='deletelog("+row.id+")'>"+window.ynz.local.del+"</a>";
            			   }
            		   }
            		   jsp+="</div>";
            		   return jsp;
            	   }
            	}, 
           ] ,
           /**
            * 设置右上角显示按钮 下列按钮为插件自带
            */
            buttons: [  
                //{ extend: 'print', className: 'btn dark btn-outline' },
               // { extend: 'copy', className: 'btn red btn-outline' },
               // { extend: 'pdf', className: 'btn green btn-outline' },
               // { extend: 'excel', className: 'btn yellow btn-outline ' },
               // { extend: 'csv', className: 'btn purple btn-outline ' } ,
                //{ extend: 'colvis', className: 'btn dark btn-outline', text: 'Columns'}//显示或隐藏某列           
                ],
 
//          "iDisplayLength":15,  //页面显示行数  
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
	        	"oPaginate":{
	           		"sFirst":window.ynz.local.table_sfirst,
	           		"sPrevious":window.ynz.local.table_sprevious,
	           		"sNext":window.ynz.local.table_snext,
	           		"sLast":window.ynz.local.table_slast
	           	},
	           	"sProcessing": window.ynz.local.table_sProcessing, 
	              },
	        "pageLength": 15,
            /**
             * 每页显示条数
             */
            "lengthMenu": [
                [5, 10, 15, 20, -1],
                [5, 10, 15, 20, "All"] // 在这里可以变换没页显示条数
            ],
            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
        });
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
    	var category = $("#category").val();
    	var powerStationId = $("#powerStationId").val();
    	var equipment = $("#equipment").val();
    	var equipmentId = $('#equipmentId').val();
    	var topic = $("#topic").val();
    	var discription = $("#discription").val();
    	var isThird=0;
//    	 if(window.ynz.isThird==true || window.ynz.isThird==1){
//    		 isThird = "1";
//    	 }
    	var url ="";
    	if(window.ynz.longieb.roleId!=4){
    		url = window.ynz.path+"/logsInfo/selectLogsSearch.do";
    	}else{
    		url = window.ynz.path+"/logsInfo/selectThirdLogsSearch.do";
    	}
    	ynzAjax.post(  
        	url,  
			{
				'page':page,
				'pagesize':pagesize,
				'topic':topic,
				'equipment':equipment,
				'equipmentId':equipmentId,
				'powerStationId':powerStationId,
				'category':category,
				'isThird':isThird,
				'discription':discription
			},
            function(response){ 
	            var data =response.results;
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
        );  
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