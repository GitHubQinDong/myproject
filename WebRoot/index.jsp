<%@ page language="java" import="java.util.*,model.*,dao.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="css/index.css">
  </head> 
  <body>

   <c:if test="${not empty msg}">
       <script>
            alert("${msg}");
       </script>
   </c:if>
<div class="header">
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

  <% CatalogDao dao=new CatalogDao();
     request.setAttribute("catalogList" ,dao.getCatalogs());
  %>
  
  <ul class="catalog_nav">
   <c:forEach var="catalog" items="${catalogList}" >
      <li><a href="GoodsServlet?cid=${catalog.catalogid}&flag=getGoodsBycId">${catalog.catalogname} </a></li>
   </c:forEach>
  </ul>  
 
 <div class="search"> 
  <form action="GoodsServlet">
      <input type="hidden" name="flag" value="search">
      <input type="text" name="seach_key" placeholder="要查询的产品">
      <input type="submit" value="搜索">
  </form>
 </div> 


<iframe src="map.html" 
  style="position: relative;
    width: 710px;
    height: 560px;
    border: 1px solid;
    left: 50%;
    margin-left: -355px;" >
    </iframe>


  </body>
</html>
