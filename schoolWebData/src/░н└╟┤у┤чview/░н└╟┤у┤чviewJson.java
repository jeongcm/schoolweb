package 강의담당view;

import java.util.List;

public class 강의담당viewJson {
	private int total;
	private int page;
	private int records;
	private List<강의담당view> rows;
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
	public List<강의담당view> getRows() {
		return rows;
	}
	public void setRows(List<강의담당view> rows) {
		this.rows = rows;
	}

}
