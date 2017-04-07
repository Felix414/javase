package testString;

import java.util.Arrays;
import java.util.Random;

public class TestString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		testSplit();
//		testSubString();
//		testindexOf();
//		testScore(0,31);
//		testreplayspan();
//		testsplit();
//		testRandom();
//		testcount();
		testSplitUrl();
	}

	static void testindexOf(){
		String str = "http://183.59.152.135:8082/EPG/jsp/defaultsdcctv/en/page/http://183.59.152.135:8082/EPG/jsp/defaultsdcctv/en/page/index.jsp";
		String s = "http://183.59.152.135:8082/EPG/jsp/defaultsdcctv/en/page/";
		System.out.println(str.indexOf(s));
		System.out.println(str.lastIndexOf(s));
		System.out.println(str.substring(str.lastIndexOf(s)));
	}
	
	static void testSplit(){
		String str = "one=1&two=2&three=3";
		System.out.println(str.split("&two=")[1]);
		System.out.println(str.split("&two=")[1].split("&")[0]);
	}
	
	static void testSubString(){
		String str = "20150508";
		System.out.println(str.substring(0, 4));
		System.out.println(str.substring(4, 6));
		System.out.println(str.substring(6, 8));
	}
	
	static void testScore(int correctCount, int time ){
		int score =0;
		if(correctCount==0){
			score = 0;
		}else if(correctCount==1){
			score = 1;
		}else if(correctCount==3&&time>=30){
			score = 3;
		}else if(correctCount>=2&&time>=20){
			score = 2;
		}else{
			score = 1;
		}
		System.out.println(score);
	}
	
	static void testreplayspan(){
		String str = "aa,,,a,,b,bb";
        str = str.replaceAll("\\,+", ",");
        System.out.println(str);
	}
	
	static void testsplit(){
		String[] array = "3477|3308|3432".split("\\|"); 
		System.out.println(array.length);
		System.out.println(array[new Random().nextInt(array.length)]);
	}
	
	static void testRandom(){
		Random r = new Random();
		int[] index = new int[3];
		boolean same = false;
		
		
		index[0] = r.nextInt(5)+1;
		index[1] = r.nextInt(5)+1;
		if(index[0] == index[1])
			same = true;
		if(!same){
			index[2] = r.nextInt(5)+1;
		}else{
			while(same){
				int num = r.nextInt(5)+1;
				if(index[0] != num){
					same = false;
					index[2] = num;
				}
			}
		}
		System.out.println(Arrays.toString(index));
	}
	
	static void testcount(){
		int d = 325;
		int sum = 0;
		while(d>0){
			int n = d%10;
			sum += n;
			d /=10;
		}
		System.out.println(sum);
	}
	
	static void testSplitUrl(){
		String str = "backurl=http://183.59.157.8:8082/EPG/jsp/defaultsmchd/en/externalLinkJumpAgent.jsp?type=1";
		System.out.println(str.substring((str.indexOf("=", 1) +1), str.length()));
	}
	

}
