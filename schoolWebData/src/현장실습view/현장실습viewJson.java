package 현장실습view;

import java.util.List;


import 현장실습view.현장실습view;

public class 현장실습viewJson {
	private int total;
	private int page;
	private int records;
	private List<현장실습view> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public List<현장실습view> getRows() {
		return rows;
	}
	public void setRows(List<현장실습view> rows) {
		this.rows = rows;
	}
}