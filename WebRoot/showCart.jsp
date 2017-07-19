<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showCart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet"  href="css/goods.css">
    <script  src="js/jquery-1.8.0.js"></script></head>
    <script >
       $(function(){
          // 累计价格
          var total=0;
            $(".item_money").each(function(k,v){
                //alert(k+"----"+$(v).html());
               total+=parseFloat($(v).html()); 
            }); 
          $("#total_money").html("总价："+total);
          $("#total_money").css({color:"red"});
          
          // 拼接carids
          var carids="";
           $(".goods_list li").each(function(k,v){
              // 除了最后一个元素外，其他元素都追加-
              if(k!=$(".goods_list li").size()-1){
               carids+=$(v).find(".ids").html()+","; 
              }else{
               carids+=$(v).find(".ids").html();
              }
           }); 
           
         // 点击“确定订单”跳转页面
          $("#confirmOrder").click(function(){
               window.location="confirm_order.jsp?cids="+carids+"&total="+total; 
          });  
           
       });
    </script>
  <body>
  <div class="header">
   <span><a href="index.jsp">首页</a></span>
    <c:if test="${not empty sessionScope.user}">
         <span> 欢迎${sessionScope.user.username}</span>
   </c:if>
  
  <c:if test="${empty sessionScope.user}">
     <span><a href="login.html">请登录</a></span>
  </c:if>
   
   <span class="cart">
    <a href="CartServlet?flag=showCart"><img src="images/cart.png"/>购物车</a>
   </span>
</div>

<c:if test="${empty carList}">
     购物空空如也，快去购物吧！！
</c:if>

<c:if test="${not empty msg}">
  <script>
     alert("${msg}");
  </script>
</c:if>

<c:if test="${not empty carList}">
    <ul class="goods_list">
		 <c:forEach var="cart" items="${carList}">
		     <li>
		         <img src="images/${cart.goods.picture}"><br>
		         <span>商品名：${cart.goods.goodsname}</span><br>
		         <span>价格：${cart.goods.price }</span><br>
		                 数量<input type="text" value="${cart.quantity}" readonly size=5><br>
		               小计：<span class="item_money">${cart.goods.price*cart.quantity }</span>
		         <span class="ids" style="dispaly:none">${cart.cartid}</span>
		        
		        <a href="CartServlet?flag=delItem&cartid=${cart.cartid}">删除</a>
		     </li>                  
		  </c:forEach>
	</ul>  
</c:if>
	<h2 id="total_money">总价：</h2>

<div style="width:100px;height:40px;background:orange; float:right;text-align:center;line-height:40px;"> 
  <a id="confirmOrder" style="text-decoration:none">确认订单</a>
</div>	
	
	
  </body>
</html>
