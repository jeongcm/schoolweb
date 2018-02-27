package 교외장학금;

import java.util.List;

public class 교외장학금Json {
	private int total;
	private int page;
	private int records;
	private List<교외장학금> rows;
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
	public List<교외장학금> getRows() {
		return rows;
	}
	public void setRows(List<교외장학금> rows) {
		this.rows = rows;
	}

}
