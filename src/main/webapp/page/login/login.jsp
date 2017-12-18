<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta name="renderer" content="webkit">
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title>${login_longieb_logintitle}</title>
		<!-- start css style by pjs-->
		 <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="${basePath}assets/global/img/faviconlongi.ico" /> </head>
<link href="${basePath}assets/pages/css/ynz/common/static.css?version=20170817024514" rel='stylesheet' type='text/css'  />
<link href="${basePath}assets/pages/css/ynz/login/login.css?version=20170817024514" rel='stylesheet' type='text/css'  />
		<!--end css style by pjs-->
	 
	</head>
	  <!-- END HEAD -->
	<body>
		<div class="loginPage">
			<h1 class="logo"><a href="###"><img src="${basePath}assets/pages/img/ynz/common/logo.png"/></a></h1>
			<h2>${login_longieb_title}</h2>
			<!--登录start-->
			<div class="relaTive">
				<div class="loginBox">
					<form action="" method="post">
					 <div id="tmp" style="display: none">
<!--                                     防止浏览器自动填充用户密码 -->
<!--                                    		 <input type="text" id="n" disabled/> -->
                                    	<input type="password" id="p" disabled/>
                        </div>
						<ul class="loginModel">
							<li class=""><span>${login_longieb_username}</span><input type="text" id="pvmts_username" name="pvmts_username" placeholder="${login_please_username}"  onkeydown="if(event.keyCode==13){$('#loginSys').click()}"/></li>
							<li class="pass"><span>${login_longieb_password}</span><input type="password" id="pvmts_password" name="pvmts_password" placeholder="${login_please_password}"  onkeydown="if(event.keyCode==13){$('#loginSys').click()}"/></li>
						</ul>
						<div id="" class="choice">
							<i class="remberPass" id="remberPass"></i><span>${login_remember_password}</span><a class="forgetPass" onclick="tipforget();">${login_forget_password}</a>
						</div>
						<button type="button" class="loginBtn" id="loginSys">${login_longieb_login}</button>					
					</form>					
				</div><!--登录end-->
				<!--提示弹框start-->
				<div class="tipBox" id="tip" style="display:none">
					<h4><span>${tip}</span><em class="close"></em></h4>
					<dl>
						<dt></dt>
						<dd>${login_error_login}</dd>
						<a class="confirmBtn" href="javascript:;" onclick="closeTab()">${affirm}</a>
					</dl>
				</div><!--提示弹框end-->
				<!--忘记密码弹框start-->
				<div class="tipBox" id="tip2" style="display:none">
					<h4><span >${tip}</span><em class="close"></em></h4>
					<dl>
						<dt></dt>
						<dd>${login_longieb_administrtor}</dd>
						<a class="confirmBtn" href="javascript:;" onclick="closeTab()">${affirm}</a>
					</dl>
				</div><!--忘记密码弹框end-->
				<!-- 二维码start -->
				<div class="downloadBox">
					<li class="androidPic">
						<a href="javascript:;"><img src="${basePath}assets/pages/img/ynz/Android.png" alt="Android"></a>
						<div class="downloadPic1" style="position: absolute;left: -145px;display:none;"><img style="width: 120px" src="${basePath}${downloadAppurl}" alt="download"></div>
					</li>
					<li class="iosPic">
						<a href="javascript:;"><img src="${basePath}assets/pages/img/ynz/IOS.png" alt="IOS"></a>
						<div class="downloadPic2" style="position: absolute;right:-145px;display:none;"><img style="width: 120px" src="${basePath}${downloadAppurl}" alt="download"></div>
					</li>	
				</div>
				
				<!-- 二维码end -->
			</div>
		</div>
	</body>
<script src="${cdnPath}global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${basePath}/assets/global/ynz/tools.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}assets/pages/scripts/login/login.js?version=20170817024514" type='text/javascript'></script>
</html>
