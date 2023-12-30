package File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//s75 126
public class RestDay {
	public static String toYMD(Calendar dd){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dd.getTime());
	}
	public static boolean isRest(String day){
		//��, �쇰� �щ��濡� 
		boolean isRest=false;
		Calendar tod= todate(day);
		if(tod.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || 
				tod.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY ){ //������ ���� �쇱����
			isRest=true;
		}
		return isRest;
	}
	public static String goGo(String ss,int day){
		Calendar cal=todate(ss);
		//-7�쇱�� ����硫� �쇱＜��
		cal.add(Calendar.DAY_OF_YEAR, day);
		//移쇰����瑜� 臾몄���대� 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}
	public static String toWantedDay(String ss,int round){
		Calendar cal=todate(ss);
		//-7�쇱�� ����硫� �쇱＜ ��
		cal.add(Calendar.DAY_OF_YEAR, -(round*7));
		//罹�由곕��瑜� 臾몄���대� 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}
	public static Calendar todate(String ss){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dd=new Date();
		try {
			dd=sdf.parse(ss);
		} catch (ParseException e) {
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(dd);
		return cal;
	}
	public static String toDay(String rs){  // 8�� 16, 2017
		SimpleDateFormat sdfDesired = new SimpleDateFormat("MMMMM dd, yyyy");
		Calendar ts=todate(rs);
		String sss=sdfDesired.format(ts.getTime());
		return sss;
	}
	public static Date toDate(int year, int month, int day){
		return toCalendar(year,month,day).getTime();
	}
	public static Calendar toCalendar(int year, int month, int day){
		Calendar cal=Calendar.getInstance();
		cal.set(year, month-1, day);
		return cal;
	}
	public static String toStrDate(Date dd){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(dd);
	}
}
