package 신입생현황외부;

public class 신입생현황외부 {
	private String 대학명;
	private String 학과명;
	private String 비고;
	private String 입력부서;
	
	private int 입학자수;
	private int 모집인원;
	
	private float 신입생충원율;
	private float T점수;

	private int 연번;

	public String get학과명() {
		return 학과명;
	}

	public void set학과명(String 학과명) {
		this.학과명 = 학과명;
	}

	public float getT점수() {
		return T점수;
	}

	public void setT점수(float t점수) {
		T점수 = t점수;
	}

	public int get입학자수() {
		return 입학자수;
	}

	public void set입학자수(int 입학자수) {
		this.입학자수 = 입학자수;
	}

	public float get신입생충원율() {
		return 신입생충원율;
	}

	public void set신입생충원율(float 신입생충원율) {
		this.신입생충원율 = 신입생충원율;
	}

	public int get모집인원() {
		return 모집인원;
	}

	public void set모집인원(int 모집인원) {
		this.모집인원 = 모집인원;
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

}