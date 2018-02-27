package 동아리view;

import java.util.List;

import 동아리view.동아리view;

public class 동아리viewJson {
	private int total;
	private int page;
	private int records;
	private List<동아리view> rows;
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
	public List<동아리view> getRows() {
		return rows;
	}
	public void setRows(List<동아리view> rows) {
		this.rows = rows;
	}
}