/**
 * 弹框方法
 */
var message = {
		/**
		 * title 弹出框显示标题
		 * message 弹出款提示
		 * _buttons
		 *  1  确认与取消按钮 success cancel是确认与取消的两个回调方法
		 *  2  删除与取消按钮 确认与删除按钮颜色不同
		 *  3  关闭按钮（带回调函数 传入cancel即可 success 传Null）
		 *  4  关闭按钮
		 *  success 点击确认或删除时执行的方法
		 *  cancel点击取消或关闭时执行的方法
		 */
		alert : function(_title,_message,_buttons,success,cancel){
			var val={}
		    switch(_buttons)
		      {
		          case 1:
			            val.success= {
			              label: window.ynz.local.sure,
			              className: "green",
			              callback: function() {
			            	  if(success){ 
				            	  success();
			            	  }
			              }
			            },
			            val.main= {
			              label: window.ynz.local.cancel,
			              className: "blue",
			              callback: function() {
			            	  if(cancel!=null){ 
			            		  cancel();
			            	  }
			              }
			           }
			           break;
		          case 2:
		        	   val.danger = {
			              label: window.ynz.local.del,
			              className: "red",
			              callback: function() {
			            	  if(success){ 
				            	  success();
			            	  }
			              }
			            },
			            val.main= {
			              label: window.ynz.local.cancel,
			              className: "blue",
			              callback: function() {
			            	  if(cancel!=null){ 
			            		  cancel();
			            	  }
			              }
			           }
			           break;
		          case 3:
			            val.main= {
			              label: window.ynz.local.closed,
			              className: "blue",
			              callback: function() {
			            	  if(cancel!=null){ 
			            		  cancel();
			            	  }
			              }
			           } 
			           break;
		          case 4:
		        	  val.main= {
			              label: window.ynz.local.closed,
			              className: "blue",
			              callback: function() {
			              }
			           } 
			           break;
			    };
			      
			 return bootbox.dialog({ title   : _title,message : _message,buttons : val});
		},
		confirm : function(_message,success,cancel){
			var flag = false;
			 bootbox.confirm({  
			        buttons: {  
			            confirm: {  
			                label: window.ynz.local.sure,  
			                className: "green"
			            },  
			            cancel: {  
			                label: window.ynz.local.cancel,  
			                className: "blue"
			            }  
			        },  
			        message: _message,  
			        callback: function(result) {  
			            if(result) {  
			            	success();
			            }else{
			            	cancel();
			            }
			        } 
			 });
			 return flag;
		}
}


/**
 * ajax公用
 */
var ynzAjax = {
		/**
		 * url  绝对路径访问地址
		 * _successfun 成功后回调方法
		 * _errorfun 异常回调
		 */
		get : function(_url,_successfun,_errorfun){
			 $.ajax({ 
				 	"headers":{
				 		"token":window.ynz.token,
				 		"fromType":"web"
				 	},
		        	"url": _url,
					"dataType": 'json',  
		            "type": "get",  
		            "success" :function(response){ 
		            	if(_successfun){
		            		_successfun(response);	
		            	}
		            },
		            "error" :function(e){ 
		            	if(_errorfun){
		            		_errorfun(e);	
		            	}
		             }
		        } ); 
		},
		/**
		 * url  绝对路径访问地址
		 * _data 参数对象 非字符串 例如 var obj={"user":123}
		 * _successfun 成功后回调方法
		 * _errorfun 异常回调
		 */
		post:function(_url,_data,_successfun,_errorfun){
			 $.ajax({ 
				 	"headers":{
				 		"token":window.ynz.token,
				 		"fromType":"web"
				 	},
		        	"url": _url,
					"dataType": 'json',  
		            "type": "post", 
		            "contentType" :'application/json',
		            "data":JSON.stringify(_data),//对象
		            "success" :function(response){ 
		            	if(_successfun){
		            		_successfun(response);	
		            	}
		            },
		            "error" :function(e){ 
		            	if(_errorfun){
		            		_errorfun(e);	
		            	}
		             }
		    } ); 
		}
		 
}

