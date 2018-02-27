package 현장실습;

public class 현장실습 {
	private int 년도;
	private String 학과명;
	private int _1학기;
	private int _2학기;
	private int 합계;
	private int 장기1학기;
	private int 장기2학기;
	private int 장기합계;
	
	private String 입력부서;
	
	private int 대상학생수;
	private float 이수학생비율;
	private double T점수;
	
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
	
	public float get이수학생비율(){
		return 이수학생비율;
	}
	public void set이수학생비율(float 이수학생비율){
		this.이수학생비율=이수학생비율;
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
	public int get_1학기() {
		return _1학기;
	}
	public void set_1학기(int _1학기) {
		this._1학기 = _1학기;
	}
	public int get_2학기() {
		return _2학기;
	}
	public void set_2학기(int _2학기) {
		this._2학기 = _2학기;
	}
	public int get합계() {
		return 합계;
	}
	public void set합계(int 합계) {
		this.합계 = 합계;
	}
	public int get장기1학기() {
		return 장기1학기;
	}
	public void set장기1학기(int 장기1학기) {
		this.장기1학기 = 장기1학기;
	}
	public int get장기2학기() {
		return 장기2학기;
	}
	public void set장기2학기(int 장기2학기) {
		this.장기2학기 = 장기2학기;
	}
	public int get장기합계() {
		return 장기합계;
	}
	public void set장기합계(int 장기합계) {
		this.장기합계 = 장기합계;
	}
	public int get대상학생수() {
		return 대상학생수;
	}
	public void set대상학생수(int 대상학생수) {
		this.대상학생수 = 대상학생수;
	}
}
