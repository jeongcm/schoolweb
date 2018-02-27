package 캡스톤디자인;

public class 캡스톤디자인 {
	private int 년도;
	private String 학과명;
	private String 입력부서;
	
	private int 이수1학기;
	private int 이수2학기;
	private int 대상1학기;
	private int 대상2학기;
	private int 연번;
	
	private int 이수합계;
	private int 대상합계;
	
	
	private float 이수학생비율;
	private float T점수;
	
	
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
	public int get이수1학기(){
		return 이수1학기;
	}
	public void set이수1학기(int 이수1학기){
		this.이수1학기=이수1학기;
	}
	public int get이수2학기(){
		return 이수2학기;
	}
	public void set이수2학기(int 이수2학기){
		this.이수2학기=이수2학기;
	}

	public int get대상1학기(){
		return 대상1학기;
	}
	public void set대상1학기(int 대상1학기){
		this.대상1학기=대상1학기;
	}
	public int get대상2학기(){
		return 대상2학기;
	}
	public void set대상2학기(int 대상2학기){
		this.대상2학기=대상2학기;
	}
	
	public int get연번(){
		return 연번;
	}
	public void set연번(int 연번){
		this.연번=연번;
	}
	public float get이수학생비율() {
		return 이수학생비율;
	}
	public void set이수학생비율(float 이수학생비율) {
		this.이수학생비율 = 이수학생비율;
	}
	public float getT점수() {
		return T점수;
	}
	public void setT점수(float t점수) {
		T점수 = t점수;
	}
	public String get입력부서() {
		return 입력부서;
	}
	public void set입력부서(String 입력부서) {
		this.입력부서 = 입력부서;
	}
	public int get이수합계() {
		return 이수합계;
	}
	public void set이수합계(int 이수합계) {
		this.이수합계 = 이수합계;
	}
	public int get대상합계() {
		return 대상합계;
	}
	public void set대상합계(int 대상합계) {
		this.대상합계 = 대상합계;
	}
}
