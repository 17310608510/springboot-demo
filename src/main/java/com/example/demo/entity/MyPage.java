package com.example.demo.entity;
/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月30日 下午3:49:05 
    * @version 1.0 
    * @parameter 
    * @since 
    * @return */
public class MyPage {
	private int  currentPage; //起始页
	private int pageSize; //每页显示多少条
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public MyPage() {
		
	}
	
	public MyPage(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	/**
	 * �?0�?�?
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
