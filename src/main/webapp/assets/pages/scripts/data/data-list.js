var tab = 1;
$(function(){
	setSelectMenuRoot("menudatalistmanager");
	 TableDatatablesButtons.init();
	 /**
	  * 增加曲线
	  */
	 $("#addUserButton").click(function(){
		 window.location.href=basePath+"dataPage/addData.do?powerStationId="+window.ynz.longieb.powerStationId;
	 });
	 /**
	  * 配置归一化
	  */
	 $("#configInstall").click(function(){
		 $("#stack1").show();
	 });
	 $("#cancelBtn").click(function(){
		 $("#stack1closBtn").click();
	 });
	 initInstall(1);
	 $("#saveRate").click(function(){
		 saveInstallRate();
	 });
	 $(".bootbox-close-button close").click(function(){
			$("#stack1closBtn").click();
			$("#saveRate").removeAttr("disabled");
   			$("#cancelBtn").removeAttr("disabled");
   			$("#saveRate").removeAttr("disabled");
   			$("#cancelBtn").removeAttr("disabled");
	 });
});
function searchCurveList(){
	$("#sample_1").DataTable().ajax.reload();
};


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
				  {"mDataProp":"",
				   "sTitle": window.ynz.local.curve_more,
				   "render":function(data,type,row){
        			 	 	return window.ynz.local.curve_more;
	            	  }
				   },
		           {"mDataProp":"name",
	            	 "sTitle": window.ynz.local.curve_name
	               },
	               {"mDataProp":"firstYaxisName",
	              	 "sTitle": window.ynz.local.curve_leftline
	               },
		           {"mDataProp":"secondYaxisName", 
		        	   "sTitle": window.ynz.local.curve_rightline 
		           },
		           {"mDataProp":"timeSpan",
		        	 "sTitle": window.ynz.local.timeSpan+"(m)",
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
				        	   return timeUtil.timeToString(row.createTime,"yyyy-MM-dd HH:mi");
				        }
				   },
				   {"mDataProp":"updateuser",
				        "sTitle":"修改人",
				        "sClass": "center"
				   },
				   {"mDataProp":"updateTime",
				        "sTitle": "修改时间",
				        "sClass": "center", //列对齐方式等 
				        "render":function(data,type,row){//增加行内操作 
				        	   return timeUtil.timeToString(row.updateTime,"yyyy-MM-dd HH:mi");
				        }
				   },
			       {"mDataProp":"",
			        	 "sTitle": window.ynz.local.action,
			        	 "sClass": "center", //列对齐方式等 
			        	 "render":function(data,type,row){//增加行内操作
			        		 	var html ="";
			        		 	if(menuConfig==1){
			        		 		html ="<a href='"+basePath+"dataPage/editData/"+row.id+".do?powerStationId="+ window.ynz.longieb.powerStationId +"'>"+ window.ynz.local.edit +"</a>";
		        			 	 	html+="&nbsp;&nbsp;|&nbsp;&nbsp;";
			        		 	}
		        			 	 	html+="<a href='"+basePath+"dataPage/dataDetail/"+row.id+".do?powerStationId="+ window.ynz.longieb.powerStationId +"'>"+ window.ynz.local.view +"</a>";
		        			 	 	if(menuConfig==1){
		        			 	 		html+="&nbsp;&nbsp;|&nbsp;&nbsp;";
		        			 	 		html+="<a href='javascript:;' onclick='deleteCurve("+row.id+");'>"+ window.ynz.local.del +"</a>"
		        			 	 	}
		        			 	 	return html;
			            	  }
			       },
		           {"mDataProp":"",
			        	 "sTitle": "",
			        	 "sClass": "none", //列对齐方式等 
		        		 "render":function(data,type,row){//增加行内操作
		        			 var point = row.pointList;
	        			 	var html = setChildTable(point);
	        			 	 	return html;
		            	  }
			       }
			       
	           ] ,
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
	            buttons: [
	              //  { extend: 'print', className: 'btn dark btn-outline' },
	              //  { extend: 'pdf', className: 'btn green btn-outline' },
	              //  { extend: 'csv', className: 'btn purple btn-outline ' }
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
	    	/**
	    	 * 当前页码
	    	 */
	    	var page  = parseInt(Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength))+1;
	    	/**
	    	 * 每页显示条数
	    	 */
	    	var pagesize =  oSettings._iDisplayLength;
	    	var createUser = $("#createUserCondition").val();
	    	var cuverName = $("#curveNameCondition").val();
	    	ynzAjax.post(
	    		basePath+"curveInfo/getCurveList.do",
	    		{"page":page,
	    		 "pagesize":pagesize,
	    		 "powerStationId":window.ynz.longieb.powerStationId,
	    		 "userName":createUser,
	    		 "name":cuverName},
	    		 //TODO
	    		function(response){ 
	            var data =response.results;
	            console.log(data);
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
        	setSelectMenu("menucustomlinemanager","customlinemanager_list");
            if (!jQuery().dataTable) {
                return;
            }
            initTable1();
        }
    };
}();
/**
 * 添加测点列表
 * @param point
 * @returns {String}html
 */
function setChildTable(point){
	var html = "<table class='table secondTable' style='table-layout:fixed;'>"+
    "<thead><tr><th class='min-tablet'>"+ window.ynz.local.equipment_type +"</th>"+
		    "<th class='min-tablet'>"+ window.ynz.local.equipment_num +"</th>"+
		    "<th class='min-tablet'>"+ window.ynz.local.curve_pointname +"</th>"+
            "<th class='min-tablet'>"+ window.ynz.local.curve_pointxy +"</th>"+
            "<th class='min-tablet'>"+ window.ynz.local.color +"</th></tr></thead>"+
     "<tbody>";
	for(var i=0;i<point.length;i++){
		html+="<tr><td>"+point[i].equipmentName+"</td>";
		html+="<td>"+point[i].equipmentNumber+"</td>";
		html+="<td>"+point[i].pointName+"</td>";
		if(point[i].yaxisType==0){
			html+="<td>"+ window.ynz.local.curve_leftline +"</td>"; 
		}else{
			html+="<td>"+ window.ynz.local.curve_rightline +"</td>";
		}
		html+="<td><div style='width: 30px;height: 15px;display:inline-block;background-color:"+point[i].colorCode+"' ></div>"+" "+point[i].colorCode+"</td></tr>";
	     
	}
        html+="</tbody>";
        return html;
}
function deleteCurve(curveId){
	if(confirm(window.ynz.local.curve_confirmdelcur)){
		ynzAjax.get( 
				   	 basePath+'curveInfo/deleteCurve/'+curveId+'.do',
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
/**
 * 切换装机容量设置tab页
 */
function changeTab(tabId){
	$("#installTbody").empty();
	tab = tabId;
	initInstall(tabId);
	switch (tabId) {
	case 1:
		$("#li_1").removeClass("installClass-iv").addClass("installClass");
		$("#li_0").removeClass("installClass").addClass("installClass-iv");
		$("#i_1").removeClass("colorBlack").addClass("colorWhite");
		$("#i_2").removeClass("colorWhite").addClass("colorBlack");
		break;
	case 2:
		$("#li_1").removeClass("installClass").addClass("installClass-iv");
		$("#li_0").removeClass("installClass-iv").addClass("installClass");
		$("#i_1").removeClass("colorWhite").addClass("colorBlack");
		$("#i_2").removeClass("colorBlack").addClass("colorWhite");
		break;
	default:
		break;
	}

}
function updateInstall(){
	$("#installTbody").find("input").removeAttr("readonly").removeAttr("style");
}
/**
 * 初始化装机容量表格
 */
function initInstall(tabId){
	tab = tabId;
	ynzAjax.get( 
		   	 basePath+'deviceRated/getAllRated.do?tabId='+tabId,
			 function(response){  
		   		 var data = response.data;
		   		 var html ='';
		   		 for(var i =0;i<data.length;i++){
		   			 html +='<tr>';
			   		 html +='<td><input id="rateId'+data[i].id+'" name="rateId" type="hidden" value='+data[i].id+'>'+data[i].equipmentDescription+'</td>';
			   		 html +='<td><input type="text" id=rateVal_'+data[i].id+' name="rateName" value='+data[i].ratedPower+'  onkeyup="this.value=this.value.replace(\/[^\\d.]\/g,\'\')" ng-pattern="/[^a-zA-Z]/" onchange="changeId(this)" style="border:0" readonly="readonly"/></td>';
			   		 html +='</tr>';
		   		 }
		   		$("#installTbody").append(html);
		      },
		     function(e){ 
		          console.log("--------error------"+e);
		      }
	);
}
function changeId(obj){
	var id = obj.id.split("_")[1];
	$("#rateId"+id).attr("name","uprateId");
	$("#rateVal_"+id).attr("name","uprateName");
}
/**
 * 保存装机容量
 */
function saveInstallRate(){
	var arr =[];
	var i = 0;
	$("#installTbody tr").each(function(index,element){
		var obj = {};
		var tdArr = $(this).children();
		if(tdArr.length==1){
			return true;
		}
		var id = tdArr.eq(0).find("input[name=uprateId]").val();
		
		var value = tdArr.eq(1).find("input[name=uprateName]").val();
		obj.id=id;
		obj.value=value;
		if(id!=undefined && id!="undefined"){
			arr[i]=obj;
			$("#rateId"+id).attr("name","rateId");
			$("#rateVal_"+id).attr("name","rateName");
			i++;
		}
	});
	
	var paramObj = {};
	paramObj.tabId = tab ;
	paramObj.rates = arr ;
	$("#installTbody").find("input").attr("readonly","readonly").attr("style","border:0");
	$("#saveRate").attr("disabled","disabled");
	$("#cancelBtn").attr("disabled","disabled");
	ynzAjax.post( 
		   	 basePath+'deviceRated/updateAllRate.do',
		     paramObj,
			 function(response){  
		   		 if(response.data){
		   			message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
		   				$("#stack1closBtn").click();
			   			$("#saveRate").removeAttr("disabled");
			   			$("#cancelBtn").removeAttr("disabled");
			   			$("#saveRate").removeAttr("disabled");
			   			$("#cancelBtn").removeAttr("disabled");
 	    			});
		   		 }
		      },
		     function(e){ 
		          console.log("--------error------"+e);
		      }
	);
}
