package 중도탈락률외부;

public class 중도탈락률외부 {
	private String 학과명;
	private String 대학명;
	private String 비고;
	private String 입력부서;
	private int 재적학생수;
	private int 타학과전과자;
	private int 계;
	private int 미등록;
	private int 미복학;
	private int 자퇴;
	private int 학사경고;
	private int 기타;
	private float 중도탈락률;
	private float T점수;

	private int 연번;

	public String get학과명() {
		return 학과명;
	}

	public void set학과명(String 학과명) {
		this.학과명 = 학과명;
	}

	public int get계() {
		return 계;
	}

	public void set계(int 계) {
		this.계 = 계;
	}

	public int get재적학생수() {
		return 재적학생수;
	}

	public void set재적학생수(int 재적학생수) {
		this.재적학생수 = 재적학생수;
	}

	public float getT점수() {
		return T점수;
	}

	public void setT점수(float t점수) {
		T점수 = t점수;
	}

	public int get학사경고() {
		return 학사경고;
	}

	public void set학사경고(int 학사경고) {
		this.학사경고 = 학사경고;
	}

	public float get중도탈락률() {
		return 중도탈락률;
	}

	public void set중도탈락률(float 중도탈락률) {
		this.중도탈락률 = 중도탈락률;
	}

	public int get기타() {
		return 기타;
	}

	public void set기타(int 기타) {
		this.기타 = 기타;
	}

	public int get미복학() {
		return 미복학;
	}

	public void set미복학(int 미복학) {
		this.미복학 = 미복학;
	}

	public int get미등록() {
		return 미등록;
	}

	public void set미등록(int 미등록) {
		this.미등록 = 미등록;
	}

	public int get자퇴() {
		return 자퇴;
	}

	public void set자퇴(int 자퇴) {
		this.자퇴 = 자퇴;
	}

	public int get연번() {
		return 연번;
	}

	public void set연번(int 연번) {
		this.연번 = 연번;
	}

	public String get대학명() {
		return 대학명;
	}

	public void set대학명(String 대학명) {
		this.대학명 = 대학명;
	}

	public String get비고() {
		return 비고;
	}

	public void set비고(String 비고) {
		this.비고 = 비고;
	}

	public String get입력부서() {
		return 입력부서;
	}

	public void set입력부서(String 입력부서) {
		this.입력부서 = 입력부서;
	}

	public int get타학과전과자() {
		return 타학과전과자;
	}

	public void set타학과전과자(int 타학과전과자) {
		this.타학과전과자 = 타학과전과자;
	}

}