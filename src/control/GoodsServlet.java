package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Goods;
import util.Page;
import dao.CatalogDao;
import dao.GoodsDao;

public class GoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get

		String ff = request.getParameter("flag");
		if ("getGoodsBycId".equals(ff)) {
			// 通过分类id查询产品
			getGoodsBycId(request, response);
		}
		if ("search".equals(ff)) {
			// 通过关键字查询产品
			searchBystr(request, response);
		}
	}

	private void searchBystr(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1.接收请求参数--查询关键字
		String str = request.getParameter("seach_key");
		str = new String(str.getBytes("iso-8859-1"), "utf-8");
		String currPage = request.getParameter("currentPage");
		if (currPage == null || "".equals(currPage.trim()))
			currPage = "1"; // 第一次访问，设置当前页为1;
		int cp=Integer.parseInt(currPage);
		Page<Goods> page=new Page<Goods>();
		page.setCurrentPage(cp);
		// 2.调用dao层
		GoodsDao dao = new GoodsDao();
		dao.getGoodsBystr(str,page);
		// 3.将查询的产品列表存放在request中
		request.setAttribute("pageresult",page);
		request.setAttribute("seach_key",str);
		request.setAttribute("flag","search");
		request.getRequestDispatcher("goods.jsp").forward(request, response);

	}

	private void getGoodsBycId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1. 获取请求参数并处理
		String id = request.getParameter("cid");
		String currPage = request.getParameter("currentPage");
		// 将字符串转化成整型
		int cid = Integer.parseInt(id);
		if (currPage == null || "".equals(currPage.trim())) {
			currPage = "1"; // 第一次访问，设置当前页为1;
		}
		int cp=Integer.parseInt(currPage);
		Page<Goods> page=new Page<Goods>();
		page.setCurrentPage(cp);
		// 2. 调用dao
		CatalogDao dao = new CatalogDao();
		dao.getGoodsBycid(cid,page);
		// 3.list存放request中转发给jsp页面显示
		if (page.getPageData()!=null&&page.getPageData().size()!=0) {
			request.setAttribute("pageresult",page);
			request.setAttribute("cid",id);
			request.setAttribute("flag","getGoodsBycId");
			request.getRequestDispatcher("goods.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
