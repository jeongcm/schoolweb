package 취업현황;

public class 취업현황 {
   private int 년도;
   private int 졸업자;
   private String 학과명;
   private float 이차유지취업률;
   private Object 학과명list;
   private float 취업률;
   private float T점수;
   private String 입력부서;
   
   private int 연번;
   
   public int get년도() {
      return 년도;
   }

   public void set년도(int 년도) {
      this.년도 = 년도;
   }

   public String get학과명() {
      return 학과명;
   }

   public void set학과명(String 학과명) {
      this.학과명 = 학과명;
   }

   public Object get학과명list() {
      return 학과명list;
   }

   public void set학과명list(Object selVal) {
      this.학과명list = selVal;
   }

   public int get졸업자() {
      return 졸업자;
   }

   public void set졸업자(int 졸업자) {
      this.졸업자 = 졸업자;
   }

   public float get이차유지취업률() {
      return 이차유지취업률;
   }

   public void set이차유지취업률(float 이차유지취업률) {
      this.이차유지취업률 = 이차유지취업률;
   }

   public float get취업률() {
      return 취업률;
   }

   public void set취업률(float 취업률) {
      this.취업률 = 취업률;
   }

   public float getT점수() {
      return T점수;
   }

   public void setT점수(float t점수) {
      T점수 = t점수;
   }

   public int get연번() {
      return 연번;
   }

   public void set연번(int 연번) {
      this.연번 = 연번;
   }

   public String get입력부서() {
      return 입력부서;
   }

   public void set입력부서(String 입력부서) {
      this.입력부서 = 입력부서;
   }

}