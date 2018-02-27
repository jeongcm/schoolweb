package ºÀ»ç½ÇÀûview;

import java.util.List;

import ºÀ»ç½ÇÀûview.ºÀ»ç½ÇÀûview;

public class ºÀ»ç½ÇÀûviewJson {
	private int total;
	private int page;
	private int records;
	private List<ºÀ»ç½ÇÀûview> rows;
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
	public List<ºÀ»ç½ÇÀûview> getRows() {
		return rows;
	}
	public void setRows(List<ºÀ»ç½ÇÀûview> rows) {
		this.rows = rows;
	}
}

