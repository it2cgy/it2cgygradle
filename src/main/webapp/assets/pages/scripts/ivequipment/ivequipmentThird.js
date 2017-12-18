var pointId = "1706285843,1706285850";
var pointMap=[];
$(function(){
	 setSelectMenu("menuequipmonitor","menuivequipment");
	 TableDatatablesButtons.init();
	 getPowerInfo();
}); 

/**
 * 曲线数据转化成纵横坐标轴数组
 * @param historyDatas
 * @returns {___anonymous13869_13871}
 */
function getPointData(historyDatas){
	var res = new Object();
	res.time=[];
	res.value=[];
	for(var i=0;i<historyDatas.length;i++){
		res.time.push(timeUtil.timeToString(historyDatas[i].time,"yyyy-MM-dd HH:mi"));
		res.value.push(toDecimal(historyDatas[i].value,3))
	}
	return res;
}
function getPowerInfo(){
	var pointMap = {};
	var startstr = timeUtil.dateToString(new Date(),"yyyy-mm-dd")+" 00:00:00";
	var endstr = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss");
	ynzAjax.post(//电表当天功率历史
			window.ynz.basePath+"stationmonitor/historyRedress/points.do",
			{   "analoginputIds":pointId,
				"startTime":startstr,
				"endTime":endstr,
				"minutesSpan":5,
				"haveReal":1
			},
			function(response){
				var legendData = [];
    			var time = [];
    			var seriesBar=[];
    			var seriesLine=[];
				var out = [];
				var dataTableStr ="";
    			for(var i = 0;i<response.data.length;i++){
    				var serieDatas=[];
    				var seriesBarData={};
    				var seriesLineData={}
    				var pointInfo = response.data[i].analoginputId;
    				var pointName ="channel-15";
    				if(response.data[i].analoginputId==1706285843){
    					pointName="channel-15";
    				}else{
    					pointName="channel-16";
    				}
    				var color = pointInfo.colorCode;
    				legendData[i]=pointName;
    				seriesLineData.name=pointName;
    				seriesLineData.type="line";
    				seriesLineData.itemStyle={"normal":{"color":color}};
    				seriesBarData.name=pointName;
    				seriesBarData.type="bar";
    				seriesBarData.itemStyle={"normal":{"color":color}};
    				for(var j=0;j<response.data[i].historyDatas.length;j++){ 
    					var timeStr = timeUtil.timeToString(response.data[i].historyDatas[j].time,"yyyy-MM-dd HH:mi");
    					var value = response.data[i].historyDatas[j].value;
    					time[j]=timeStr;
    					serieDatas[j]=toDecimal(value,3);
    					out.push( [ pointName, timeStr, value] );
    				}
    				seriesLineData.data=serieDatas;
    				seriesBarData.data=serieDatas;
    				seriesBar[i]=seriesBarData;
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
	 var myChart = echarts.init(document.getElementById('electLine'));
	    myChart.setOption({
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
	        xAxis: [{//x轴
	            type: 'category',
	            data: _xdata
	        }],
	        yAxis: [{//y轴
	            type: 'value',
	            splitArea: {
	                show: true
	            }
	        }],
	        series:_series
	    });
	    if(myChart.getDataURL()!=null){
	    	columeImage = myChart.getDataURL();
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
        var table = $('#ivtable');//table id
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
	            {"mDataProp":"name",
            	 "sTitle": window.ynz.local.iv_number 
                },
                {"mDataProp":"vVal",
               	 "sTitle": window.ynz.local.iv_voltage
                },
	            {"mDataProp":"aVal", 
	        	    "sTitle": window.ynz.local.iv_current
	        	},
	        	 {"mDataProp":"wVal",
	               	 "sTitle": window.ynz.local.iv_current_power
	            },
	            {"mDataProp":"dayVal",
	                  	 "sTitle": window.ynz.local.iv_generating_day
	             },
	             {"mDataProp":"monthVal",
                  	 "sTitle": window.ynz.local.iv_generating_mouth
	             },
	             {"mDataProp":"yearVal",
	              	 "sTitle": window.ynz.local.iv_generating_year
	             },
	             {"mDataProp":"totalVal",
	              	 "sTitle": window.ynz.local.iv_generating
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
    	var equipmentIds = "15,16";
    	var pagesize =  oSettings._iDisplayLength;
    	ynzAjax.get(
    		basePath+"ivEquipment/getIvEquipmentThird?powerstationId="+window.ynz.longieb.powerStationId+"&equipmentIds="+equipmentIds,
    		function(response){ 
	            var data =response.data;
	            console.log(JSON.stringify(data))
	            for(var ii in data){
	            	for(var iii in data[ii]){
	            		if(iii!="name"){
	            			data[ii][iii]=toDecimal(data[ii][iii],3);
	            		}
	            	}
	            }
	        	var returnData={};
	        	returnData.aaData=data;//设置列表数据
	        	returnData.iTotalRecords =response.data.length;//设置总数
	        	returnData.iTotalDisplayRecords =response.data.length;//设置显示的数据总数  此属性需要与iTotalRecords相同 并且必须配置
	        	returnData.iDisplayLength=5;//设置每页显示条数
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