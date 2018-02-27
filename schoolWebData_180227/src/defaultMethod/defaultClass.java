package defaultMethod;

import java.math.BigDecimal;

public class defaultClass {

	public static boolean nullCheck(String value) {
		if (null == value || value.isEmpty()) {
			return true;
		}
		return false;
	}
	public static float  중도탈락률T점수(float 비율, float 평균, float 표준편차) //중도탈락률 - 다른거랑 다르게 값이 높으면 안좋다. 
	{

		BigDecimal B_비율 = new BigDecimal(String.valueOf(비율));
		BigDecimal B_평균 = new BigDecimal(String.valueOf(평균));
		BigDecimal B_표준편차 = new BigDecimal(String.valueOf(표준편차));

		if(비율==100 ){
			return 20;
		}
		Log log = new Log();

		BigDecimal ten = new BigDecimal("10");
		BigDecimal fif = new BigDecimal("50");

		BigDecimal mol = ten.multiply((B_평균.subtract(B_비율))); //중도탈락률은 구하는식이다름 평균에서 비율을 빼야함 -12/19일자로 수정한것

		if (mol.compareTo(BigDecimal.ZERO) != 0 && B_표준편차.compareTo(BigDecimal.ZERO) != 0) 
		{

				float T점수 = 20;
				float T점수계산 = (mol.divide(B_표준편차, 2, BigDecimal.ROUND_HALF_UP).add(fif)).floatValue();//엑셀에서 티점수는 애초에 round값으로 하기 때문에 반올림해도 됨 -12.26

				log.debug("T점수 비율 : " + B_비율 + "  T점수 평균 : " + B_평균 + "  T점수 표준편차" + B_표준편차+"   T점수 최종" + T점수계산);
				System.out.println("T점수 비율 : " + B_비율 + "  T점수 평균 : " + B_평균 + "  T점수 표준편차" + B_표준편차+"   T점수 최종" + T점수계산);

				log.close();
				
				if (T점수계산 >= 80)
				{
					T점수 = 80;
				} 
				else if (T점수계산 <= 20) 
				{
					T점수 = 20;
				} 
				else 
				{
					T점수 = T점수계산;
				}
				return T점수;
		}
		else 
		{
			return 20;
		}
	}
	
	public static float T점수(float 비율, float 평균, float 표준편차) 
	{

		BigDecimal B_비율 = new BigDecimal(String.valueOf(비율));
		BigDecimal B_평균 = new BigDecimal(String.valueOf(평균));
		BigDecimal B_표준편차 = new BigDecimal(String.valueOf(표준편차));

		if(비율==0 ){
			return 20;
		}
		
		Log log = new Log();

		BigDecimal ten = new BigDecimal("10");
		BigDecimal fif = new BigDecimal("50");

		BigDecimal mol = ten.multiply((B_비율.subtract(B_평균)));
		System.out.println("mol : " + mol);

		if (mol.compareTo(BigDecimal.ZERO) != 0 && B_표준편차.compareTo(BigDecimal.ZERO) != 0) 
		{
				float T점수 = 20;
				float T점수계산 = (mol.divide(B_표준편차, 2, BigDecimal.ROUND_HALF_UP).add(fif)).floatValue();
				log.debug("T점수 비율 : " + B_비율 + "  T점수 평균 : " + B_평균 + "  T점수 표준편차" + B_표준편차+"   T점수 최종" + T점수계산);
				System.out.println("T점수 비율 : " + B_비율 + "  T점수 평균 : " + B_평균 + "  T점수 표준편차" + B_표준편차+"   T점수 최종" + T점수계산);

				log.close();
				
				if (T점수계산 >= 80)
				{
					T점수 = 80;
				} 
				else if (T점수계산 <= 20) 
				{
					T점수 = 20;
				} 
				else if( T점수계산 ==0)
				{
					T점수 = 20;
				}
				else 
				{
					T점수 = T점수계산;
				}
				return T점수;
		}
		else 
		{
			return 20;
		}
	}

}


