package testUrlcoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestUrlCoder {

	
	public static void main(String[] args) throws UnsupportedEncodingException {
		urlCoder();
	}
	
	public static void urlCoder() throws UnsupportedEncodingException {
		String url = "http://121.58.0.92:80/iptv/bq_xx/xx_index.jsp?"
				+ "epg_info=<server_ip>10.255.23.70</server_ip><group_name>9999</group_name>"
				+ "<group_id>22</group_id><group_path></group_path><oss_user_id>38984017</oss_user_id>"
				+ "<page_url>http://10.255.23.70:33200/EPG/jsp/gzbqbao/en/tvbar/vastomem.jsp</page_url>"
				+ "<partner>HW2X</partner>";
		
		System.out.println(URLEncoder.encode(url, "utf-8"));
		
	}
}
