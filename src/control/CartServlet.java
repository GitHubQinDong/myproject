package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;

import model.Cart;
import model.User;

public class CartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         String ff=request.getParameter("flag");
         if("addCart".equals(ff)){
        	 //加入购物车
        	 addCart(request,response);
         }
         if("showCart".equals(ff)){
        	 //显示购车
        	 showCart(request,response);
         }
         if("delItem".equals(ff)){
        	 //删除购车中项
        	 delCartItem(request,response);
         }
	}

	
	private void delCartItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//1.接收请求参数cartid并类型转化
		  String catid= request.getParameter("cartid");
		  int cartid=Integer.parseInt(catid);
		//2.调用dao
		   CartDao dao=new CartDao();
		  boolean result= dao.delCartItem(cartid);
	   //3. 根据结果，页面跳转到showCart.jsp
		  if(result){
			  request.setAttribute("msg","删除成功！"); 
			  showCart(request,response);
		  }
	}


	private void showCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.根据用户userid(从session中取) 
		 User user=(User)request.getSession().getAttribute("user");
		 if(user!=null){
			 int uid=user.getUserid();
			    //2.通过uid查询cart表
				  CartDao dao=new CartDao();
				 List<Cart> list= dao.showCartByUserid(uid);
				//3.页面转发
				 request.setAttribute("carList", list);
				 request.getRequestDispatcher("showCart.jsp").forward(request, response); 	 
		 }else{
			 request.setAttribute("carList", null);
			 request.getRequestDispatcher("showCart.jsp").forward(request, response); 	  
		 } 
		 
	}


	private void addCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.判断用户是否登录
		 User user=(User)request.getSession().getAttribute("user");
		  if(user==null){
			  request.getRequestDispatcher("login.html").forward(request, response);
		  }else{
			  String gid=request.getParameter("gid");
			   int gid_=Integer.parseInt(gid);
			 //2.调用dao层，添加购物车 
			  CartDao dao=new CartDao();
			 boolean result= dao.addCart(gid_,user.getUserid());
			 //3.根据结果响应不同信息
			 if(result){
				request.setAttribute("msg", "添加成功！");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			 }
		  }
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
