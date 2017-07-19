package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Goods;

import dao.CatalogDao;
import dao.GoodsDao;

public class GoodsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		//get  
	
		String ff=request.getParameter("flag");
		if("getGoodsBycId".equals(ff)){
			//通过分类id查询产品
			getGoodsBycId(request,response);
		}
		if("search".equals(ff)){
			//通过关键字查询产品
			searchBystr(request,response);
		}
	}

	private void searchBystr(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.接收请求参数--查询关键字
		  String str=request.getParameter("seach_key");
		   str=new String(str.getBytes("iso-8859-1"),"utf-8");
		   //2.调用dao层
		  GoodsDao dao=new GoodsDao();
		  List<Goods> list=dao.getGoodsBystr(str) ; 
		  System.out.println(list.size()+"产品个数---");
		  // 3.将查询的产品列表存放在request中
		  request.setAttribute("goodsList", list);
		  request.getRequestDispatcher("goods.jsp").forward(request, response);
		
	}

	private void getGoodsBycId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	  //1. 获取请求参数并处理
		String id=request.getParameter("cid");
		// 将字符串转化成整型
		int cid= Integer.parseInt(id);
	 //2. 调用dao
		 CatalogDao dao=new CatalogDao();
	    List<Goods>	list= dao.getGoodsBycid(cid);
	 // 3.list存放request中转发给jsp页面显示	
	    if(list!=null){
	    	request.setAttribute("goodsList", list);
	    	request.getRequestDispatcher("goods.jsp").forward(request, response);
	    }
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
