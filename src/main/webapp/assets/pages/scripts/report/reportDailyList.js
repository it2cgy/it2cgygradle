var pointMap={};
$(function(){
	setSelectMenu("menureportmanager","report_everyday");
	TableDatatablesButtons.init();
	$("#cancelBtn").click(function(){
		$("#stack1closBtn").click();
	});
	ComponentsDateTimePickers.init();
	
//	$("#datetime").val("13:12");
	 $("#configTime").click(function(){
		 var datetime = $("#datetime").val();
		 var configId = $("#configId").val();
		 var configtype = $("input:radio[name='configtype']:checked").val();
		 ynzAjax.get(basePath+"reportDaily/configTime.do?datetime="+datetime+"&configId="+configId+"&configtype="+configtype, function(response){
			 if(response.message=="success"){
				 $("#stack1closBtn").click();
			 }
		 },
		 function(e){ 
		 });
	 });
});

var ComponentsDateTimePickers = function () {
    var handleTimePickers = function () {
        if (jQuery().timepicker) {
            $('.timepicker-24').timepicker({
                autoclose: true,
                minuteStep: 5,
                showSeconds: false,
                showMeridian: false
            });

            $('.timepicker').parent('.input-group').on('click', '.input-group-btn', function(e){
                e.preventDefault();
                $(this).parent('.input-group').find('.timepicker').timepicker('showWidget');
            });

            $( document ).scroll(function(){
                $('#stack1 .timepicker-default, #stack1 .timepicker-no-seconds, #stack1 .timepicker-24').timepicker('place'); //#modal is the id of the modal
            });
        }
    }
    return {
        init: function () {
        	handleTimePickers();
        }
    };

}();

/**
 * 表格初始化
 */
var TableDatatablesButtons = function () {
	/**
	 * 初始化表格
	 */
    var initTable1 = function () {
        var table = $('#reportDailyTable');//table id
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
               {"mDataProp":function(obj){
		    	   return window.ynz.local.report_everyday;
		       },
		        	 "sTitle": window.ynz.local.report_type,
		        	 "sClass": "center" //列对齐方式等 
		       },
		       {"mDataProp":function(obj){
		    	   return timeUtil.timeToString(obj.reporttime,"yyyy-MM-dd HH:mi");
		       },
		        	 "sTitle": window.ynz.local.report_time,
		        	 "sClass": "center" //列对齐方式等 
		       },
		       {"mDataProp":"",
		        	 "sTitle": window.ynz.local.action,
		        	 "sClass": "center", //列对齐方式等 
	        		 "render":function(data,type,row){//增加行内操作
	        			var html = "";
	        			
	        				html+="<a href='javascript:;' onclick='downLoadwordreport("+row.id+");'>"+window.ynz.local.download+"</a> ";
	        			if(menuConfig==1){
        			 		html+="| <a href='javascript:;' onclick='deletereport("+row.id+");'>"+window.ynz.local.del+"</a>";
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
    	var url=window.ynz.path+"/reportDaily/getReportDailyInfo";
    	ynzAjax.post(
			url,
			{
				'page':page,
				'pagesize':pagesize
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

function deletereport(id){
	if(confirm("确定删除该每日报表吗？")){
		ynzAjax.get(
				window.ynz.path+'/reportDaily/deleteReportDaily/'+id+'.do',
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
function downLoadwordreport(id){
	ynzAjax.get(
			window.ynz.path+'/reportDaily/getReportById/'+id+'.do',
			function(response){
				if(response.data!=null){
					var form = $("<form>");
					form.attr('style','display:none');
					form.attr('target','');
					form.attr('method','post');
					form.attr('action',basePath+"reportDaily/downLoadword.do");
					var input = $('<input>');
					input.attr('type','hidden');
					input.attr('name','id');
					input.attr('value',id);
					$('body').append(form);
					form.append(input);
					form.submit();
				}
			},
			function(e){ 
				console.log("--------error------"+e);
			}
	);
}
