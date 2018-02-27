package 기술이전;

public class 기술이전 {
	
	private String 학과명;
	private String 입력부서;
	private String 대표발명자;
	private String 지식재산권;
	
	private float 정액기술료;
	
	private int 년도;
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
	public String get대표발명자() {
		return 대표발명자;
	}
	public void set대표발명자(String 대표발명자) {
		this.대표발명자 = 대표발명자;
	}
	public String get지식재산권() {
		return 지식재산권;
	}
	public void set지식재산권(String 지식재산권) {
		this.지식재산권 = 지식재산권;
	}
	public float get정액기술료() {
		return 정액기술료;
	}
	public void set정액기술료(float 정액기술료) {
		this.정액기술료 = 정액기술료;
	}
}
