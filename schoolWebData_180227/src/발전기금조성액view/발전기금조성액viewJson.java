package 발전기금조성액view;

import java.util.List;

public class 발전기금조성액viewJson {
	private int total;
	private int page;
	private int records;
	private List<발전기금조성액view> rows;
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
	public List<발전기금조성액view> getRows() {
		return rows;
	}
	public void setRows(List<발전기금조성액view> rows) {
		this.rows = rows;
	}

}
