package FurnitureAdm.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class GetRandomid {
	public static String getRandomFileName() {

		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();
		String str = simpleDateFormat.format(date); //�õ���ǰ��ʱ��

		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999999 - 10000000 + 1)) + 10000000;// ��ȡ5λ�����

		return  str + rannum;// ��ǰʱ��
	}
//	public static String main(String[] args) {
//		String fileName = GetRandomid.getRandomFileName();
//		return fileName;
//	}
}