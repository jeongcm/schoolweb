package 특허등록;

import java.util.List;


import 특허등록.특허등록;

public class 특허등록Json {
	private int total;
	private int page;
	private int records;
	private List<특허등록> rows;
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
	public List<특허등록> getRows() {
		return rows;
	}
	public void setRows(List<특허등록> rows) {
		this.rows = rows;
	}
}
