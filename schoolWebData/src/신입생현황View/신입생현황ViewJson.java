package 신입생현황View;

import java.util.List;

public class 신입생현황ViewJson {
	private int total;
	private int page;
	private int records;
	private List<신입생현황View> rows;
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
	public List<신입생현황View> getRows() {
		return rows;
	}
	public void setRows(List<신입생현황View> rows) {
		this.rows = rows;
	}

}
