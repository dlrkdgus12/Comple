package comple.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * org.apache.commons.lang.time.DateUtils ?óê ?óÜ?äîÍ≤ÉÎßå ÎßåÎì§Í∏?
 * @author ttobii
 *
 */
public class DateUtil {

	public static long getCurTimeMils(){
		Date d = new Date();
		return d.getTime();
	}
	
	public static String timeToString(Date time, String pattern){
		if(time == null){
			return "?óÜ?ùå";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(time);
	}
	
	public static long stringToTime(String time, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		long result = 0;
		try{
			result = sdf.parse(time).getTime(); 
		} catch (Exception e){}
		return result;
	}
	
	/**
	 * formetter "yyyy-MM-dd HH:mm:ss"
	 * @param time
	 * @return
	 */
	public static String timeToStdString(Date date) {
		return timeToString(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * formetter "yyyy-MM-dd HH:mm:ss"
	 * @param time
	 * @return
	 */
	public static String timeToStdString(long time){
		return timeToString(new Date(time), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * formetter "yyyy-MM-dd HH:mm:ss"
	 * @param time
	 * @return
	 */
	public static long stdStringToTime(String time) {
		return stringToTime(time, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * "yyyy?ÖÑ MM?õî dd?ùº"
	 * @param time
	 * @return
	 */
	public static String timeToKorString(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy?ÖÑ MM?õî dd?ùº");
		Date standarddate = new Date(time);
		return sdf.format(standarddate);
	}

	public static boolean correntIsAftter(Long lastsenddate, int weight) {
		long cur = getCurTimeMils();
		if((cur-lastsenddate)>weight){
			return true;
		}
		return false;
	}

	public static String getFormatString(String pattern) {
		return getFormatString(pattern, java.util.Locale.KOREA);
	}

	public static String getFormatString(String pattern, java.util.Locale locale) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (pattern, locale);
        String dateString = formatter.format(new java.util.Date());
        return dateString;
    }
    
	public static int SYSTEM_SEQ = 0;
    /**
     * ?ãú?ä§?Öú ?ãú???ä§Î•? Í∞??†∏?ò®?ã§.
     * @return ?ãú?ä§?Öú?ãú???ä§ Î¨∏Ïûê?ó¥
     */
    public synchronized static String getSytemSeq() {
    	SYSTEM_SEQ++;
    	if(SYSTEM_SEQ == 1000)	SYSTEM_SEQ = 1;
    	
    	String str = Integer.toString(SYSTEM_SEQ);
        while (str.length() < 3) {
            str = "0" + str;
        }
        
        return getFormatString("yyyyMMddHHmmss") + str;
    }

	public static Date getExpireAfterTime(Date dealdate, Integer validperiod) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(dealdate);
		date.add(GregorianCalendar.DATE, validperiod);
		date.set(GregorianCalendar.HOUR_OF_DAY, 23);
		date.set(GregorianCalendar.MINUTE, 59);
		date.set(GregorianCalendar.SECOND, 59);
		date.set(GregorianCalendar.MILLISECOND, 0);
		return date.getTime();
	}

	public static Date clearLessOfDay(Date regdate) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(regdate);
		date.set(GregorianCalendar.HOUR_OF_DAY, 0);
		date.set(GregorianCalendar.MINUTE, 0);
		date.set(GregorianCalendar.SECOND, 0);
		date.set(GregorianCalendar.MILLISECOND, 0);
		return date.getTime();
	}


}
