package 재학생현황View;

public class 재학생현황View {
	private int 년도;
	private String 기준;
	private String 학과명;
	private int 학생정원;
	private int 군휴학자;
	private int 정원내;
	private int 정원외;
	private int 계;
	private int 타학과전과자;
	
	private String 신설연도;
	private String 단과대학;

	private float 전체재학생충원율;
	private float 정원내재학생충원율;
	private float 재학생충원율;
	private float T점수;

	public int get년도() {
		return 년도;
	}

	public void set년도(int 년도) {
		this.년도 = 년도;
	}

	public String get학과명() {
		return 학과명;
	}

	public void set학과명(String 학과명) {
		this.학과명 = 학과명;
	}

	public int get학생정원() {
		return 학생정원;
	}

	public void set학생정원(int 학생정원) {
		this.학생정원 = 학생정원;
	}

	public int get계() {
		return 계;
	}

	public void set계(int 계) {
		this.계 = 계;
	}

	public int get정원외() {
		return 정원외;
	}

	public void set정원외(int 정원외) {
		this.정원외 = 정원외;
	}

	public int get군휴학자() {
		return 군휴학자;
	}

	public void set군휴학자(int 군휴학자) {
		this.군휴학자 = 군휴학자;
	}

	public int get정원내() {
		return 정원내;
	}

	public void set정원내(int 정원내) {
		this.정원내 = 정원내;
	}

	public String get기준() {
		return 기준;
	}

	public void set기준(String 기준) {
		this.기준 = 기준;
	}

	public String get단과대학() {
		return 단과대학;
	}

	public void set단과대학(String 단과대학) {
		this.단과대학 = 단과대학;
	}

	public float get전체재학생충원율() {
		return 전체재학생충원율;
	}

	public void set전체재학생충원율(float 전체재학생충원율) {
		this.전체재학생충원율 = 전체재학생충원율;
	}

	public float get정원내재학생충원율() {
		return 정원내재학생충원율;
	}

	public void set정원내재학생충원율(float 정원내재학생충원율) {
		this.정원내재학생충원율 = 정원내재학생충원율;
	}

	public float get재학생충원율() {
		return 재학생충원율;
	}

	public void set재학생충원율(float 재학생충원율) {
		this.재학생충원율 = 재학생충원율;
	}

	public float getT점수() {
		return T점수;
	}

	public void setT점수(float t점수) {
		T점수 = t점수;
	}

	public String get신설연도() {
		return 신설연도;
	}

	public void set신설연도(String 신설연도) {
		this.신설연도 = 신설연도;
	}

	public int get타학과전과자() {
		return 타학과전과자;
	}

	public void set타학과전과자(int 타학과전과자) {
		this.타학과전과자 = 타학과전과자;
	}

}