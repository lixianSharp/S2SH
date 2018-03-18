package gz.itcast.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5���ܹ�����
 * @author APPle
 *
 */
public class MD5Util {

	/**
	 * ����ԭʼ���룬���ؼ���֮��������ַ���
	 * @param password
	 * @return
	 */
	public static String md5(String password){
		try {
			//1)�������������
			MessageDigest md = MessageDigest.getInstance("md5");
			//2�����м���
			byte[] byteArray = md.digest(password.getBytes());
			//-127 -36 -101 -37 82 -48 77 -62 0    54  -37 -40 49 62 -48 85 
			//                                    "36"
			StringBuffer sb = new StringBuffer();
			for(byte b:byteArray){
				//System.out.print(b+" ");
				//��ÿ��10���Ƶ��ֽ���ֵ    ת�� Ϊ   2λ�ַ���ʮ�����Ƶ��ַ���
				sb.append(numToHex(b));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ����һ��10���Ƶ��ֽ���ֵ������2λ��ʮ�����Ƶ��ַ���
	 * @param num
	 * @return
	 */
	private static String numToHex(byte num){
		/**
		 * byte���ͣ�
		 * 		�޷���λ �� ȡֵ��Χ��   0 ~ 255
		 *      �з���Ϊ�� ȡֵ��Χ��   -128 ~ 127 
		 */
		int targetNum = 0;
		//����Ǹ���������תΪ�������ٽ��м��㣻����ֱ��ʹ��
		if(num<0){
			targetNum = 256+num;
		}else{
			targetNum = num;
		}
		//��һλ�ַ���ֵ
		int first = targetNum/16;
		//�ڶ�λ�ַ���ֵ
		int sencond = targetNum%16;
	
		return strArray[first]+strArray[sencond];
	}
	
	private static String[] strArray = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	
	
	public static void main(String[] args) {
		String result = md5("1234"); // 81dc9bdb52d04dc20036dbd8313ed055
		System.out.println(result);  // 81dc9bdb52d04dc20036dbd8313ed055
		
		///System.out.println(Integer.toHexString(-36));
	}
}
