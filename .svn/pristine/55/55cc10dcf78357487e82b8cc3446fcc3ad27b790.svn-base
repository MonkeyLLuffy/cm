package cn.dlb.cm.entity;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class PageBean {
	private int currentPage;//当前页
	private int pageSize;//每页显示条数
	private int pageNum;//总页数
	private int recordNum;//总记录数
	private List pageRecord;//要显示的数据集合
	
	public PageBean() {
		super();
	}
	
	public PageBean(int currentPage, int pageSize, int recordNum,
			List pageRecord) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.pageNum=recordNum%pageSize==0?recordNum/pageSize:recordNum/pageSize+1;
		this.recordNum = recordNum;
		this.pageRecord = pageRecord;
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
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}
	public List getPageRecord() {
		return pageRecord;
	}
	public void setPageRecord(List pageRecord) {
		this.pageRecord = pageRecord;
	}
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", pageNum=" + pageNum + ", recordNum="
				+ recordNum + ", pageRecord=" + pageRecord + "]";
	}
	
}
