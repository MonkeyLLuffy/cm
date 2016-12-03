package cn.dlb.cm.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormate {
	public static SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd");
	public static String formate(Date date){
		return df.format(date);
	}
	public static Date parse(String str){
		try {
			return df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("导入日期为空");
			e.printStackTrace();
		}
		return null;
	}
}
