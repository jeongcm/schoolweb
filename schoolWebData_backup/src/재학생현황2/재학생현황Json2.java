package 재학생현황2;

import java.util.List;

public class 재학생현황Json2 {
	private int total;
	private int page;
	private int records;
	private List<재학생현황2> rows;
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
	public List<재학생현황2> getRows() {
		return rows;
	}
	public void setRows(List<재학생현황2> rows) {
		this.rows = rows;
	}

}
