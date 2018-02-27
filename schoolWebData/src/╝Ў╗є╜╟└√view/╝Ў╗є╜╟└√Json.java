package 수상실적view;

import java.util.List;

import 수상실적view.수상실적;

	public class 수상실적Json {
		private int total;
		private int page;
		private int records;
		private List<수상실적> rows;
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
		public List<수상실적> getRows() {
			return rows;
		}
		public void setRows(List<수상실적> rows) {
			this.rows = rows;
		}

}
