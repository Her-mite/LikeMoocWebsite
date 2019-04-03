$(document).ready(function(){
	/*用户名*/
	//获得焦点提示密码格式
	$(".register_username input").focus(function (){	
		$(".username_tips").css("color","grey");
		$(".username_tips").text("支持字母、数字、_、不少于6位的组合");
		
	});
	//验证用户名合法性函数
	function checkusername(){
		var str1 = $(".register_username input").val();
		var x1 = "^[0-9A-z_]{6,256}$";
		var y1 = str1.match(x1);
		if(y1==null){
			return false;
		}else{
			return true;
		}
	}
	
/*********************************************************************/
	//发送数据
	function send(to, p) {  
	    var myForm = $("form");  
	    myForm.method = "post";  
	    myForm.action = to;  
	    var myInput = $(".register_username input");  
	    myInput.setAttribute("name", p);   
	     myForm.appendChild(myInput);  
  
	    document.body.appendChild(myForm);  
	    myForm.submit();  
	    document.body.removeChild(myForm);  
	}  
	//获取数据
	
		/*
		var xmlhttp;
		if (window.XMLHttpRequest){
  			xmlhttp=new XMLHttpRequest();
  		}else{
  			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  			}
  		xmlhttp.open("POST",user+".txt",true);
		xmlHttpRequest.setRequestHeder("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xmlhttp.send();
		
  		xmlhttp.onreadystatechange=function(){
  			if (xmlhttp.readyState==4 && xmlhttp.status==200){
    			if(xmlhttp.responseText=="namedefeat"){
    			return false;
    			}else{
    				return true;
    			}
    		}
  		}		
	*/
	
	
/********************************************************************/	
	
	
	//失去焦点
	$(".register_username input").blur(function(){
		if(checkusername()){
			$(".username_tips").text("");
			console.log($(".register_username input").val());
			send("../goVerifyUserInfo",$(".register_username input").val());
			
			
			var xmlhttp;
			if (window.XMLHttpRequest){
  				xmlhttp=new XMLHttpRequest();
  			}else{
  				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  			}
  			xmlhttp.open("POST","../txt/verify.txt",true);
			xmlHttpRequest.setRequestHeder("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			xmlhttp.send();
		
  			xmlhttp.onreadystatechange=function(){
  				if (xmlhttp.readyState==4 && xmlhttp.status==200){
    				if(xmlhttp.responseText=="namedefeat"){
    					$(".username_tips").css("color","red");
						$(".username_tips").text("该用户名已经被注册");
    			}
    		}
  		}		
			
		}else{
			$(".username_tips").css("color","red");
			$(".username_tips").text("您输入的用户名格式不正确");
		}
	});
	
	/*密码*/
	//获得焦点
	$(".register_password input").focus(function (){
		$(".password_tips").css("color","grey");
		$(".password_tips").text("建议使用字母、数字、_、两种以上的组合，6-20个字符");
	});
	
	//检查密码函数
	function checkpassword(){
		var str1 = $(".register_password input").val();
		var x1 = "^[0-9A-z_]{6,256}$";
		var y1 = str1.match(x1);
		if(y1==null){
			return false;
		}else{
			return true;
		}
	}
	//失去焦点
	$(".register_password input").blur(function(){
		if(checkpassword()){
			$(".password_tips").text("");
		}else{
			$(".password_tips").css("color","red");
			$(".password_tips").text("您输入的密码格式不正确");
		}
	});
	
	/*在次输入密码*/
	$(".register_rpassword input").focus(function(){
		$(".rpassword_tips").css("color","grey");
		$(".rpassword_tips").text("请再次您的密码");
	});
	//验证密码是否一致
	function checkrpassword(){
		if ($(".register_password input").val()!="") {
			if ($(".register_password input").val()==($(".register_rpassword input").val())) {
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	//提示
	$(".register_rpassword input").blur(function (){
		console.log($(".register_password input").val());
		console.log($(".register_rpassword input").val());
		console.log(checkrpassword());
		if(checkrpassword()){
			$(".rpassword_tips").text("");
		}else{
			$(".rpassword_tips").css("color","red");
			$(".rpassword_tips").text("两次输入的密码不同");
		}
	
	});
	
	//电话号码
	$(".register_tel input").focus(function (){
		$(".tel_tips").css("color","grey");
		$(".tel_tips").text("请输入11位电话号码");
	});
	//检查号码的合格性
	function checktel(){
		var str1 = $(".register_tel input").val();
		var x1 = "^[1-9][0-9]{10}$";
		var y1 = str1.match(x1);
		if(y1==null){
			return false;
		}else{
			return true;
		}
	}
	
	//失去焦点
	$(".register_tel input").blur(function (){
		if(checktel()){
			$(".tel_tips").text("");
			
			send($(".register_tel input").val());
			
			var xmlhttp;
			if (window.XMLHttpRequest){
  				xmlhttp=new XMLHttpRequest();
  			}else{
  				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  			}
  			xmlhttp.open("POST","goVerifyInfo.txt",true);
			xmlHttpRequest.setRequestHeder("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			xmlhttp.send();
		
  			xmlhttp.onreadystatechange=function(){
  				if (xmlhttp.readyState==4 && xmlhttp.status==200){
    				if(xmlhttp.responseText=="namedefeat"){
    					$(".tel_tips").css("color","red");
						$(".tel_tips").text("改号码已经被注册");
    			}
    		}
  		}		
			
			
		}else{
		$(".tel_tips").css("color","red");
		$(".tel_tips").text("您输入的号码格式不正确");
		}
	});
	/*邮箱*/
	//获得焦点
	$(".register_email input").focus(function(){
		$(".email_tips").css("color","grey");
		$(".email_tips").text("邮箱格式如@163.com @126.com @qq.com @gmail.com");
	});
	//判断邮箱格式
	function checkemail(){
		var str = $(".register_email input").val();
		var x = "^[A-z0-9]{1,11}@[A-z0-9]{1,10}.[A-z]{1,5}$";
		var y = str.match(x);
		if(y == null){
			return false;
		}else{
			return true; 
		}
		}
	//失去焦点
	$(".register_email input").blur(function (){
		if(checkemail()){
			$(".email_tips").text("");	
			
			//发送数据
			send($(".register_email input").val());
			var xmlhttp;
			if (window.XMLHttpRequest){
  				xmlhttp=new XMLHttpRequest();
  				}else{
  				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  				}
  		
				xmlhttp.onreadystatechange=function(){
  				if (xmlhttp.readyState==4 && xmlhttp.status==200)
    			{
    				if(xmlhttp.responseText=="defeat"){
    				$(".email_tips").css("color","red");
					$(".email_tips").text("改邮箱已经被注册");
    			}
    			}
  			}		
			xmlhttp.open("POST","goVerifyInfo.txt",true);
			xmlHttpRequest.setRequestHeder("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			xmlhttp.send($(".register_email input").val());
		
		
		}else{
			$(".email_tips").css("color","red");
			$(".email_tips").text("您输入的E-mail格式不正确");
		}
	});
	
	/*验证码*/
	$(".register_confirm input").click(function(){
		$(".confirm_tips").css("color","grey");
		$(".confirm_tips").text("看不清？点击图片更换验证码");
	});
	//产生验证码
	function changecode(){
		var str="qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPLKJHGFDSAZXCVBNM";
		var str1=0;
		for(var i=0; i<4;i++){
			str1+=str.charAt(Math.floor(Math.random()*62)) 
		}
		str1=str1.substring(1)
		$("#confirm_code").text(str1);
	}
	changecode();
	$("#confirm_code").click(function(){
		changecode();
	});
	//判断验证码
	function checkcode(){
		if($(".register_confirm input").val().toLowerCase()==$("#confirm_code").text().toLowerCase()){
			return true;
		}
		else{
			return false;
		}
	}
	$(".register_confirm input").blur(function (){
		console.log($(".register_confirm input").val().toLowerCase());
		console.log($("#confirm_code").text().toLowerCase());
		
		if(checkcode()){
			$(".confirm_tips").text("");
		}else if($(".register_confirm input").val()!=""){
			$(".confirm_tips").css("color","red");
			$(".confirm_tips").text("验证码输入错误");
		}else{
			$(".confirm_tips").css("color","grey");
			$(".confirm_tips").text("看不清？点击图片更换验证码");
		}
	});
	
	//用户协议
	function checkarguement(){
		if($("#xieyi")[0].checked){
			return true;
		}else{						
			return false;
		}
	};
	
	
	//提交
	$("#submit_button").click(function(){
		if( checkusername()&&checktel()&&checkrpassword()&&checkpassword()&&
		checkemail()&&checkcode()&&checkarguement() ){
			var xmlhttp;
			if (window.XMLHttpRequest){
  				xmlhttp=new XMLHttpRequest();
  		}else{
  			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  		}
  		
		xmlhttp.onreadystatechange=function(){
  			if (xmlhttp.readyState==4 && xmlhttp.status==200)
    		{
    			if(xmlhttp.responseText=="defeat"){
    				alert("用户名或者密码错误！");
    			}
    		}
  		}		
		xmlhttp.open("POST",$(".username").val+".xml",true);
		xmlHttpRequest.setRequestHeder("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xmlhttp.send();
		
		}else{
			alert("请完整填写用户信息！");
		}
	});
	
	
});
