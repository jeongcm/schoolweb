package 강의담당외부상세;

import java.util.List;

public class 강의담당외부상세Json {
	private int total;
	private int page;
	private int records;
	private List<강의담당외부상세> rows;
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
	public List<강의담당외부상세> getRows() {
		return rows;
	}
	public void setRows(List<강의담당외부상세> rows) {
		this.rows = rows;
	}

}
