$(function(){
	 setSelectMenuRoot("rolemanager");
	 TableDatatablesButtons.init();
	 /**
	  * 增加橘色
	  */
	 $("#addButton").click(function(){ 
		 var param ="?a=1";
		 	if(window.ynz.admin){
		 		param  +="&admin=1";
		 	}
		 	
		 	if(window.ynz.longieb.powerStationId>0){
		 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
		 	} 
		 window.location.href=basePath+"sync/addSyncPage.do"+param;
	 });
 
	 
});

function searchdata(){
	var table = $('#sample_1').dataTable();
	table.fnDraw(false);
}
 

var ComponentsDateTimePickers = function () {

    var handleDatePickers = function () {

        if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl: App.isRTL(),
                orientation: "left",
                autoclose: true
            });
        }
        $( document ).scroll(function(){
            $('#form_modal2 .date-picker').datepicker('place'); //#modal is the id of the modal
        });
    }
    return {
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
			var  content = " <label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'> <input type='checkbox' class='checkboxes' value='"+ row.id +"' /> <span></span></label>";
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
//               checkbox,
	           {"mDataProp":"name",
            	 "sTitle": window.ynz.local.sync_name
               }, 
	           {"mDataProp":"startDate",
	        	   "sTitle": window.ynz.local.sync_startdate,
	        	   "sClass": "center", //列对齐方式等 
	        	   "render":function(data,type,row){//增加行内操作 
       			 		return timeUtil.timeToString(row.startDate,"yyyy-MM-dd");
	            	}
	           }, 
	           {"mDataProp":"endDate",
	        	   "sTitle": window.ynz.local.sync_enddate,
	        	   "sClass": "center", //列对齐方式等 
	        	   "render":function(data,type,row){//增加行内操作 
       			 		return timeUtil.timeToString(row.endDate,"yyyy-MM-dd");
	            	}
	           }, {"mDataProp":"status",
	        	   "sTitle": window.ynz.local.sync_status,
	        	   "sClass": "center", //列对齐方式等 
	        	   "render":function(data,type,row){//增加行内操作 
       			 		return row.status==100?window.ynz.local.sync_finish:(window.ynz.local.sync_running+" "+row.status+"%");
	            	}
	           },
	           {"mDataProp":"nickname",
	        	   "sTitle": window.ynz.local.user_createuser,
	        	   "sClass": "center"  //列对齐方式等 
	        	  
	           },
	           {"mDataProp":"createDate",
	        	   "sTitle": window.ynz.local.sync_create,
	        	   "sClass": "center", //列对齐方式等 
	        	   "render":function(data,type,row){//增加行内操作 
       			 		return timeUtil.timeToString(row.createDate,"yyyy-MM-dd HH:mi");
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
            "pageLength": 15,
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
    	var rolenamekey  = $.trim($("#rolenamekey").val());
    	/**
    	 * 当前页码
    	 */
    	var page  = parseInt(Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength))+1;
    	/**
    	 * 每页显示条数
    	 */
    	var pagesize =  oSettings._iDisplayLength;
    	ynzAjax.get(
    		basePath+"sync/getSyncList.do?name="+rolenamekey+"&page="+page+"&pagesize="+pagesize,
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
 

function resetSearch(){
	$("#rolenamekey").val("");
	$("#sample_1").DataTable().ajax.reload();
}