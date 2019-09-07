package org.pcl.springlongkuang.Utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 日期转字符串
	 * 
	 * @param time
	 * @return yyyy-MM
	 */
	public static String dateToStr(Date time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String stringTime = formatter.format(time);
		return stringTime;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param time
	 * @return yyyy-MM-dd
	 */
	public static String dateToString(Date time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String stringTime = formatter.format(time);
		return stringTime;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param time
	 * @return yyyyMMdd
	 */
	public static String dateTimeToString(Date time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd"); // 定义将日期格式要换成的格式
		String stringTime = formatter.format(time);
		return stringTime;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws Exception
	 */
	public static String dateToStringTime(Date date) throws Exception {
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return day.format(date);

	}

	/**
	 * 字符串转日期
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 * @throws Exception
	 */
	public static Date StringToDate(String date) throws Exception {
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		return day.parse(date);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws Exception
	 */
	public static Date stringToDateTime(String date) throws Exception {
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return day.parse(date);
	}

	/**
	 * 比较日期大小 第一个比第二个大则返回 1 第一个日期比第二小则返回 -1 其他返回 0
	 * 
	 * @return int
	 */
	public static int compareDate(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	 /**
     * 
      * getCompareDate(返回两日期比较差)
      * @Title: getCompareDate
      * @Description: TODO
      * @param @param startDate
      * @param @param endDate
      * @param @return
      * @param @throws ParseException    设定文件
      * @return long    返回类型
      * @throws
     */
	public static long getCompareDate(String startDate,String endDate) throws ParseException  {  
	     SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");  
	     Date date1=formatter.parse(startDate);      
	     Date date2=formatter.parse(endDate);  
	     long l = date2.getTime() - date1.getTime();  
	     long d = l/(24*60*60*1000);  
	     return d;  
	 }
	 /**
	 * 
	  * TODO(获取前后日期 i为正数 向后推迟i天，负数时向前提前i天)
	  * @Title: getdate
	  * @Description: TODO
	  * @param @param i
	  * @param @return    设定文件
	  * @return Date    返回类型
	  * @throws
	 */
	public static Date getdate(int i){
	 Date dat = null;
	 Calendar cd = Calendar.getInstance();
	 cd.add(Calendar.DATE, i);
	 dat = cd.getTime();
	 SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 Timestamp date = Timestamp.valueOf(dformat.format(dat));
	 return date;
	 }
}
