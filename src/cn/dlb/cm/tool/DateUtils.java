package cn.dlb.cm.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	private static SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date myDateFormat(String englishDate) throws ParseException{
	       SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
	       String yue=null;
	       
	       try{
	       	   Date date=sdf1.parse(englishDate);
	           String sDate=sdf.format(date);
	           int index=sDate.indexOf("-");
	           yue=sDate.substring(index+1);
	           int index2=englishDate.lastIndexOf(" ");
	           englishDate=englishDate.substring(index2);
	           
	           System.out.println((englishDate+"-"+yue));
	          
	       }
	       catch (ParseException e)
	       {
	           e.printStackTrace();
	       }
	       return sdf.parse(englishDate+"-"+yue);
	}
	
	public static String formate(Date date){
		return sdf.format(date);
	}
	
	public static Date parse(String str){
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			System.out.println("导入日期为空");
			e.printStackTrace();
		}
		return null;
	}
}
