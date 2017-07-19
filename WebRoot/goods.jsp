<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'goods.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet"  href="css/goods.css">

  </head>
  
  <body>
     <c:if test="${empty goodsList}">
                  未搜索到符合要求的产品
     </c:if>
     
     <c:if test="${not empty goodsList}">
         <ul class="goods_list">
		       <c:forEach var="goods" items="${goodsList}">
		      <li>
		         <div class="mask">
		             <span>商品名：${goods.goodsname}</span>
		         </div>
		          <img src="images/${goods.picture }"><br>
		          <span>价格：${goods.price }</span>
		        
		          <a href="CartServlet?gid=${goods.goodsid }&flag=addCart">加入购物车</a>
		      </li>                  
		       </c:forEach>
		  </ul>             
     </c:if>
     
   
  </body>
</html>
