package defaultMethod;

import java.math.BigDecimal;

public class defaultClass {

	public static boolean nullCheck(String value) {
		if (null == value || value.isEmpty()) {
			return true;
		}
		return false;
	}
	public static float  �ߵ�Ż����T����(float ����, float ���, float ǥ������) //�ߵ�Ż���� - �ٸ��Ŷ� �ٸ��� ���� ������ ������. 
	{

		BigDecimal B_���� = new BigDecimal(String.valueOf(����));
		BigDecimal B_��� = new BigDecimal(String.valueOf(���));
		BigDecimal B_ǥ������ = new BigDecimal(String.valueOf(ǥ������));

		if(����==100 ){
			return 20;
		}
		Log log = new Log();

		BigDecimal ten = new BigDecimal("10");
		BigDecimal fif = new BigDecimal("50");

		BigDecimal mol = ten.multiply((B_���.subtract(B_����))); //�ߵ�Ż������ ���ϴ½��̴ٸ� ��տ��� ������ ������ -12/19���ڷ� �����Ѱ�

		if (mol.compareTo(BigDecimal.ZERO) != 0 && B_ǥ������.compareTo(BigDecimal.ZERO) != 0) 
		{

				float T���� = 20;
				float T������� = (mol.divide(B_ǥ������, 2, BigDecimal.ROUND_HALF_UP).add(fif)).floatValue();//�������� Ƽ������ ���ʿ� round������ �ϱ� ������ �ݿø��ص� �� -12.26

				log.debug("T���� ���� : " + B_���� + "  T���� ��� : " + B_��� + "  T���� ǥ������" + B_ǥ������+"   T���� ����" + T�������);
				System.out.println("T���� ���� : " + B_���� + "  T���� ��� : " + B_��� + "  T���� ǥ������" + B_ǥ������+"   T���� ����" + T�������);

				log.close();
				
				if (T������� >= 80)
				{
					T���� = 80;
				} 
				else if (T������� <= 20) 
				{
					T���� = 20;
				} 
				else 
				{
					T���� = T�������;
				}
				return T����;
		}
		else 
		{
			return 20;
		}
	}
	
	public static float T����(float ����, float ���, float ǥ������) 
	{

		BigDecimal B_���� = new BigDecimal(String.valueOf(����));
		BigDecimal B_��� = new BigDecimal(String.valueOf(���));
		BigDecimal B_ǥ������ = new BigDecimal(String.valueOf(ǥ������));

		if(����==0 ){
			return 20;
		}
		
		Log log = new Log();

		BigDecimal ten = new BigDecimal("10");
		BigDecimal fif = new BigDecimal("50");

		BigDecimal mol = ten.multiply((B_����.subtract(B_���)));
		System.out.println("mol : " + mol);

		if (mol.compareTo(BigDecimal.ZERO) != 0 && B_ǥ������.compareTo(BigDecimal.ZERO) != 0) 
		{
				float T���� = 20;
				float T������� = (mol.divide(B_ǥ������, 2, BigDecimal.ROUND_HALF_UP).add(fif)).floatValue();
				log.debug("T���� ���� : " + B_���� + "  T���� ��� : " + B_��� + "  T���� ǥ������" + B_ǥ������+"   T���� ����" + T�������);
				System.out.println("T���� ���� : " + B_���� + "  T���� ��� : " + B_��� + "  T���� ǥ������" + B_ǥ������+"   T���� ����" + T�������);

				log.close();
				
				if (T������� >= 80)
				{
					T���� = 80;
				} 
				else if (T������� <= 20) 
				{
					T���� = 20;
				} 
				else if( T������� ==0)
				{
					T���� = 20;
				}
				else 
				{
					T���� = T�������;
				}
				return T����;
		}
		else 
		{
			return 20;
		}
	}

}


