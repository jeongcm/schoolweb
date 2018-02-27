package 설문조사view;

public class 설문조사view {
	
	private int 년도;
	private String 단과대학;
	private String 학과명;
	private int 참여학생수;
	private float 설문조사총점;
	
	private float 학생만족도평가;
	private double T점수;
	
	public int get년도(){
		return 년도;
	}
	public void set년도(int 년도){
		this.년도=년도;
	}
	
	public String get단과대학(){
		return 단과대학;
	}
	public void set단과대학(String 단과대학){
		this.단과대학=단과대학;
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

}