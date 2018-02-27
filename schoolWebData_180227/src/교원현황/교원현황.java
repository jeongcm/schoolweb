package 교원현황;

public class 교원현황 {
	private int 년도;
	private String 학과명;
	private int 일학기;
	private int 이학기;
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
	public int get일학기(){
		return 일학기;
	}
	public void set일학기(int first학기){
		this.일학기=first학기;
	}
	public int get이학기(){
		return 이학기;
	}
	public void set이학기(int 이학기){
		this.이학기=이학기;
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