package 설문조사view;

import java.util.List;

import 설문조사view.설문조사view;

public class 설문조사viewJson {
	private int total; //jqgrid에 표시할 전체 페이지수
	 private int page; //현재 페이지
	 private int records; //전체 레코드수
	 private List<설문조사view>rows; //목록

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

	public List<설문조사view> getRows() {
		 return rows;
	 }

	public void setRows(List<설문조사view> rows) {
		 this.rows = rows;
	 }   
}