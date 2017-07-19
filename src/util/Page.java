package util;

import java.util.List;

public class Page<T> {
    private int currentPage = 1; // 当前页, 默认显示第一页
    private int pageCount = 4;   // 每页显示的行数(查询返回的行数), 默认每页显示4行
    private int totalCount;      // 总记录数
    private int totalPage;       // 总页数 = 总记录数 /行数,结果向上取整
    private List<?> pageData;    // 分页查询到的数据
    
    // 返回总页数
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