//登录注册界面跳转
$("#register").click(function(){
	//点击图片后发送跳转到指定页面的事件。
	window.location.href="pages/news.html";
	});
$("#login").click(function(){
	//点击图片后发送跳转到指定页面的事件。
	window.location.href="pages/test.html";
	});


//修改导航栏是否可见
$(function(){

	var nav=$(".head"); //得到导航对象
	var win=$(window); //得到窗口对象
	var sc=$(document);//得到document文档对象。

	win.scroll(function(){
		  if(sc.scrollTop()>=60){
			  $(".head").addClass("fixednav");  
			   
			  }else{
				  $(".head").removeClass("fixednav");
			  }  
	})  
})

//
$(".nav li").hover(function(){

	$(this).find(".button").show();
    },function(){
        //鼠标移开
    $(this).find(".button").hide();
    });


