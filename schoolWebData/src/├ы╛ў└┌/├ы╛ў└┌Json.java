package 취업자;

import java.util.List;

public class 취업자Json {
	private int total;
	private int page;
	private int records;
	private List<취업자> rows;
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
	public List<취업자> getRows() {
		return rows;
	}
	public void setRows(List<취업자> rows) {
		this.rows = rows;
	}

}
