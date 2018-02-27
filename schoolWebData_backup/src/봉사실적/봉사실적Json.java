package ºÀ»ç½ÇÀû;

import java.util.List;

import ºÀ»ç½ÇÀû.ºÀ»ç½ÇÀû;

public class ºÀ»ç½ÇÀûJson {
	private int total;
	private int page;
	private int records;
	private List<ºÀ»ç½ÇÀû> rows;
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
	public List<ºÀ»ç½ÇÀû> getRows() {
		return rows;
	}
	public void setRows(List<ºÀ»ç½ÇÀû> rows) {
		this.rows = rows;
	}
}
