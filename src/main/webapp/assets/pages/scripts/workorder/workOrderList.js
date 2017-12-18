$(function(){
	  setSelectMenu("workordermanager","workordermanager_list");
	  $('#nestable_list_menu').on('click', function (e) {
           var target = $(e.target),
               action = target.data('action');
           if (action === 'expand-all') {
        	   $("#faultDiv").show();
        	   $("#orderDiv").hide();
           }
           if (action === 'collapse-all') {
               $("#faultDiv").hide();
          	   $("#orderDiv").show();
        	   
           }
       });
	  order.orderlist(1).init();
	  $("#selectOrder").click(function(){
		  alert(1);
	  });
});


/**
 * 工单处理
 */
var order = {
		/**
		 * 加载工单列表
		 */
		orderlist : function(_status){
			/**
			 * 初始化表格
			 */
		    var initTable1 = function () {
		        var table = $('#orderTable');//table id
		        var oTable = table.dataTable({
		        	"bAutoWidth":true,//设置自动计算列宽
		        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
		            "serverSide":true,//设置服务器端分页方式  false情况下默认使用前端插件分页
		            "sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
		            "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
		            "bFilter":false,
		            "aoColumns": [
                       {"mDataProp":"orderId",
 		            	 "sTitle": window.ynz.local.order_id ,
 		            	 "visible":false //设置隐藏列
 		               },
			           {"mDataProp":"orderNumber",
		            	 "sTitle": window.ynz.local.order_number ,
		               },
		               {
		            	"mDataProp":function(obj){
		            		var dateTime = new Date();
		            		dateTime.setTime(obj.allocateTime)
		            	    return getDateFromTime(dateTime);
		               	},
		               	"sTitle":window.ynz.local.banish_time,
		               },
			           {"mDataProp":"maintenanceName", 
			        	 "sTitle": window.ynz.local.assigner
			           },
			           {"mDataProp":"powerstationName",
			        	 "sTitle": window.ynz.local.powerstation_info,
			        	 "sClass": "center" //列对齐方式等 
			           },
			           {"mDataProp":"remarks",
				        	 "sTitle": window.ynz.local.general,
				        	 "sClass": "center" //列对齐方式等 
				       },
				       {"mDataProp":"",
				        	 "sTitle": window.ynz.local.order_status,
				        	 "sClass": "center", //列对齐方式等 
			        		 "render":function(data,type,row){//增加行内操作
		        			 	var html ="<a href='"+basePath+"compantUser/editUser.do?userId="+row.id+"&flag=1'>"+window.ynz.local.edit+"</a>";
		        			 	 	html+="&nbsp;&nbsp;|&nbsp;&nbsp;";
		        			 	 	html+="<a href='"+basePath+"compantUser/editUser.do?userId="+row.id+"&flag=0'>"+window.ynz.local.view+"</a>";
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
			    	ynzAjax.get(
			    		basePath+"workOrderInfo/orderlist/"+_status+".do?page="+page+"&pagesize="+pagesize,
			    		function(response){ 
			            var data =response.results;
			        	var returnData={};
				        	returnData.aaData=data;//设置列表数据
				        	returnData.iTotalRecords =response.counts;//设置总数
				        	returnData.iTotalDisplayRecords =response.counts;//设置显示的数据总数  此属性需要与iTotalRecords相同 并且必须配置
				        	returnData.iDisplayLength=5;//设置每页显示条数
				            fnCallback(returnData);  
				        },
				        function(e){ 
				            console.log("--------error------"+e);
				        }
			    	)
			    } 
			    function getDateFromTime(_dateTime){
			    	var year  = _dateTime.getFullYear();
			    	var month = _dateTime.getMonth()+1;
			    	var date  = _dateTime.getDate();
			    	var hour  = _dateTime.getHours();
			    	var minute = _dateTime.getMinutes();
			    	var second = _dateTime.getSeconds();
			    	return year+"-"+month+"-"+date+"  "+hour+":"+minute+":"+second;
			    }
			    return {
			        init: function () {
			            if (!jQuery().dataTable) {
			                return;
			            }
			            initTable1();
			        }
			    };
}
		
		
		
}