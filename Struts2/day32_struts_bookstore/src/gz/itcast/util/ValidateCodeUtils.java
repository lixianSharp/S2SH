package gz.itcast.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class ValidateCodeUtils {

	/**
	 * ������֤��
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	static Random ran = new Random();
	public static void main(String[] args) throws Exception {
		OutputStream out = new FileOutputStream("e:/code1.png");
		genNewCode(out);
	}

	/**
	 * ����һ��ͼƬ����д����ָ���������λ��
	 * @param out
	 * @throws IOException
	 */
	public static String genNewCode(OutputStream out) throws IOException {
		int width = 120;
		int height = 50;
		//���ڴ�������һ��ͼƬ
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		//�滭����ͼƬ
		//�õ�����
		Graphics g = image.getGraphics();
		
		//�ѵ�ɫ��ɻ�ɫ
		g.setColor(Color.GRAY);//��ɫ
		g.fillRect(0, 0, width, height);
		
		//д�ĸ�������� 5623
		String number = "";
		for(int i=1;i<=4;i++){
			number += ran.nextInt(10);
		}
		g.setColor(Color.BLACK);
		//����
		g.setFont(new Font("����",Font.ITALIC,35));
		//д��
		g.drawString(number, 20, 40);
		
		//д�����ɫ������
		for(int i=1;i<=30;i++){
			int x1 = ran.nextInt(width);
			int x2 = ran.nextInt(width);
			int y1 = ran.nextInt(height);
			int y2 = ran.nextInt(height);
			g.setColor(getRandomColr());
			g.drawLine(x1, y1, x2, y2);
		}
		
		//��ͼƬд����Ӳ��
		ImageIO.write(image, "png", out);
		return number;
	}
	
	/**
	 * ���������ɫ
	 */
	private static Color getRandomColr(){
		int r = ran.nextInt(256);
		int g = ran.nextInt(256);
		int b = ran.nextInt(256);
		return new Color(r,g,b);
	}
}
