package C그룹;

import java.util.List;

import C그룹.C그룹;

public class C그룹Json {
	private int total;
	private int page;
	private int records;
	private List<C그룹> rows;
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
	public List<C그룹> getRows() {
		return rows;
	}
	public void setRows(List<C그룹> rows) {
		this.rows = rows;
	}
}