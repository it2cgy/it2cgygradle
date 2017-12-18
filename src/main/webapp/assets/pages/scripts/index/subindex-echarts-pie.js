var todyEleEchart;
$(function(){ 
	 // ECHARTS
    require.config({
        paths: {
        	 echarts: window.ynz.cdnPath+'global/plugins/echarts/'
        }
    });
    setTimeout(function(){
    	if(todyEleEchart){
    		window.onresize = function(){
    			todyEleEchart.resize();
    		}
    	}
    },100)
})
function setOption(curveData){
	 // DEMOS
    require(
        [
            'echarts',
            'echarts/config',
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
          //--by pjs start--
        	todyEleEchart =ec.init(document.getElementById("todyEleEchart"));
        	var eChartOption1 = {
    			/*legend: {
                    data: [window.ynz.local.index_today],
                    x:"right",
                    textStyle:{
        	    		color:"#82a3bc"
        	    	}
                },*/
                toolbox:{
                	show:true,
                	feature:{
                		magicType:{
                			show:true,
                			title:{
                        		line:window.ynz.local.echar_line,
                        		bar:window.ynz.local.echar_bar
                        	},
                			type:['line','bar']
                        }
                	}
                },
        	    grid: {
        	        left: '1%',
        	        right: '1%',
        	        bottom: '1%',
        	        containLabel: true,
        	        borderWidth:0
            	},
        	    color:["#49bb5c"],
        	    tooltip: {
        	    	trigger : 'axis'
        	    	/*axisPointer : {
        	    		animation : false
        		    }*/
        	    },
        	    animation : false,
        	    xAxis: {
        	        data: curveData.date,
        	        name:window.ynz.local.index_time,
        	        nameLocation:"end",
        	        nameGap:"15",
        	        nameTextStyle:{
        	        	color:"#82a3bc"
        	        },
        	        boundaryGap:true,
        	        splitLine:{
        	        	show:false,
        	        	lineStyle:{
        	        		color:"red"
        	        	}
        	        },
        	    	axisTick:{
        	    		show:false
        	    	},
        	    	axisLine:{
        	    		show:true,
        	    		onZero:false,
        	    		lineStyle:{
        	    			borderWidth:"1px",
        	    			color:"#2e354b"
        	    		}
        	    	},
        	    	axisLabel:{
        	    		textStyle:{
        	    			color:"#0383a0"
        	    		}
        	    	}
        	    },
        	    yAxis: curveData.yaxisData,
        	    series:curveData.point,
        	    axisLine:{
        	    	show:false,
        			lineStyle:{
        				color:"red"
        			}
        	    },
        	    textStyle:{
        	    	color:"red"
        	    },
        	    splitLine:{
        	    	show:false
        	    }
        	};
        	todyEleEchart.setOption(eChartOption1);
        	todyEleEchart.on("magicTypeChanged",function(param){
        		if(line==0){//功率
        			if(param.magicType.line){
            			echarType1="line";
            		}else{
            			echarType1="bar";
            		}
        		}else{//发电量
        			if(param.magicType.line){
            			echarType="line";
            		}else{
            			echarType="bar";
            		}
        		}
        	});
        }
    );
	
}