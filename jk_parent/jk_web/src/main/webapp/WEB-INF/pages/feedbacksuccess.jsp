<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv= "Refresh" content= "5;url=homeAction_tomain.action?moduleName=home ">
<title>Insert title here</title>
<script type="text/javascript">
	var times=6;
	clock();
	function clock()
	{
	   window.setTimeout('clock()',1000);
	   times=times-1;
	   time.innerHTML =times;
	}
</script>
</head>
<body>
	<h3>意见反馈已成功发送。。。。</h3>  
    <b>该页面将在<span id= "time" style="color: red">5</span>秒后，自动跳转回主页。</b>
</body>
</html>