<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'confirm_order.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  <script  src="js/jquery-1.8.0.js"></script>
  <script>
     $(function(){
       //window.location返回值是object
        var addr=window.location+"";
        var arr=addr.split("?");
        var prs=arr[1].split("&");// cids=1-2-3 ,total=704
        $("#cartids").val(prs[0].split("=")[1]);
        $("#money").val(prs[1].split("=")[1])
     });
  </script>
  </head>
  <body>
      <form action="OrderServlet?flag=gomai" method="post">
               电话 <input type="text" name="telphone">   
               发货地址<input type="text" name="address">
               收件人<input type="text" name="uname" value="${sessionScope.user.username}" readonly>      
                金额 <input id="money" name="money" readonly>
        <input type="hidden" id="cartids" name="cartids">        
        <input type="submit" value="提交订单">
          <input type="reset" value="重置">
      </form>
      
  <a href="CartServlet?flag=showCart">返回</a>    
  </body>
</html>
