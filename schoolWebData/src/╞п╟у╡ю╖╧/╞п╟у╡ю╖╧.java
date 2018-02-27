package 특허등록;

public class 특허등록 {
	
	private String 학과명;
	private String 입력부서;
	
	private int 년도;
	private int 전임교원수;
	private int 국제;
	private int 국내;
	private float 특허이전료기술이전;
	private float 특허등록및기술이전수입료;
	private float T점수;
	private int 연번;
	
	
	public int get년도(){
		return 년도;
	}
	public void set년도(int 년도){
		this.년도=년도;
	}
	
	public String get학과명(){
		return 학과명;
	}
	public void set학과명(String 학과명){
		this.학과명=학과명;
	}
	
	public int get전임교원수(){
		return 전임교원수;
	}
	public void set전임교원수(int 전임교원수){
		this.전임교원수=전임교원수;
	}

	public int get국제(){
		return 국제;
	}
	public void set국제(int 국제){
		this.국제=국제;
	}
	public int get국내(){
		return 국내;
	}
	public void set국내(int 국내){
		this.국내=국내;
	}
	public float get특허이전료기술이전(){
		return 특허이전료기술이전;
	}
	public void set특허이전료기술이전(float 특허이전료기술이전){
		this.특허이전료기술이전=특허이전료기술이전;
	}
	public float get특허등록및기술이전수입료(){
		return 특허등록및기술이전수입료;
	}
	public void set특허등록및기술이전수입료(float 특허등록및기술이전수입료){
		this.특허등록및기술이전수입료=특허등록및기술이전수입료;
	}
	public double getT점수(){
		return T점수;
	}
	public void setT점수(float T점수){
		this.T점수=T점수;
	}
	public int get연번(){
		return 연번;
	}
	public void set연번(int 연번){
		this.연번=연번;
	}
	public String get입력부서() {
		return 입력부서;
	}
	public void set입력부서(String 입력부서) {
		this.입력부서 = 입력부서;
	}
}
