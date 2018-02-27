package 연구비외부view;

public class 연구비외부view {
	private String 기준년도;
	private String 학교;
	private String 학과;
	private int 전임교원수;
	private int 소계;
	
	private int 연구비C;
	private int 연구비D;
	private int 연구비E;
	private int 연구비F;
	private float 전임교원1인당교외연구비;
	private double T점수;
	
	public String get기준년도(){
		return 기준년도;
	}
	public void set기준년도(String 기준년도){
		this.기준년도=기준년도;
	}
	public String get학교(){
		return 학교;
	}
	public void set학교(String 학교){
		this.학교=학교;
	}
	public String get학과(){
		return 학과;
	}
	public void set학과(String 학과){
		this.학과=학과;
	}
	public int get전임교원수(){
		return 전임교원수;
	}
	public void set전임교원수(int 전임교원수){
		this.전임교원수=전임교원수;
	}
	
	public int get소계(){
		return 소계;
	}
	public void set소계(int 소계){
		this.소계=소계;
	}
	
	public int get연구비C(){
		return 연구비C;
	}
	public void set연구비C(int 연구비C){
		this.연구비C=연구비C;
	}
	public int get연구비D(){
		return 연구비D;
	}
	public void set연구비D(int 연구비D){
		this.연구비D=연구비D;
	}
	public int get연구비E(){
		return 연구비E;
	}
	public void set연구비E(int 연구비E){
		this.연구비E=연구비E;
	}
	public int get연구비F(){
		return 연구비F;
	}
	public void set연구비F(int 연구비F){
		this.연구비F=연구비F;
	}
	
	public float get전임교원1인당교외연구비(){
		return 전임교원1인당교외연구비;
	}
	public void set전임교원1인당교외연구비(float 전임교원1인당교외연구비){
		this.전임교원1인당교외연구비=전임교원1인당교외연구비;
		}
	
	public double getT점수(){
		return T점수;
	}
	public void setT점수(double T점수){
		this.T점수=T점수;
	}
}
