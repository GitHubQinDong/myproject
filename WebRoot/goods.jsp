<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<link rel="stylesheet" href="css/goods.css">

</head>

<body>
	<c:if test="${empty pageresult.pageData}">
                  未搜索到符合要求的产品
     </c:if>

	<c:if test="${not empty pageresult}">
		<ul class="goods_list">
			<c:forEach var="goods" items="${pageresult.pageData}">
				<li>
					<div class="mask">
						<span>商品名：${goods.goodsname}</span>
					</div> <img src="images/${goods.picture }"><br> <span>价格：${goods.price
						}</span> <a href="CartServlet?gid=${goods.goodsid }&flag=addCart">加入购物车</a>
				</li>
			</c:forEach>
		</ul>
	  当前${pageresult.currentPage }/${pageresult.totalPage }页     &nbsp;&nbsp;
    <a href="GoodsServlet?flag=${flag }&seach_key=${seach_key }&cid=${cid }&currentPage=1">首页</a>
	<a href="GoodsServlet?flag=${flag }&seach_key=${seach_key }&cid=${cid }&currentPage=${pageresult.currentPage-1}">上一页</a>
	<a href="GoodsServlet?flag=${flag }&seach_key=${seach_key }&cid=${cid }&currentPage=${pageresult.currentPage+1}">下一页</a>
	<a href="GoodsServlet?flag=${flag }&seach_key=${seach_key }&cid=${cid }&currentPage=${pageresult.totalPage}">末页</a>
	</c:if>
</body>
</html>
