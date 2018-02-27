package 동아리;

import java.util.List;

import 동아리.동아리;

public class 동아리Json {
	private int total;
	private int page;
	private int records;
	private List<동아리> rows;
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
	public List<동아리> getRows() {
		return rows;
	}
	public void setRows(List<동아리> rows) {
		this.rows = rows;
	}
}