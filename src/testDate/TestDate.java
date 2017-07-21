package testDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

	public static void main(String[] args) {

		dateformate("20170630235959", "yyyyMMddHHmmss");
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
}
