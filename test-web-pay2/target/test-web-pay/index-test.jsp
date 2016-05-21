<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<title>威富通微信WAP支付测试页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0  topMargin=4>
	<div id="main">
        <div class="cashier-nav">
            <ol>
				<li class="current">选择功能 </li> 
            </ol>
        </div>
        <form action="testQuery" method="post"  target="_blank">
            <div id="body" style="clear:left">
                <dl class="content">
                    <dt>支付测试：</dt>
					<dd>
						<a href="index-pay.jsp">点此</a>
					</dd>
                </dl>
                <dl class="content">
                    <dt>订单查询测试：</dt>
					<dd>
						<a href="index-query.jsp">点此</a>
					</dd>
                </dl>
                <dl class="content">
                    <dt>退款测试：</dt>
					<dd>
						<a href="index-refund.jsp">点此</a>
					</dd>
                </dl>
                <dl class="content">
                    <dt>退款查询测试：</dt>
					<dd>
						<a href="index-refund-query.jsp">点此</a>
					</dd>
                </dl>
            </div>
		</form>
        <div id="foot">
			<ul class="foot-ul">
				<li><font class="note-help">如果您点击“确认”按钮，即表示您同意该次的执行操作。 </font></li>
				<li>
					威富通版权所有 
				</li>
			</ul>
		</div>
	</div>
</body>
</html>