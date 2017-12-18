var map={};
var myChart;
$(function(){
	$('#select1').multiSelect({checkAllText:""});
	// ECHARTS
    require.config({
        paths: {
            echarts: window.ynz.cdnPath+'global/plugins/echarts/'
        }
    });
	setSelectMenu("menuequipmonitor","menuivequipment");
	TableDatatablesButtons.init();
	getbaseInfo();
	 window.onresize = function(){
		 setTimeout(function(){
				if(myChart){
					myChart.resize()
				}
			},100);
    }
}); 
var colors=["#36C6D3","#3598dc","#26c281","#d91e18","#525e64","#e7505a","#9a12b3","#f3c200"];
var selectData = [];
$("#stack1closBtn").click(function(){
    selectData = $('#select1').val();
    if(!selectData || selectData==""){
    	selectData = [];
    }
	$('#select1').val(selectData);
	$('#select1').multiSelect("refresh");
});

$("#cancelBtn").click(function(){
	$("#stack1closBtn").click();
});

$("#checkdata").click(function(){ 
    selectData = $('#select1').val();
    if(!selectData || selectData==""){
    	selectData = [];
    }
    getPowerInfo(selectData)
    $("#stack1closBtn").click();
});

function getbaseInfo(){
		ynzAjax.get(
				basePath+"ivEquipment/getIvEquipmentInfos?powerstationId="+window.ynz.longieb.powerStationId+"&equipmentType=10&pointType=1123",
				function(response){ 
					var html = "";
					var select = [];
					for(var i in response.data){
						var da = response.data[i]
						if(window.ynz.longieb.roleId==4){
							for(var ii in thirdEquipments){
								if(thirdEquipments[ii].equipmentType==10&&da.equipmentcontainerName==thirdEquipments[ii].number){
									html+="<option value="+da.id+">"+da.equipmentcontainerName+"</option>";
									if(select.length<=0){
				            			selectData.push(da.equipmentcontainerName);
				            			select.push(da.id)
				        			}
									map[da.id]=da.equipmentcontainerName;
								}
							}
						}else{
							if(i==0){
								select.push(da.id);
							}
							if(select.length<=0){
		            			selectData.push(da.equipmentcontainerName);
		        			}
							html+="<option value="+da.id+">"+da.equipmentcontainerName+"</option>";
							map[da.id]=da.equipmentcontainerName;
						}
					}
					$('#select1').empty();
	        		$("#select1").append(html);
	        		$('#select1').val(select);
	        		$('#select1').multiSelect("refresh");
	        		 getPowerInfo(select);
					var timer = window.setInterval(getPowerInfo,TimeOutTime,$('#select1').val());
				},
				function(e){ 
					console.log("--------error------"+e);
				}
		)
}
/**
 * 曲线数据转化成纵横坐标轴数组
 * @param historyDatas
 * @returns {___anonymous13869_13871}
 */
function getPointData(historyDatas){
	var res = new Object();
	res.time=[];
	res.value=[];
	if(historyDatas.length==0||historyDatas.length==1){
		res.time.push(timeUtil.timeToString(new Date(),"yyyy-MM-dd HH:mi"));
		res.value.push(0);
	}else{
		for(var i=0;i<historyDatas.length;i++){
			for(var j=0;j<historyDatas[i].historyDatas.length;j++){
				res.time.push(timeUtil.timeToString(historyDatas[i].historyDatas[j].time,"yyyy-MM-dd HH:mi"));
				res.value.push(toDecimal(historyDatas[i].historyDatas[j].value,3));
			}
		}
	}
	return res;
}
function getPowerInfo(pointIds){
	var pointData =""+pointIds ;
	var pointArr = pointData.split(",");
	var startstr = timeUtil.dateToString(new Date(),"yyyy-mm-dd")+" 00:00:00";
	var endstr = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss");
	ynzAjax.post(//电表当天功率历史
			window.ynz.basePath+"stationmonitor/historyRedress/points.do",
			{   "analoginputIds":pointData,
				"startTime":startstr,//"2017-08-28 00:00:00",
				"endTime":endstr,//"2017-08-30 23:59:00",
				"minutesSpan":5,
				"haveReal":1
			},
			function(response){
				var legendData = [];
    			var time = []; 
    			var seriesLine=[];
				var out = [];
				var dataTableStr ="";
				for(var i = 0;i<response.data.length;i++){
    				var serieDatas=[]; 
    				var seriesLineData={} 
    				var pointName ="";
    				var colorindex = i%7;
    				var color = colors[colorindex];
    				legendData[i]=map[response.data[i].analoginputId];
    				seriesLineData.name=map[response.data[i].analoginputId];
    				seriesLineData.type="line";
    				seriesLineData.itemStyle={"normal":{"color":color}}; 
    				if(response.data[i].historyDatas.length==0||response.data[i].historyDatas.length==1){
    					if(response.data[i].historyDatas.length==0){
//    						if(time.length==0){
//    							time.push(timeUtil.timeToString(new Date(),"yyyy-MM-dd HH:mi"));
//    						}
//    						serieDatas.push(0);
    					}else{
    						if(response.data[i].historyDatas[0].time==0){
    							if(time.length==0){
    								time.push(timeUtil.timeToString(new Date(),"yyyy-MM-dd HH:mi"));
    							}
    							serieDatas.push(0);
    						}else{
    							if(time.length==0){
    								time.push(timeUtil.timeToString(response.data[i].historyDatas[0].time,"yyyy-MM-dd HH:mi"));
    							}
    							serieDatas.push(toDecimal(response.data[i].historyDatas[0].value,3));
    						}
    					}
    				}else{
	    				for(var j=0;j<response.data[i].historyDatas.length;j++){ 
	    					var timeStr = timeUtil.timeToString(response.data[i].historyDatas[j].time,"yyyy-MM-dd HH:mi");
	    					var value = response.data[i].historyDatas[j].value;
	    					time[j]=timeStr;
	    					serieDatas[j]=toDecimal(value,3); 
	    				} 
    				}
    				seriesLineData.data=serieDatas;
    				seriesLine[i]=seriesLineData;
    			}
				setEchars(legendData,time,seriesLine);
			},
			function(e){ 
				console.log("--------error------"+e);
			}
	);
}



function setEchars(_legendData,_xdata,_series){
	// DEMOS
    require(
        [
            'echarts',
            'echarts/chart/bar',
            'echarts/chart/chord',
            'echarts/chart/eventRiver',
            'echarts/chart/force',
            'echarts/chart/funnel',
            'echarts/chart/gauge',
            'echarts/chart/heatmap',
            'echarts/chart/k',
            'echarts/chart/line',
            'echarts/chart/map',
            'echarts/chart/pie',
            'echarts/chart/radar',
            'echarts/chart/scatter',
            'echarts/chart/tree',
            'echarts/chart/treemap',
            'echarts/chart/venn',
            'echarts/chart/wordCloud'
        ],
        function(ec) {
            myChart = ec.init(document.getElementById("electLine")).setOption({
            	 tooltip: {
 		            trigger: 'axis'
 		        },
 		        legend: {//显示的数据列
 		            data:_legendData,
 		           padding:[30,0,0,0],
 		        },
                toolbox: {
                    show: true,
                    feature: {
                        dataZoom : {show: true},
        	            restore : {show: true},
                        restore: {
                            show: true
                        },
                        saveAsImage: {
                            show: true
                        },
                        
                    }
                },
 		        calculable: true,
 		        animation:false,
 		        xAxis: [{//x轴
 		            type: 'category',
 		            data: _xdata
 		        }],
 		        yAxis: [{"name":"（w）",
						"splitArea":{
							show:true
						},
						"type":"value",
						"position":"left"
					
 		        }],
 		        series:_series
            });
        }
    );
}

/**
 * 表格初始化
 */
var TableDatatablesButtons = function () {
	/**
	 * 初始化表格
	 */
    var initTable1 = function () {
        var table = $('#ivtable');//table id
        var oTable = table.dataTable({
        	"bAutoWidth":true,//设置自动计算列宽
        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
            "serverSide":true,//设置服务器端分页方式  false情况下默认使用前端插件分页
            "sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
            "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
            "bFilter":false,
            "paging":false,
            /**
             * 指定显示列
             *  mDataProp 对应服务端字段名  
             *  sTitle 列展示名称
             *  visible true时显示当前列 设置false 隐藏当前列  不设置默认true
             *  sClass 对齐方式 
             */
           "aoColumns": [
	            {"mDataProp":"name",
            	 "sTitle": window.ynz.local.iv_number 
                },
                {"mDataProp":"vVal",
               	 "sTitle": window.ynz.local.iv_voltage+"（V）"
                },
	            {"mDataProp":"aVal", 
	        	    "sTitle": window.ynz.local.iv_current+"（A）"
	        	},
	        	 {"mDataProp":"wVal",
	               	 "sTitle": window.ynz.local.iv_current_power+"（W）"
	            },
	            {"mDataProp":"dayVal",
	                  	 "sTitle": window.ynz.local.iv_generating_day+"（kWh）"
	             },
	             {"mDataProp":"monthVal",
                  	 "sTitle": window.ynz.local.iv_generating_mouth+"（kWh）"
	             },
	             {"mDataProp":"yearVal",
	              	 "sTitle": window.ynz.local.iv_generating_year+"（kWh）"
	             },
	             {"mDataProp":"totalVal",
	              	 "sTitle": window.ynz.local.iv_generating+"（kWh）"
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
	        "pageLength": 16,
            "lengthMenu": [
//                [16, 15, 20, -1],
//                [16, 15, 20, "All"] // 在这里可以变换没页显示条数
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
    	var ids = "";
    	if(window.ynz.longieb.roleId==4){
			for(var ii in thirdEquipments){
				if(thirdEquipments[ii].equipmentType==10){
					ids+=thirdEquipments[ii].number+","
				}
			}
    	}
    	var pagesize =  oSettings._iDisplayLength;
    	ynzAjax.get(
    		basePath+"ivEquipment/getIvEquipments?powerstationId="+window.ynz.longieb.powerStationId+"&page="+page+"&pagesize="+pagesize+"&ids="+ids.substring(0,ids.length-1),
    		function(response){ 
	            var data =response.results;
			    for(var ii in data){
	            	for(var iii in data[ii]){
	            		if(iii!="name"){
	            			data[ii][iii]=toDecimal(data[ii][iii],3);
	            		}
	            	}
	            }
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