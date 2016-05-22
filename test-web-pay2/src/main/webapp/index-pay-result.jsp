<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312" />
<title>微信支付</title>
<meta name="description" content="微信支付" />
<meta name="keywords" content="微信支付" />
<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href = "${pay_info}"><button class="new-btn-login" id="subBtn" type="button"></button></a>

<script type="text/javascript">
	$(function(){
		$("#subBtn").trigger("click");
	});
</script>
</body>
</html>
