$(function(){
	setSelectMenu("companyusrmanager","companyusrmanager_list");
	//setSelectMenu("logmanager","logmanager_list");
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
        var table = $('#sample_1');//table id
        var oTable = table.dataTable({
        	"bAutoWidth":true,//设置自动计算列宽
        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
            "serverSide":true,//设置服务器端分页方式  false情况下默认使用前端插件分页
            "sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
            "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
            /**
             * 指定显示列
             *  mDataProp 对应服务端字段名  
             *  sTitle 列展示名称
             *  visible true时显示当前列 设置false 隐藏当前列  不设置默认true
             *  sClass 对齐方式 
             */
           "aoColumns": [
               { "mDataProp":"orderId",  "sTitle": "工单id",
            	   "visible":true, //设置隐藏列
            	   "render":function(data,type,row){//增加行内操作
            		   var html="<a href='###'>查看</a>";
            		   return html;
            	   }
            	},
	           {"mDataProp":"orderNumber",
            	 "sTitle": "工单编号" 
               },
	           {"mDataProp":"createTime", 
	        	   "sTitle": "创建时间" },
	           {"mDataProp":"powerstationName",
	        	 "sTitle": "电站名称",
	        	 "sClass": "center" //列对齐方式等 
	           }
           ] ,
           /**
            * 设置右上角显示按钮 下列按钮为插件自带
            */
            buttons: [  
                { extend: 'print', className: 'btn dark btn-outline' },
                { extend: 'copy', className: 'btn red btn-outline' },
                { extend: 'pdf', className: 'btn green btn-outline' },
                { extend: 'excel', className: 'btn yellow btn-outline ' },
                { extend: 'csv', className: 'btn purple btn-outline ' } ,
                { extend: 'colvis', className: 'btn dark btn-outline', text: 'Columns'}//显示或隐藏某列
            ],
			"bLengthChange":false,//表格是否显示每页显示条数（5，10，15，20）设置为false时 用户无法自行更改页面显示条数
            responsive: true,//？？？？
            "order": [  //排序
                [0, 'asc']
            ],
//          "iDisplayLength":5,  //页面显示行数  
//          "sPaginationType": "full_numbers",  //翻页界面类型    太丑
            /**
             * 汉化
             */
            "oLanguage": {                              
                "sLengthMenu": "每页显示 _MENU_ 条记录",     
                "sZeroRecords": "没有检索到数据",     
                "sInfo": "当前数据为从第 _START_ 到第 _END_ 条数据；总共有 _TOTAL_ 条记录",     
                "sInfoEmtpy": "没有数据",     
                "sProcessing": "正在加载数据...",     
                "oPaginate": {     
                    "sFirst": "首页",     
                    "sPrevious": "前页",     
                    "sNext": "后页",     
                    "sLast": "尾页"    
                },
                "buttons":{
                	"print":"打印",
                	"copy":"复制",
                	"pdf":"存储为Pdf",
                	"excel":"表格",
                	"csv":"csv",
                	"colvis":"显示/隐藏列"
                }
            }     ,
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
    	
        $.ajax({  
        	"url": "../workOrderInfo/orderlist/1.do?page="+page+"&pagesize="+pagesize,  
			"dataType": 'json',  
            "type": "get",  
//          "contentType": "application/json",  
//          "data": JSON.stringify({"username":"yw003","password":"123"}),  
            "success" :function(response){ 
	            var data =response.results;
	        	var returnData={};
	        	returnData.aaData=data;//设置列表数据
	        	returnData.iTotalRecords =response.counts;//设置总数
	        	returnData.iTotalDisplayRecords =response.counts;//设置显示的数据总数  此属性需要与iTotalRecords相同 并且必须配置
	        	returnData.iDisplayLength=5;//设置每页显示条数
	            fnCallback(returnData);  
            },
            "error" :function(e){ 
                console.log("--------error------"+e);
             }
        } );  
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