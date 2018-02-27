package 입력확인2;

import java.util.List;

public class 입력확인Json {
	private int total;
	private int page;
	private int records;
	private List<입력확인> rows;
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
	public List<입력확인> getRows() {
		return rows;
	}
	public void setRows(List<입력확인> rows) {
		this.rows = rows;
	}

}
