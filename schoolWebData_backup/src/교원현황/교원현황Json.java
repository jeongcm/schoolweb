package 교원현황;

import java.util.List;

public class 교원현황Json {
 private int total; //jqgrid에 표시할 전체 페이지수
 private int page; //현재 페이지
 private int records; //전체 레코드수
 private List<교원현황>rows; //목록

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

public List<교원현황> getRows() {
	 return rows;
 }

public void setRows(List<교원현황> rows) {
	 this.rows = rows;
 }   
}
