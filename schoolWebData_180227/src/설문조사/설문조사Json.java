package ��������;

import java.util.List;

import ��������.��������;

public class ��������Json {
	private int total; //jqgrid�� ǥ���� ��ü ��������
	 private int page; //���� ������
	 private int records; //��ü ���ڵ��
	 private List<��������>rows; //���

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

	public List<��������> getRows() {
		 return rows;
	 }

	public void setRows(List<��������> rows) {
		 this.rows = rows;
	 }   
}