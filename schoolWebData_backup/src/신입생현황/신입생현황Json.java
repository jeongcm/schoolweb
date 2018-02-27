package 신입생현황;

import java.util.List;

public class 신입생현황Json {
	private int total;
	private int page;
	private int records;
	private List<신입생현황> rows;
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
	public List<신입생현황> getRows() {
		return rows;
	}
	public void setRows(List<신입생현황> rows) {
		this.rows = rows;
	}

}
