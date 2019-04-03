$(document).ready(function(){
	//输入框获得焦点
	$(".username").focus(function (){	
		$(".login_username_tip p").css("color","gray");
		$(".login_username_tip p").text("支持字母、数字、下划线、不小于6个字符的组合");
		
	});
	
	//判断用户名格式是否正确
	function checkusername(){
		var str1 = $(".username").val();
		var x1 = "^[0-9A-z_]{6,256}$";
		var y1 = str1.match(x1);
		if(y1==null){
			return false;
		}else{
			return true;
		}
	}
	
	//输入框失去焦点
	$(".username").blur(function (){	
		if(checkusername()){
			$(".login_username_tip p").text("");
		}else{
			$(".login_username_tip p").css("color","red");
			$(".login_username_tip p").text("请输入正确的密码格式");
		}	
	});
	
	
	
	//密码框的焦点
	$(".password").focus(function (){
		$(".login_submit p").css("color","gray");
		$(".login_submit p").text("支持字母、数字、下划线、不小于6个字符的组合");
		
	});
	
	//判断密码格式是否正确
	function checkpassword(){
		var str1 = $(".password").val();
		var x1 = "[0-9A-z_]{6,256}$";
		var y1 = str1.match(x1);
		if(y1==null){
			return false;
		}else{
			return true;
		}	
	}
	
	//密码框失去焦点
	$(".password").blur(function (){	
		if(checkpassword()){
			$(".login_password_tip p").text("");
		}else{
			$(".login_submit p").css("color","red")
			$(".login_password_tip p").text("请输入正确的密码格式");
		}
	});
	
	//点击按钮提交数据
	/*$(".submit").click(function(){
		if(checkpassword()&&checkpassword()){
			
		}else{
			alert("用户名或者密码格式不正确！");
		}
	});*/
	
	
	//去立即注册
	$(".login_form_title").click(function(){
		alert("skr~ skr~");
		$(location).attr('href', 'register.html');
	});
	
	//忘记密码
	$(".login_submit a").click(function (){
		alert("你是猪吗？密码都记不住！");
	});
	
	
	//其他方式
	//0
	$(".login_form_other .icon_list li img").eq(0).mouseover(function(){
		$(this).attr("src","img/weixin1.png");
	});
	$(".login_form_other .icon_list li img").eq(0).mouseleave(function(){
		$(this).attr("src","img/weixin.png");
	});
	//1
	$(".login_form_other .icon_list li img").eq(1).mouseover(function(){
		$(this).attr("src","img/qq1.png");
	});
	$(".login_form_other .icon_list li img").eq(1).mouseleave(function(){
		$(this).attr("src","img/qq.png");
	});
	//2
	$(".login_form_other .icon_list li img").eq(2).mouseover(function(){
		$(this).attr("src","img/weibo1.png");
	});
	$(".login_form_other .icon_list li img").eq(2).mouseleave(function(){
		$(this).attr("src","img/weibo.png");
	});
	//3
	$(".login_form_other .icon_list li img").eq(3).mouseover(function(){
		$(this).attr("src","img/email1.png");
	});
	$(".login_form_other .icon_list li img").eq(3).mouseleave(function(){
		$(this).attr("src","img/email.png");
	});
	
	//跳转微信
	$(".login_form_other .icon_list li ").eq(0).click(function(){		
		$(location).attr('href', 'https://wx.qq.com/');
	});
	$(".login_form_other .icon_list li ").eq(1).click(function(){		
		$(location).attr('href', 'http://web2.qq.com/');
	});
	$(".login_form_other .icon_list li ").eq(2).click(function(){		
		$(location).attr('href', 'https://passport.weibo.cn/signin/login');
	});
	$(".login_form_other .icon_list li ").eq(3).click(function(){		
		$(location).attr('href', 'https://qiye.163.com/login/');
	});
	
	//背景切换
	
	
});