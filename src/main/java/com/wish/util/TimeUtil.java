package com.wish.util;



import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeUtil {
	private static Logger logger = Logger.getLogger(TimeUtil.class);
	public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final DateFormat formatw = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat formati = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final DateFormat formatm = new SimpleDateFormat("MM-dd HH:mm");
	public static final DateFormat formats = new SimpleDateFormat("MM-dd");

	public static final DateFormat formath = new SimpleDateFormat("yy-MM-dd");
	public static final DateFormat formatz = new SimpleDateFormat("yyyyMMdd");
	
	public static final DateFormat formatx = new SimpleDateFormat("yyyyMMddHHmmssSS");
	
	public static String formatDate(Date date, int style) {
		if (date == null) {
			return "";
		}
		switch (style) {
		case 5:
			return formatx.format(date);
		case 4:
			return formats.format(date);
		case 3:
			return formatm.format(date);
		case 2:
			return format.format(date);
		default:
			return formatw.format(date);
		}
	}
	/** 
     * 时间与当前时间相差距离多少天多少小时多少分多少秒 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return String 返回值为：xx天xx小时xx分xx秒 
     */  
    public static String getdiffercuTime( String str2) {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date one;  
        Date two;  
        long day = 0;  
        long hour = 0;  
        long min = 0;  
        long sec = 0;  
        try {  
        	
			one = new Date();
					//df.parse(str1); 
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            day = diff / (24 * 60 * 60 * 1000);  
            hour = (diff / (60 * 60 * 1000) - day * 24);  
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
        } catch (java.text.ParseException e) {  
            e.printStackTrace();  
        }  
        String restring="";
        if(day>0){
        	restring=day+"天";
        }
        if(hour>0){
        	restring+=hour+"小时";
        }
        restring+= + min + "分" + sec + "秒"; 
       // return day + "天" + hour + "小时" + min + "分" + sec + "秒"; 
        return restring;
    }
	public static String getFromDataToString(Date date){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	public static Date getFromStringToData(String time)    {
		Date date;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(time==null){
			return date=new Date();
		}
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("时间转型异常", e);
			return date=new Date();
		}
		return date;
	}  
    
    /**
	 * 时间戳转为时间类型
	 * @param time
	 * @return
	 */
	public static Date getLongTime(long time) {
		return new Date(time);
//		return new Date(time * 1000);
	}

	/**
	 * 获取当前时间戳
	 * @return
	 */
	public static  long getTimeSec(){
		/* Calendar today = Calendar.getInstance();
	        today.setTimeInMillis(System.currentTimeMillis());
	        today.set(Calendar.HOUR_OF_DAY, 0);
	        today.set(Calendar.MINUTE, 0);
	        today.set(Calendar.SECOND, 0);
	        long beginTime = today.getTimeInMillis() / 1000;*/
			return Calendar.getInstance().getTimeInMillis();
//			return Calendar.getInstance().getTimeInMillis() / 1000;
	}
	/**
	 * 日期格式转换成时间戳
	 * @return
	 * @throws ParseException
	 */
	public static long getTimeStamp(Date time)
	{
	    long timeStemp=0;
		timeStemp = time.getTime()/1000;
		return timeStemp;
	}
	/**
	 * 日期格式转换成时间戳
	 * @return
	 * @throws ParseException
	 */
	public static long getTimeStamp(String time)
	{
		 SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     Date date;
	     long timeStemp=0;
		try {
			date = simpleDateFormat.parse(time);
//			date = simpleDateFormat.parse("2010-06-25 18:26");
			timeStemp = date.getTime()/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			timeStemp=0;
		}
		return timeStemp;
	}
	public static String getTimeUni(){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat format=new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date=new Date();
		return format.format(date);
	}
	public static String getTimeYMD(){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat format=new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date=new Date();
		return format.format(date);
	}
	public static int getTimeYMDToInt(){
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
//		SimpleDateFormat format=new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date=new Date();
		return Integer.parseInt(format.format(date));
	}
	public static String getYLFromDataToString(Date date){
		SimpleDateFormat format=new SimpleDateFormat("yyyymmddhhMMss");
		return format.format(date);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(Math.random());
		// TODO Auto-generated method stub
		System.out.println(getYLFromDataToString(new Date()));
//		System.out.println(getTimeUni());
//		System.out.println(getTimeStamp("2015-02-27 16:31:00"));
//		System.out.println(getTimeSec());
//		System.out.println(getDateTime("0000-00-00").getDate());
//		System.out.println((getTimeStamp("2014-12-15 14:30")-getTimeStamp("2014-11-10 00:00"))/10800);
//			System.out.println(getTimeSec());
//			System.out.println(1419387271);
//			
//			System.out.println(getTime(new Date("Jan 16, 2015 5:03:40 PM")));
//		System.out.println((getTimeStamp("2014-11-12 10:11:00")-getTimeStamp("2014-11-10 11:11:00")));
//		1436100520
		System.out.println(getTimeStamp(new Date()));
//		System.out.println(getDateTime(1416213888L));
//		System.out.println(getDateTime(1416213977L));
		System.out.println(getTimeYMD());
//		System.out.println(getLongTime(1415344497L));
		
		//System.out.println((8<<2));
	}
}
