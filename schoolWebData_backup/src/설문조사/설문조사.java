package 설문조사;

public class 설문조사 {
	private int 년도;
	private String 학과명;
	private float 설문조사총점;
	private int 참여학생수;
	private float 학생만족도평가;
	private double T점수;
	private int 연번;
	private String 입력부서;
	
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
	public int get참여학생수(){
		return 참여학생수;
	}
	public void set참여학생수(int 참여학생수){
		this.참여학생수=참여학생수;
	}
	public float get설문조사총점(){
		return 설문조사총점;
	}
	public void set설문조사총점(float 설문조사총점){
		this.설문조사총점=설문조사총점;
	}
	public float get학생만족도평가(){
		return 학생만족도평가;
	}
	public void set학생만족도평가(float 학생만족도평가){
		this.학생만족도평가=학생만족도평가;
	}
	public double getT점수(){
		return T점수;
	}
	public void setT점수(double T점수){
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