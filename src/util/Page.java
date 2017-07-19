package util;

import java.util.List;

public class Page<T> {
    private int currentPage = 1; // ��ǰҳ, Ĭ����ʾ��һҳ
    private int pageCount = 4;   // ÿҳ��ʾ������(��ѯ���ص�����), Ĭ��ÿҳ��ʾ4��
    private int totalCount;      // �ܼ�¼��
    private int totalPage;       // ��ҳ�� = �ܼ�¼�� /����,�������ȡ��
    private List<?> pageData;    // ��ҳ��ѯ��������
    
    // ������ҳ��
    public int getTotalPage() {
    	totalPage= (int)Math.ceil(totalCount/(float)pageCount);
        return totalPage;
    }
	public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    
    public List<?> getPageData() {
        return pageData;
    }
    public void setPageData(List<?> pageData) {
        this.pageData = pageData;
    }
}