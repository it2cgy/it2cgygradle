var myChart5,
    todyEleEchart,
    systemEchart;   
$(function(){ 
	 // ECHARTS
    require.config({
        paths: {
        	 echarts: window.ynz.cdnPath+'global/plugins/echarts/'
        }
    });
    setTimeout(function(){
    	window.onresize = function(){
    		if(myChart5){
    			myChart5.resize();
    		}
    		if(systemEchart){
    			systemEchart.resize();
    		}
    		if(todyEleEchart){
    			todyEleEchart.resize();
    		}
    	}
    },100)
})
function setOption(data){
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
            // -- PIE --
            myChart5 = ec.init(document.getElementById('echarts_pie'));
            myChart5.setOption({
                color:data.color,
                toolbox: {
                    feature : {
                        magicType : {
                            show: true, 
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'center',
                                    max: 1548
                                }
                            }
                        }
                    }
                },
                animation:false,
                series : [
                    {
                        name:'',
                        type:'pie',
                        radius : ['50%', '65%'],
                        itemStyle : {
                        	 normal: {
                                 label: {
                                     position: 'inner',
                                     formatter: function(params) {
//                                         return (params.percent - 0) + '%'
                                     }
                                 },
                                 labelLine: {
                                     show: false
                                 }
                             },
                             emphasis: {
                                 label: {
                                	 position : 'center',
                                     show: true,
                                     //formatter: "{b}\n{d}%"
                                     formatter: "{d}%"
                                 }
                             }
                        },
                        data:data.percent
                    }
                ]
            });
            
          //--by pjs start--
        	todyEleEchart =ec.init(document.getElementById("todyEleEchart"));
        	var eChartOption1 = {
    			legend: {
                    data: [data.lendname],
                    x:"right",
                    textStyle:{
        	    		color:"#82a3bc"
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
        	        data: data.time,
        	        name:window.ynz.local.index_time,
        	        nameLocation:"end",
        	        nameGap:"15",
        	        boundaryGap:true,
        	        nameTextStyle:{
        	        	color:"#82a3bc"
        	        },
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
        	    yAxis: {
        	        name:data.yname,
        	        nameTextStyle:{
        	        	color:"#82a3bc"
        	        },
        	        type:"value",      	
        	        splitLine:{
        	        	show:true,
        	        	lineStyle:{
            				color:"#13243e",
            				type:"dotted"
            			}        	        	
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
        	    series: [{
        	        name: data.lendname,
        	        type: data.type,
//        	        barWidth : 10,
        	        data: data.value
        	    }],
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
        	
        	
        	
        	
        	/*系统性能*/
        	    systemEchart =ec.init(document.getElementById("systemEchart"));
        		var eChartOption2 = {
        				title: {
        					text: '',
        					left:'center',
        					textStyle:{
        						color:"#253553",
        						textAlign:"center",
        						fontSize:"15",
        						lineHeight:"10",
        						marginBottom:"5",
        						"fontFamily":"微软雅黑"
        					}               
        				},
        				legend: {
                            data: [window.ynz.local.index_system_efficiency],
                            x:"right",
                            textStyle:{
                	    		color:"#82a3bc"
                	    	}
                        },
        				grid: {
        					left: '1%',
        					right: '1%',
        					bottom: '1%',
//        					x2:140,
//        					y2:150,
        					containLabel: true,
        					borderWidth:0
        				},
        				color:["#48bcc7"],
        				tooltip: {
        					trigger : 'axis'
        				},
        				animation : false,
        				xAxis: {
        					data: data.name,
        					name:window.ynz.local.powerstation_name,
        					nameLocation:"end",
        					nameGap:"5",
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
        						interval:'auto',
//        						rotate:30,
        						textStyle:{
        							color:"#0383a0"
        						}
        					}
        				},
        				yAxis: {
        					name:window.ynz.local.index_unit+"（%）",
//        					nameLocation:"end",
//        					nameGap:"25",
        					max:100,
        					min:0,
        					nameTextStyle:{
        						color:"#82a3bc"
        					},
        					type:"value",
        					splitLine:{
        						show:true,
        						lineStyle:{
        							color:"#13243e",
                    				type:"dotted"
        						}        	        	
        					},
        					axisLine:{
        						show:true,
        						onZero:false,
        						lineStyle:{
//        						type:"dotted"
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
        				series: [{
        					name: window.ynz.local.index_system_power,
        					type: 'bar',
        					barWidth : 20,
        					data: data.pr,
        				}],
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
//        				legend:{
//        					data:['系统性能'],
//        					bottom:15,
//        					x:"right"
//        				}    	    
        		};
        		
        	systemEchart.setOption(eChartOption2);	
        	//--by pjs end--   
            
        }
    );
	
}