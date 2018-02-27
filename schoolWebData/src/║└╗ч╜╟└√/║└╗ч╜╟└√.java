package 봉사실적;

public class 봉사실적 {
	private String 학과명;
	private String 비고;
	private String 성명;
	private String 입력부서;
	
	private int 학번;
	private int 이수학점;
	private int 년도;
	private String 학기;
		
	
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
	public int get학번(){
		return 학번;
	}
	public void set학번(int 학번){
		this.학번=학번;
	}
	public String get성명(){
		return 성명;
	}
	public void set성명(String 성명){
		this.성명=성명;
	}
	
	public String get비고(){
		return 비고;
	}
	public void set비고(String 비고){
		this.비고=비고;
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
	public int get이수학점() {
		return 이수학점;
	}
	public void set이수학점(int 이수학점) {
		this.이수학점 = 이수학점;
	}
	public String get학기() {
		return 학기;
	}
	public void set학기(String 학기) {
		this.학기 = 학기;
	}
}
