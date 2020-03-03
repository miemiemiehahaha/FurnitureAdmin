package FurnitureAdm.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class GetRandomid {
	public static String getRandomFileName() {

		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();
		String str = simpleDateFormat.format(date); //拿到当前的时间

		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999999 - 10000000 + 1)) + 10000000;// 获取5位随机数

		return  str + rannum;// 当前时间
	}
//	public static String main(String[] args) {
//		String fileName = GetRandomid.getRandomFileName();
//		return fileName;
//	}
}