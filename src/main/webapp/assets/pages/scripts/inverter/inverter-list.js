var page=1;
var pagesize =5;
var load=false;
$(function(){
	page=oldpage;
	pagesize=oldpagesize;
	var param ={};
	setSelectMenu("menuequipmonitor","menuinverterbox");
	TableDatatablesButtons.init();
	TableDatatablesButtons.general();
//	var table =$('#sample_1').DataTable();
//	table.page(2).draw(false);	
//	alert(1);
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
            "sAjaxSource": false,//插件自带ajax请求方式  需要写 但不使用
            "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
            "bFilter":false,//搜索
            "bSort":false,//排序
            "fnInitComplete":function(){//表格加载完成后执行
              	var table =$('#sample_1').DataTable();
            	table.page(oldpage-1).draw(false);
            },
            /**
             * 指定显示列
             *  mDataProp 对应服务端字段名  
             *  sTitle 列展示名称
             *  visible true时显示当前列 设置false 隐藏当前列  不设置默认true
             *  sClass 对齐方式 
             */
           "aoColumns": [
                {"mDataProp":"id",
            	 "sTitle": window.ynz.local.inverter_ID ,
            	 "sClass": "center" //列对齐方式等 
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
        	 page  = parseInt(Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength))+1;
        	/**
        	 * 每页显示条数
        	 */
        	 if(!load){
        		 page=oldpage;
        	 }
        	 pagesize =  oSettings._iDisplayLength;
        	 $(".portlet-body").empty();
        		param = {
        				'page':page,
        				'pagesize':pagesize,
        				'inverterId':inverterId,
        				'powerStationId':window.ynz.longieb.powerStationId
        			};
        		if(window.ynz.isThird==true || window.ynz.isThird==1){
        			param.isThirdUser="1";
        		}
        		getdata(fnCallback);
        		window.setInterval(getdata,60000,fnCallback);
    }  
    return {
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }
            initTable1();
        },
        general:function(){
        	var param = "";
        	if(window.ynz.isThird==true || window.ynz.isThird==1){
        		param="?isThirdUser=1";
    		}
        	
        	ynzAjax.get(
        	window.ynz.path+"/longiPowerStation/getEquipmentCounts/"+ window.ynz.longieb.powerStationId +".do"+param,  
            function(response){ 
        		$("#inverterSum").html(response.data.inverterCounts);
        		$("#testBox").html(response.data.measureBoxCounts);
        		$("#cocurrent").html(response.data.ammeter1Counts);
        		$("#singlePhase").html(response.data.ammeter2Counts);
        		$("#threePhase").html(response.data.ammeter3Counts);
        		load=true;
            }
        	);  
        },
        loadingData:function(result){
        	$(".portlet-body").html("");
        	console.log(result);
        	for (var i = 0; i < result.length; i++) {
        		var status = window.ynz.local.inverter_standby;
        		var imgName="status_stop";
        		if(result[i].equipmentStatus==1){
        			status =window.ynz.local.inverter_normal;
        			imgName = "status_green";
        		}
        		if(result[i].equipmentStatus==2){
        			status = window.ynz.local.inverter_fault;
        			imgName = "status_red";
        		}
				$(".portlet-body").append("<div class='inverterList'>"+
						"<div class='inverterBox'>"+
						"<div class='leftBox'>"+
						"<p class='subTitle'>"+result[i].name+"<em>"+result[i].serialNumber+"</em></p>"+
						"<a href='"+basePath+"inverterPage/inverterDetail/"+result[i].id+"?powerStationId="+window.ynz.longieb.powerStationId+"&page="+page+"&pagesize="+pagesize+"&oldId="+inverterId+"'><div class='imgBgTu' style='background: url(../assets/pages/css/ynz/common/backgroundImg/"+imgName+".png) no-repeat center;'></div></a>"+     //逆变器详情跳转
						"<ul>"+
						"<li><span>"+window.ynz.local.inverter_run_type+":</span><em>"+status+"</em></li>"+
						"<li><span>"+window.ynz.local.inverter_direct_power+":</span><em>"+toDecimal(result[i].totalInputPower,3)+"</em>kW</li>"+
						"<li><span>"+window.ynz.local.inverter_alternator_power+":</span><em>"+toDecimal(result[i].threePhaseActivePower,3)+"</em>kW</li>"+
						"<li><span>"+window.ynz.local.meter_power_factor+":</span><em>"+toDecimal(result[i].powerFactor,3)+"</em></li>"+
						"<li><span>"+window.ynz.local.inverter_temperature+":</span><em>"+toDecimal(result[i].temperature,3)+"</em>℃</li>"+
						"<li><span id='aa'>"+window.ynz.local.inverter_generating_days+":</span><em>"+toDecimal(result[i].generationDaliy,3)+"</em>kWh</li>"+
						"</ul>"+
						"</div>"+
						"<div class='rightBox'>"+
						"<div id='measBox"+i+"' class='measBox'>"+
						"<h3 class='subTitle'><em>"+result[i].measurementBoxSerialNumber+"</em></h3>"+
						"<div class='ammeters'>"+
						"<div id='ammeterTop"+i+"' class='ammeterTop'>"+
						"</div>"+
						
						"<dl id='ammeterBottom"+i+"' class='ammeterBottom'>"+
						"</dl>"+
						
						"</div>"+
						"</div>"+
						"<div class='directTable'>"+
						"<table border='0' cellspacing='0' cellpadding='0'>"+
						"<thead>"+
						"<tr id='meterTable"+i+"' class='meterTable'>"+
						"<th></th>"+
						"</tr>"+
						"</thead>"+
						"<tbody>"+
						"<tr id='power"+i+"' class='power'>"+
						"<td class='element'>"+window.ynz.local.inverter_power+"</br><em>(kW)</em></td>"+
						"</tr>"+
						"<tr id='generationSum"+i+"' class='generationSum'>"+
						"<td class='element'>"+window.ynz.local.inverter_generating_count+"</br><em>(kWh)</em></td>"+
						"</tr>"+
						"</tbody>"+
						"</table>"+
						"</div>"+
						"</div>"+
						"</div>"+
				"</div>");
				var list = result[i].ammeterList;
				var k = 0;
				for (var j = 0; j < list.length; j++) {
					if(list[j].ammeterType==1){
						$("#ammeterBottom"+i+"").append("<dl><dt>"+window.ynz.local.inverter_direct+"</dt>"+//"+list[j].name+"
								"<a href='"+basePath+"meter/meterDetail/"+list[j].id+"?powerStationId="+window.ynz.longieb.powerStationId+"&page="+page+"&pagesize="+pagesize+"'><dd class='directImg'></dd></a>"+			//跳转电表详情
								"<dd>"+list[j].name+"</dd></dl>");
						$("#meterTable"+i+"").append("<th>"+window.ynz.local.inverter_direct+""+list[j].name+"</th>");
						$("#power"+i+"").append("<td>"+toDecimal(list[j].power,3)+"</td>");
						$("#generationSum"+i+"").append("<td>"+toDecimal(list[j].generationGross,3)+"</td>");
						k++;
						
					}else if(list[j].ammeterType==2){
						$("#ammeterTop"+i+"").append("<dt>"+window.ynz.local.inverter_singlephase+"</dt>"+
							"<a href='"+basePath+"meter/meterDetail/"+list[j].id+"?powerStationId="+window.ynz.longieb.powerStationId+"&page="+page+"&pagesize="+pagesize+"'><dd class='exchangeImg'></dd></a>"+				//跳转电表详情
							"<dd>"+list[j].name+"</dd>");
						$("#meterTable"+i+"").append("<th>"+window.ynz.local.inverter_singlephase+""+list[j].name+"</th>");
						$("#power"+i+"").append("<td>"+toDecimal(list[j].power,3)+"</td>");
						$("#generationSum"+i+"").append("<td>"+toDecimal(list[j].generationGross,3)+"</td>");
					}else if(list[j].ammeterType==3){
						$("#ammeterTop"+i+"").append("<dt>"+window.ynz.local.inverter_threephase+"</dt>"+
							"<a href='"+basePath+"meter/meterDetail/"+list[j].id +"?powerStationId="+window.ynz.longieb.powerStationId+"&page="+page+"&pagesize="+pagesize+"'><dd class='exchangeImg'></dd></a>"+				//跳转电表详情
							"<dd>"+list[j].name+"</dd>");
						$("#meterTable"+i+"").append("<th>"+window.ynz.local.inverter_threephase+""+list[j].name+"</th>");
						$("#power"+i+"").append("<td>"+toDecimal(list[j].power,3)+"</td>");
						$("#generationSum"+i+"").append("<td>"+toDecimal(list[j].generationGross,3)+"</td>");
					}
				}
				if(k<=1){
					$("#ammeterBottom"+i+"").find("dl").addClass("oneAmmeter");
				}
			}
        },
        setPage : function (){
	       	 var table =$('#sample_1').DataTable();
	    	 table.page(2).draw(false);	
        }
        
    };
  

}();
function getdata(fnCallback){
	ynzAjax.post( 
        	window.ynz.path+"/longiPowerStation/getInverterInfo.do",  
        	param,
            function(response){ 
//					console.log(JSON.stringify(response));
					var data =response.results;
					TableDatatablesButtons.loadingData(data);
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
