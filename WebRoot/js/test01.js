$(function(){ //页面dom结构加载完成，执行以下函数体	
	$("#msg_user").css({color:"red"});
	// 给用户名 输入框绑定失去焦点验证
	//在html中用onblur=函数名()，可读性差
	$("#user").blur(function(){
		// 失去焦点事件（匿名函数体中调用自定义的事件）
		checkUser();
	});
	
	$("#msg_psw").css({color:"red"});
	$("#psw").blur(function(){
		checkPassWord();
	});
	
});

function checkUser_reg(){
	var flag=false;//状态
	//判断输入是否为空
	var vv=$("#user").val();
	if(vv!=""){
		//用户名没有被占用
		 $.ajax({
			  type:"get",
			  url:"UserServlet?tt=new Date()",
			  data:{uname:vv,flag:"checkUserName"},
			  success:function(recv_data){
				  if(recv_data==1){
					  $("#msg_user").html("用户名可用");
					  $("#msg_user").css({color:"green"});
				  }
                  if(recv_data==0){
					  $("#msg_user").html("用户名被占用");
					  $("#msg_user").css({color:"red"});
					  $("#user").val("");  
				  }
			  } 
		 }); 
		flag=true;
	}
	else{
		$("#msg_user").html("用户名不能为空");
		$("#msg_user").css({color:"red"});
	}
	return flag;
}

function checkUser_login(){
	var flag=false;//状态
	//判断输入是否为空
	var vv=$("#user").val();
	if(vv!=""){
		  $("#msg_user").html("输入正确！");
		  $("#msg_user").css({color:"red"});
		  flag=true;
	}
	else{
		$("#msg_user").html("用户名不能为空");
		$("#msg_user").css({color:"red"});
	}
	return flag;
}
//验证密码
function checkPassWord(){
	var flag=false;//状态
	var psw=$("#psw").val();
	if(psw.length<6){
		$("#msg_psw").html("密码不正确");
		$("#msg_psw").css({color:"red"});
	}else{
		$("#msg_psw").html("密码正确");
		$("#msg_psw").css({color:"green"});
		flag=true;
	}
	return flag;
}

//提交验证
function checkForm(){
	var flag=false;
	if(checkUser_reg()&&checkPassWord()){
		flag=confirm("确认提交吗？");
	}
     return flag;
   }

function checkForm_login(){
	var flag=false;
	if(checkUser_login()&&checkPassWord()){
		flag=true;
	}
     return flag;
   }
