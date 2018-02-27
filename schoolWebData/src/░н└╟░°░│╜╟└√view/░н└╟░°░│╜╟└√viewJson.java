package 강의공개실적view;

import java.util.List;

import 강의공개실적view.강의공개실적view;

public class 강의공개실적viewJson {
	private int total;
	private int page;
	private int records;
	private List<강의공개실적view> rows;
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
	public List<강의공개실적view> getRows() {
		return rows;
	}
	public void setRows(List<강의공개실적view> rows) {
		this.rows = rows;
	}
}
