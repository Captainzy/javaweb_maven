package common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Page<T> implements Serializable {

	//默认查询的条数
	public static int DEFAULTROWSPERPAGE = 10;
	/** 总条*/
	private int count;
	/** 页码 */
	private int pageNo;
	/** 每页显示多少*/
	private int rowsPerPage;
	/** 总页数*/
	private int totalPageCount;
	/** 起始条数 */
	private int firstRow;
	/** 结束条数 */
	private int lastRow;
	/** 查询结果 */
	private List<T> result;
	/** 过滤数量*/
	private int limitNum;
	/** 搜索条件*/
	private Map<String, Object> search;
	
	public Page() {
	}

	public Page(List list) {
		this(list.size(), 1, list.size(), list);
	}

	public Page(int count, int pageNo, int rowsPerPage, List<T> result) {
		if (rowsPerPage < 1) {
			rowsPerPage = 1;
		}
		this.count = count;
		this.pageNo = pageNo;
		this.result = result;
		this.rowsPerPage = rowsPerPage;
		if (this.result == null)
			this.result = new ArrayList();
		totalPageCount = count / rowsPerPage;
		if (count - (count / rowsPerPage) * rowsPerPage > 0)
			totalPageCount++;
		if (count == 0) {
			totalPageCount = 0;
			pageNo = 0;
		}

		firstRow = (pageNo - 1) * rowsPerPage + 1;
		if (count == 0) {
			firstRow = 0;
		}
		lastRow = (pageNo) * rowsPerPage;
		if (lastRow > count) {
			lastRow = count;
		}
	}
	
	public Page(String pageNoString, int rowsPerPage){
		this.rowsPerPage=rowsPerPage;
		if(pageNoString==null||pageNoString.equals("null")){
			this.pageNo=1;
			this.limitNum=0;
		}else{
			this.pageNo=getPageInt(pageNoString);
			this.limitNum=(pageNo-1)*this.rowsPerPage;
		}
	}
	
	public Page(int count, int pageNo, int rowsPerPage ){
		if (rowsPerPage < 1) {
			rowsPerPage = 1;
		}
		this.count = count;
		this.pageNo = pageNo;
		this.rowsPerPage = rowsPerPage;
		
		totalPageCount = count / rowsPerPage;
		if (count - (count / rowsPerPage) * rowsPerPage > 0)
			totalPageCount++;
		if (count == 0) {
			totalPageCount = 0;
			pageNo = 0;
		}

		firstRow = (pageNo - 1) * rowsPerPage + 1;
		if (count == 0) {
			firstRow = 0;
		}
		lastRow = (pageNo) * rowsPerPage;
		if (lastRow > count) {
			lastRow = count;
		}
	}
	
	/**
	 * 返回当前截止条数
	 * 
	 * @param pageNow 当前页码
	 */
	public Page(int pageNow){
		this.pageNo=pageNow;
	}
	/** 返回总条数*/
	public int getCount() {
		return count;
	}

	public List<T> getResult() {
		return result;
	}

	public int getPageNo() {
		return pageNo;
	}

	/** 返回每页的条数*/
	public int getRowsPerPage() {
		return rowsPerPage;
	}

	/** 返回总的页数 */
	public int getTotalPageCount() {
		totalPageCount = count / rowsPerPage;
		if (count - (count / rowsPerPage) * rowsPerPage > 0)
			totalPageCount++;
		if (count == 0) {
			totalPageCount = 0;
			pageNo = 0;
		}
		return totalPageCount;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getFirstRow() {
		firstRow = (pageNo - 1) * rowsPerPage;
		if (count == 0) {
			firstRow = 0;
		}
		return firstRow;
	}
	
	public int getLastRow() {
		lastRow = (pageNo) * rowsPerPage-1;
		if (lastRow > count) {
			lastRow = count;
		}
		return lastRow;
	}
	
	public int getFirstRowByPageNo(){
		return (pageNo-1)*DEFAULTROWSPERPAGE;
	}
	
	public int getLastRowByPageNo(){
		return pageNo*DEFAULTROWSPERPAGE;
	}
	
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
	public boolean isNotEmptyOfResult(){
		return this.getResult()!=null && this.getResult().size()!=0;
	}

	public int getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
	
	private static int getPageInt(String pageNo){
		int pa=0;
		double d = Double.parseDouble(pageNo);
		pa=(int)d;
		return pa;
	}

	public Map<String, Object> getSearch() {
		return search;
	}

	public void setSearch(Map<String, Object> search) {
		this.search = search;
	}
}

