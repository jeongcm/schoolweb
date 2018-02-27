package 기술이전;

import java.util.List;


import 기술이전.기술이전;

public class 기술이전Json {
	private int total;
	private int page;
	private int records;
	private List<기술이전> rows;
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
	public List<기술이전> getRows() {
		return rows;
	}
	public void setRows(List<기술이전> rows) {
		this.rows = rows;
	}
}
