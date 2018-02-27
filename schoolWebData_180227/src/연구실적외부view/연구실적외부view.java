package 연구실적외부view;

public class 연구실적외부view {
	private String 기준년도;
	private String 학교;
	private String 학과;
	private int 전임교원;
	private float 저서;
	private float 역서;
	private float 연구재단B;
	private float SCI급E;
	private double 전임교원1인당연구실적;
	private double T점수;
	
	public void set기준년도(String 기준년도){
		this.기준년도=기준년도;
	}
	public String get기준년도(){
		return 기준년도;
	}
	
	public void set학교(String 학교){
		this.학교=학교;
	}
	public String get학교(){
		return 학교;
	}
	
	public void set학과(String 학과){
		this.학과=학과;
	}
	public String get학과명(){
		return 학과;
	}
	
	public void set전임교원(int 전임교원){
		this.전임교원=전임교원;
	}
	public int get전임교원(){
		return 전임교원;
	}
	
	public void set저서(float 저서){
		this.저서=저서;
	}
	public float get저서(){
		return 저서;
	}
	
	public void set역서(float 역서){
		this.역서=역서;
	}
	public float get역서(){
		return 역서;
	}
	
	public void set연구재단B(float 연구재단B){
		this.연구재단B=연구재단B;
	}
	public float get연구재단B(){
		return 연구재단B;
	}
	
	public void setSCI급E(float SCI급E){
		this.SCI급E=SCI급E;
	}
	public float getSCI급E(){
		return SCI급E;
	}
	
	public void set전임교원1인당연구실적(double 전임교원1인당연구실적){
		this.전임교원1인당연구실적=전임교원1인당연구실적;
	}
	public double get전임교원1인당연구실적(){
		return 전임교원1인당연구실적;
	}
	
	public void setT점수(double T점수){
		this.T점수=T점수;
	}
	public double getT점수(){
		return T점수;
	}
}
