package 교육프로그램참여비율;

import java.util.List;


import 교육프로그램참여비율.교육프로그램참여비율;

public class 교육프로그램참여비율Json {
	private int total;
	private int page;
	private int records;
	private List<교육프로그램참여비율> rows;
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
	public List<교육프로그램참여비율> getRows() {
		return rows;
	}
	public void setRows(List<교육프로그램참여비율> rows) {
		this.rows = rows;
	}
}
