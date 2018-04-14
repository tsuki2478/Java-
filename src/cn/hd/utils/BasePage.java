package cn.hd.utils;

import java.util.List;

/**
 * 
 * ��ҳ����
 * @author Administrator
 * @param <T>
 *
 */

public class BasePage<T> {
/**
 * 	��ǰҳ
 */
	private int currentPage;
/**
 *   ÿҳ��ʾ������
 */
    private int  pageSize;	
/**
 * 	��ҳҪ��ʾ������
 */
    private List<T> list;
/**
 * 	�ܵ����ݵ�����   
 */
    private int  size;
/**
 * 	�ܵ�ҳ��
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
