<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="css/welcome.css"/>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			/* //登录框得到焦点
			$("#password").focus(function(){
				$("#left_hand").animate({
					left: "150",
					top: " -38"
				},{step: function(){
					if(parseInt($("#left_hand").css("left"))>140){
						$("#left_hand").attr("class","left_hand");
					}
				}}, 2000);
				$("#right_hand").animate({
					right: "-64",
					top: "-38px"
				},{step: function(){
					if(parseInt($("#right_hand").css("right"))> -70){
						$("#right_hand").attr("class","right_hand");
					}
				}}, 2000);
			});
			
			//失去焦点
			$("#password").blur(function(){
				$("#left_hand").attr("class","initial_left_hand");
				$("#left_hand").attr("style","left:100px;top:-12px;");
				$("#right_hand").attr("class","initial_right_hand");
				$("#right_hand").attr("style","right:-112px;top:-12px");
			}); */
			
		    //新闻点击 */
		    
		    //登录按钮点击
		    $('#login_btn').click(function(){
		    	//先进行账号和密码框的验证，验证通过之后，取出帐号密码发送请求
		    	//alert($('#title').val().trim());
		    	var title = $('#title').val().trim();
		    	var username = $('#username').val().trim();
				var password = $('#password').val().trim();
				var isOk = true;
				if(username=="" || password==""){
					isOk = false;
					alert("用户名和密码不能为空");
				}
		    	if(isOk){
		    		$.ajax({
		    			type:"post",//为post请求
		                url:"worker/login.check",//这是我在后台接受数据的文件名
		                data:{"title":title,"username":username,"password":password},
		                //async:false,//false同步，true异步
		                dataType:"json",
			    		success:function(result){
			    			//alert("ending");
			    			if(result.success){
			    				//登录成功
			    				alert(result.data.stateinfo);
			    				//alert(result.data.state);
			    				if(result.data.state==1){
			    					if(title=="财务部门管理员"){
				    					//alert("jump");
				    					window.location.href = "school.jsp";
				    				}
				    				else if(title=="财政部门管理员"){
				    					//alert("jump");
				    					window.location.href = "college.jsp";
				    				}else{
				    					window.location.href = "office.jsp";
				    				} 
			    				}
			    				else{
			    					alert(result.data.stateinfo);
			    				}
			    			}
			    			
			    		},
			    		error:function(){
			    			alert("登录出现异常");
			    		}
		    		});
		    	}
		    	/* if(isOk){
		    		$("#submitform").submit();
		    	} */
		    });
		
		$(document).keypress(function(e) {  
			// 回车键事件  
			if(e.which == 13) {
				$('#login_btn').click();
			}  
	   	});
	});
	</script>
</head>
<body>
<div>
<img src="images/welcome_xiaohui.png" width="587" height="110" style="position:absolute; left:230px; top:85px; " />
</div> 
<div>
<img src="images/welcome_1.jpg" style="position:absolute; left:255px; top:250px; "/> 
	 <div id="login">
	   <h1><strong>Login</strong></h1>
	   <!-- <form action="demo1" method="post"> -->
	   <form >
	   		<table>
				<tr>
				<td><h2>身  份</h2></td>
				<td>
					<select id="title" name="user" Width="100%" Height="100%" >
					<option value="财务部门管理员">财务部门管理员</option>
					<option value="财政部门管理员">财政部门管理员</option>
					<option value="业务部门管理员">业务部门管理员</option>
				</select></td>
				</tr>
				<tr>
					<td><h3>用户名</h3></td>
					<td><input type="text" class="form_user" id="username" placeholder="请输入用户名"></td>
				</tr>
				<tr>
					<td><h4>密 码</h4></td>
					<td><input type="password" class="form_user" id="password" placeholder="请输入密码"></td>
				</tr>
			</table>
			<button id="login_btn" class="but">登录</button>
			<!-- <button type="button" class="but" onclick="window.open('university.jsp')">登录</button> -->
			</form>
    </div>  
</div>  
</body>
</html>