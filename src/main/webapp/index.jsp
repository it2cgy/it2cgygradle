<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="i18n" uri="http://www.yunengzhe.com/ynz/tag" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("path", path);
	pageContext.setAttribute("basePath", basePath);  
%> 
     <script type="text/javascript">
		window.location.href='${basePath}/index'
	</script>
<body>  
</body>
</html>