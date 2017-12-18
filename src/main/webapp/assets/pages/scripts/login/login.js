$(function(){
	
			var uname = window.localStorage.getItem('pvmts_username');
			var upass = window.localStorage.getItem('pvmts_password');
			if(uname!="" && uname!=null){
				$("#pvmts_username").val(uname);
				$("#pvmts_password").val(upass);
				$("#remberPass").addClass("clickPic");
			}
			//记住密码切换
			var flag=true;
			$("#remberPass").click(function(){				 
				if(flag){					
					$(this).addClass("clickPic");
					flag=false;
				}else{					
					$(this).removeClass("clickPic");
					flag=true;
				}				
			});
			$(".close").click(function(){
				$(".tipBox").hide();
			});
			$("#loginSys").click(function(){
				login();
			});
			 
			/**
			 * 登录系统 
			 */
			function login(){
				var userName = $("#pvmts_username").val();
				var password = $("#pvmts_password").val();
				if(""==userName){
					$("#tip2_dd").html(window.ynz.local.login_error_username);
					$("#tip2").show();
				}
				if(""==password){
					$("#tip2_dd").html(window.ynz.local.login_error_password);
					$("#tip2").show();
				}
				var obj = {};
				obj.username = userName;
				obj.password = password;
				var url = window.ynz.basePath+"user/login.do";
				ynzAjax.post(
						url,
						obj,
						function(data) {
							if(data.code=="1"){
								var storage = window.localStorage;
//								if($("#remberPass").hasClass("clickPic")){
								if(!flag){
									//记住密码
									storage.setItem('pvmts_username',userName);
									storage.setItem('pvmts_password',password);
								}else{
								 	storage.setItem('pvmts_username',"");
									storage.setItem('pvmts_password',"");
								}
								window.location.href =window.ynz.basePath+"index";
							}else{
								$("#tip").show();
							}
						},
						function(msg) {
							console.log(msg);
						}
				);
			}
});


function closeTab(){
	console.log(2);
	$("#tip").hide();
	$("#tip2").hide();
}
function tipforget(){
	$("#tip2").show();
}