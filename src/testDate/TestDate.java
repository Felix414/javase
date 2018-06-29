package testDate;

import java.io.DataInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

	public static void main(String[] args) {

		//dateformate("20170630235959", "yyyyMMddHHmmss");
		getDays("20181130");
	}

	static void dateformate(String date, String format) {
	    Date d;
		try {
			d = new SimpleDateFormat(format).parse(date);
			 System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	static void getDays(String dateStr){
		dateStr += "000000";
		try {
			Date d = new SimpleDateFormat("yyyyMMddHHmmss").parse(dateStr);
			long l = d.getTime()-new Date().getTime();
			
			System.out.println(l/1000/60/60/24);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
