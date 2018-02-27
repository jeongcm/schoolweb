package 수상실적상세자료;

import java.util.List;

import 수상실적상세자료.수상실적상세자료;

public class 수상실적상세자료Json {
	private int total; //jqgrid에 표시할 전체 페이지수
	 private int page; //현재 페이지
	 private int records; //전체 레코드수
	 private List<수상실적상세자료>rows; //목록

	public int getTotal(){
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

	public List<수상실적상세자료> getRows() {
		 return rows;
	 }

	public void setRows(List<수상실적상세자료> rows) {
		 this.rows = rows;
	 }   
}
