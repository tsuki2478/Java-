package cn.hd.utils;

import java.util.List;

/**
 * 
 * 分页工具
 * @author Administrator
 * @param <T>
 *
 */

public class BasePage<T> {
/**
 * 	当前页
 */
	private int currentPage;
/**
 *   每页显示的条数
 */
    private int  pageSize;	
/**
 * 	分页要显示的数据
 */
    private List<T> list;
/**
 * 	总的数据的条数   
 */
    private int  size;
/**
 * 	总的页数
 */
    private int totalPage;
    
    
	public BasePage() {
		super();
	}
	public BasePage(int currentPage, int pageSize) {
	super();
	this.currentPage = currentPage;
	this.pageSize = pageSize;
}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
    
    
}
