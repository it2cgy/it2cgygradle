$(function(){
	setSelectMenuRoot("equipmentmanager");
	 TableDatatablesButtons.init();
});
/**
 * 表格初始化
 */
var TableDatatablesButtons = function () {
	 var initTable1 = function () {
	        var table = $('#sample_1');

	        var oTable = table.dataTable({
	        	"bAutoWidth":true,//设置自动计算列宽
	        	"bLengthChange":false,
	        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
	            "serverSide":true,//设置服务器端分页方式  false情况下默认使用前端插件分页
	            "sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
	            "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
	        	"bLengthChange":false,//表格是否显示每页显示条数（5，10，15，20）设置为false时 用户无法自行更改页面显示条数
	            "responsive": true,//？？？？
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
		           {"mDataProp":"equipmentType",
	            	 "sTitle": window.ynz.local.equipment_type ,
	            	 "sClass": "center" //列对齐方式等 
	               },
	               {"mDataProp":"equipmentModel",
	              	 "sTitle": window.ynz.local.equipment_model ,
	              	 "sClass": "center" //列对齐方式等 
	               },
		           {"mDataProp":"equipmentNumber", 
		        	   "sTitle":window.ynz.local.equipment_num ,
		        	   "sClass": "center" //列对齐方式等 
		           },
		           {"mDataProp":"factory",
		        	 "sTitle": window.ynz.local.equipment_company,
		        	 "sClass": "center" //列对齐方式等 
		           },
		           {"mDataProp":"nickname",
			        	 "sTitle": window.ynz.local.user_createuser,
			        	 "sClass": "center" //列对齐方式等 
			       },
			       {"mDataProp":"createTime",
			        	 "sTitle": window.ynz.local.user_createtime,
			        	 "sClass": "center", //列对齐方式等 
			        	 "render":function(data,type,row){//增加行内操作 
		       			 		return timeUtil.timeToString(row.createTime,"yyyy-MM-dd");
			            	}
			        },
		           {"mDataProp":"powerstationname",
		        	   "sTitle": window.ynz.local.powerstationname,
		        	   "sClass": "center" //列对齐方式等 
		           },
			       {"mDataProp":"",
			        	 "sTitle": window.ynz.local.action,
			        	 "sClass": "center", //列对齐方式等 
			        	 "render":function(data,type,row){//增加行内操作
			        		 	var html ="";
			        		 	var param ="?a=1";
			        		 	if(window.ynz.admin){
			        		 		param  +="&admin=1";
			        		 	}
			        		 	
			        		 	if(window.ynz.longieb.powerStationId>0){
			        		 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
			        		 	}
		        		 	    html="<a href='"+basePath+"equipmentPage/equipmentDetail/"+row.id+".do"+ param +"'>"+ window.ynz.local.view +"</a>";
			        			if(menuConfig==1){
			        				html+="&nbsp;&nbsp;|&nbsp;&nbsp;";
		        			 		html+="<a href='"+basePath+"equipmentPage/equipmentEdit/"+row.id+".do"+ param +"'>"+ window.ynz.local.edit +"</a>";
		        			 		html+="&nbsp;&nbsp;|&nbsp;&nbsp;";
		        			 	 	html+="<a href='javascript:;' onclick='deleteEquipment("+row.id+");'>"+ window.ynz.local.del +"</a>"
		        			     }
		        			 	 return html;
			            	  }
			       }
			       
	           ] ,
	           "pagingType": "bootstrap_full_number", // pagination type(bootstrap, bootstrap_full_number or bootstrap_extended)
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
		           	"iListLength":10,
		           	"sProcessing": window.ynz.local.table_sProcessing, 
		         },
		         buttons: [  
		                        ],
	            responsive: {
	                details: {
	                   
	                }
	            },
	            "order": [
	                [0, 'asc']
	            ],
	            
	            "lengthMenu": [
	                [5, 10, 15, 20, -1],
	                [5, 10, 15, 20, "All"] // change per page values here
	            ],
	            // set the initial value
	            "pageLength": 15,
	            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
	        });
	    }
	 function retrieveData(sSource, aoData, fnCallback, oSettings ) {
		 	var sourse = $("#sourse").val();
	    	/**
	    	 * 当前页码
	    	 */
	    	var page  = parseInt(Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength))+1;
	    	/**
	    	 * 每页显示条数
	    	 */
	    	var powerStationId = 0;
	    	if(isSubStation){
	    		powerStationId = window.ynz.longieb.powerStationId;
	    	}
	    	var pagesize =  oSettings._iDisplayLength;
	    	ynzAjax.post(
	    		basePath+"equipment/getEquipmentList.do",
	    		{"page":page,
	    		 "pagesize":pagesize,
	    		 "powerStationId":window.ynz.longieb.powerStationId,
	    		 "sourse":sourse},
	    		 //TODO
	    		function(response){ 
	            var data =response.results;
	        	var returnData={};
	        	returnData.aaData=data;//设置列表数据
	        	returnData.iTotalRecords =response.counts;//设置总数
	        	returnData.iTotalDisplayRecords =response.counts;//设置显示的数据总数  此属性需要与iTotalRecords相同 并且必须配置
	        	returnData.iDisplayLength= pagesize;//设置每页显示条数
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
function sourcedata(){
	$("#sample_1").DataTable().ajax.reload();
};

function adddata(){
	var param ="?a=1";
 	if(window.ynz.admin){
 		param  +="&admin=1";
 	}
 	
 	if(window.ynz.longieb.powerStationId>0){
 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
 	}
 	
	var url=basePath+"equipmentPage/equipmentAdd.do"+param; 
		window.location.href=url;
};
function deleteEquipment(id){
	if(confirm(window.ynz.local.equipment_confirmdelequi)){
		ynzAjax.get( 
			   	 basePath+'equipment/deletEquipment/'+id+'.do',
				 function(response){ 
			   		message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
						location.reload();
 	    			});
			     },
			     function(e){ 
			          console.log("--------error------"+e);
			      }
			     );
	}
}