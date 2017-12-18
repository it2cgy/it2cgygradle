$(function(){
	setSelectMenuRoot("menustationmanager");
	TableDatatablesButtons.init();
	$("#addPowerstation").click(function(){
		var param ="?a=1";
	 	if(window.ynz.admin){
	 		param  +="&admin=1";
	 	}
	 	
	 	if(window.ynz.longieb.powerStationId>0){
	 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
	 	}
		 window.location.href=basePath+"powerstation/addPowerstation.do"+param;
	});
});


/**
 * 表格初始化
 */
var TableDatatablesButtons = function () {
	/**
	 * 初始化表格
	 */
    var initTable1 = function () {
        var table = $('#powerstationTable');//table id
        var oTable = table.dataTable({
        	"bAutoWidth":true,//设置自动计算列宽
        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
            "serverSide":true,//设置服务器端分页方式  false情况下默认使用前端插件分页
            "sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
            "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
            "bFilter":false,
            /**
             * 指定显示列
             *  mDataProp 对应服务端字段名  
             *  sTitle 列展示名称
             *  visible true时显示当前列 设置false 隐藏当前列  不设置默认true
             *  sClass 对齐方式 
             */
           "aoColumns": [
               {"mDataProp":"id",
            	 "sTitle":  window.ynz.local.powerstation_ID,
            	 "visible":false
               },
	           {"mDataProp":"powerStationName",
            	 "sTitle": window.ynz.local.powerstation_name,
               },
               {"mDataProp":"powerstationCode",
              	 "sTitle": window.ynz.local.powerstation_number,
               },
	           {"mDataProp":"installCapacity", 
	        	   "sTitle":  window.ynz.local.powerstation_installed_capacity },
	           {"mDataProp":"address",
	        	 "sTitle": window.ynz.local.powerstation_addressinfo,
	        	 "sClass": "center" //列对齐方式等 
	           },
	           {"mDataProp":"investFirmContactName",
		        	 "sTitle":  window.ynz.local.powerstation_manager,
		        	 "sClass": "center" //列对齐方式等 
		       },
		       {"mDataProp":function(obj){
		    	   return timeUtil.timeToString(obj.createtime,"yyyy-MM-dd HH:mi");
		       },
		        	 "sTitle":  window.ynz.local.powerstation_createtime,
		        	 "sClass": "center" //列对齐方式等 
		       },
		       {"mDataProp":function(obj){
		    	   return obj.effectivity==1?window.ynz.local.powerstation_start:window.ynz.local.powerstation_stop;
		       },
		        	 "sTitle": window.ynz.local.powerstation_status,
		        	 "sClass": "center" //列对齐方式等 
		       },
		       {"mDataProp":"",
		        	 "sTitle": window.ynz.local.action,
		        	 "sClass": "center", //列对齐方式等 
	        		 "render":function(data,type,row){//增加行内操作
	        			var html = "";
	        			var param ="?powerId="+row.id;
	        		 	if(window.ynz.admin){
	        		 		param  +="&admin=1";
	        		 	}
	        		 	
	        		 	if(window.ynz.longieb.powerStationId>0){
	        		 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
	        		 	}
	        			if(menuConfig==1){
        			 		html+="<a href='javascript:;' onclick='updateStatus("+row.id+","+row.effectivity+");'>"+(row.effectivity==1?window.ynz.local.powerstation_stop:window.ynz.local.powerstation_start)+"</a>";
        			 	 	html+="&nbsp;&nbsp;|&nbsp;&nbsp;";
        			 	 	html+="<a href='"+basePath+"powerstation/editPowerstation"+ param +"'>"+window.ynz.local.edit+"</a>";
        			 	 	html+="&nbsp;&nbsp;|&nbsp;&nbsp;";
        			 	 	html+="<a href='javascript:;' onclick='deletePower("+row.id+");'>"+ window.ynz.local.del +"</a>";
        			 	}else{
        			 		html +="--";
        			 	}
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
//            "order": [  //排序
//                [0, 'asc']
//            ],
            "ordering":false,
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
    	
    	// 关键字
    	var searchkey=$("#searchkey").val();
    	var url=window.ynz.path+"/powerStation/getPowerstationList?page="+page+"&pagesize="+pagesize;
    	if(searchkey && searchkey!=""){
    		url = url+"&key="+searchkey;
    	}
    		
    	ynzAjax.get(
    			url,
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

function searchdata(){
	
	var table = $('#powerstationTable').dataTable();//table id 
	table.fnDraw(false);
	
//	 var table = $('#powerstationTable').dataTable();//table id
//	 table.load(null,true);
// 	var url = window.ynz.path+"/powerstation/powerstationList?admin=1";
//	var searchkey=$("#searchkey").val();
//	if(searchkey && searchkey!=""){
//		url = url+"&searchkey="+searchkey;
//	}
//	window.location.href=url;

}
function deletePower(_id){
	if(confirm(window.ynz.local.powerstation_confirmdelpower)){
		ynzAjax.post(
				window.ynz.path+'/powerStation/delPowerStation/'+_id+'.do',
				null,
				function(response){ 
					if(response.data==true){
						message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
							location.reload();
	 	    			});
					}else{
						message.alert( window.ynz.local.tip,window.ynz.local.powerstation_error,3,null,function(){
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
function updateStatus(_id,_effectivity){
	ynzAjax.post(
			window.ynz.path+'/powerStation/updateStatus/'+_id+'/'+Math.abs(parseInt(_effectivity)-1)+'.do',
			null,
			function(response){ 
				
				message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
				//message.alert( window.ynz.local.tip,"'"+(Math.abs(parseInt(_effectivity)-1)==1?"启用":"禁用")+"'成功",3,null,function(){
					if(response.data==true){
						var param ="?a=1";
					 	if(window.ynz.admin){
					 		param  +="&admin=1";
					 	}
					 	
					 	if(window.ynz.longieb.powerStationId>0){
					 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
					 	}
						window.location.href=window.ynz.path+"/powerstation/powerstationList"+param;
					}
				});
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
	);
}