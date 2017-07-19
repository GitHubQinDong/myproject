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
			// ͨ������id��ѯ��Ʒ
			getGoodsBycId(request, response);
		}
		if ("search".equals(ff)) {
			// ͨ���ؼ��ֲ�ѯ��Ʒ
			searchBystr(request, response);
		}
	}

	private void searchBystr(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1.�����������--��ѯ�ؼ���
		String str = request.getParameter("seach_key");
		str = new String(str.getBytes("iso-8859-1"), "utf-8");
		String currPage = request.getParameter("currentPage");
		if (currPage == null || "".equals(currPage.trim()))
			currPage = "1"; // ��һ�η��ʣ����õ�ǰҳΪ1;
		int cp=Integer.parseInt(currPage);
		Page<Goods> page=new Page<Goods>();
		page.setCurrentPage(cp);
		// 2.����dao��
		GoodsDao dao = new GoodsDao();
		dao.getGoodsBystr(str,page);
		// 3.����ѯ�Ĳ�Ʒ�б�����request��
		request.setAttribute("pageresult",page);
		request.setAttribute("seach_key",str);
		request.setAttribute("flag","search");
		request.getRequestDispatcher("goods.jsp").forward(request, response);

	}

	private void getGoodsBycId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1. ��ȡ�������������
		String id = request.getParameter("cid");
		String currPage = request.getParameter("currentPage");
		// ���ַ���ת��������
		int cid = Integer.parseInt(id);
		if (currPage == null || "".equals(currPage.trim())) {
			currPage = "1"; // ��һ�η��ʣ����õ�ǰҳΪ1;
		}
		int cp=Integer.parseInt(currPage);
		Page<Goods> page=new Page<Goods>();
		page.setCurrentPage(cp);
		// 2. ����dao
		CatalogDao dao = new CatalogDao();
		dao.getGoodsBycid(cid,page);
		// 3.list���request��ת����jspҳ����ʾ
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
