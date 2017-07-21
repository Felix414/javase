package testJSON;

import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJSON {
	public static void main(String[] args) {
		// testJSON();
		// testJSON2();

		URL url;
		HttpURLConnection con = null;
		try {
			url = new URL("http://61.181.152.158:9984/search/UserJuHaoKan/getUserJuHaoKan/userId/8000000000125");
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.connect();

			System.out.println(con.getInputStream());
			con.getInputStream(); // ���������ʽ��������˷������󣬷���������˽��޷���ӡhello world
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void testJSON() {
		String str = "{\"rows\":[{\"_id\":\"594c7d3f89f2c3b463c718ef\",\"userId\":\"8000000000125\","
				+ "\"createTime\":\"20170623103023\",\"updateTime\":\"20170623103023\",\"flag\":\"0\","
				+ "\"productId\":\"gdprdt154\",\"endTime\":\"20170630\",\"__v\":0},"
				+ "{\"_id\":\"594c7d3f89f2c3b463c718ef\",\"userId\":\"8000000000125\","
				+ "\"createTime\":\"20170623103023\",\"updateTime\":\"20170623103023\",\"flag\":\"0\","
				+ "\"productId\":\"gdprdt176\",\"endTime\":\"20170930\",\"__v\":0}]}";
		JSONObject jsonObj = JSONObject.fromObject(str);
		JSONArray jsonarray = (JSONArray) (jsonObj.get("rows"));

		if (jsonarray.size() > 0) {
			for (int i = 0; i < jsonarray.size(); i++) {
				JSONObject row = jsonarray.getJSONObject(i); // ���� jsonarray
				// ���飬��ÿһ������ת�� json ����
				System.out.println("��" + i + "����" + row.get("productId")
						+ "��Ч����" + row.get("endTime")); // �õ� ÿ�������е�����ֵ
			}
		}
	}

	static void testJSON2() {
		String str = "{\"gdprdt027\":"
				+ "{\"amount\":5000,\"balance\":0,\"result\":10020001,\"verificationCodeSwitch\":\"0\","
				+ "\"supportWBBilling\":\"1\",\"transactionId\":\"\",\"productList\":[{\"desc\":\"\","
				+ "\"isOnline\":\"0\",\"id\":\"\",\"rentalTerm\":0,\"cycleUnit\":\"1\",\"listPrice\":0,"
				+ "\"name\":\"\",\"domain\":\"1\",\"vCoin\":0,\"teleRewardPoints\":0,\"activityResourceIds\":\"\","
				+ "\"categoryId\":\"\",\"purchaseType\":\"0\",\"iptvRewardPoints\":0,\"contentID\":\"\",\"cycleLength\":1,"
				+ "\"productId\":\"gdprdt027\",\"fee\":1000,\"productDesc\":\"��飺����ʾ糡�����º��硢�����Ⱦ磬һ׷���ס�\","
				+ "\"price\":1000,\"pictureUrl\":\"\",\"serviceId\":\"\",\"limitTimes\":0,\"payChannelList\":[\"1\"],"
				+ "\"productName\":\"��ʾ糡\"}],\"domain\":0,\"expiredTime\":\"\",\"productName\":\"��ʾ糡\",\"productId\":\"gdprdt027\"}}";
		JSONObject jsonObj = JSONObject.fromObject(str);
		jsonObj = (JSONObject) jsonObj.get("gdprdt027");
		String result = jsonObj.getString("result");
		System.out.println("result=" + result);
	}
}
