<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="demo1" method="post">
	<input type="text" name="name">
	<input type="text" name="age">
	<input type="submit" value="tijiao">
</form>
</body>
</html> -->

<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="css/welcomee.css"/>
</head>
<body>
<div>
<img src="images/welcome_xiaohui.png" width="587" height="110" style="position:absolute; left:210px; top:85px; " />
</div> 
<div>
<img src="images/welcome_1.jpg" style="position:absolute; left:230px; top:250px; "/> 
	 <div id="login">
	   <h1><strong>Login</strong></h1>
	   <form action="college/export" method="post">
	   		<table>
				<tr>
				<td><h2>身  份</h2></td>
				<td>
					<select Width="100%" Height="100%" name="user">
					<option value="caiwu">财务部门管理员</option>
					<option value="caizheng">财政部门管理员</option>
					<option value="yewu">业务部门管理员</option>
				</select></td>
				</tr>
				<tr>
					<td><h3>用户名</h3></td>
					<td><input type="text" class="form-user" name="username" placeholder="请输入用户名"></td>
				</tr>
				<tr>
					<td><h4>密 码</h4></td>
					<td><input type="password" class="form-user" name="password" placeholder="请输入密码"></td>
				</tr>
			</table>
			<button class="but" type="submit" >登录</button>
			<!-- <button type="button" class="but" onclick="window.open('university.jsp')">登录</button> -->
			</form>
			
		
    </div>  
</div>



</body>
</html>