package 현장실습;

import java.util.List;


import 현장실습.현장실습;

public class 현장실습Json {
	private int total;
	private int page;
	private int records;
	private List<현장실습> rows;
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
	public List<현장실습> getRows() {
		return rows;
	}
	public void setRows(List<현장실습> rows) {
		this.rows = rows;
	}
}