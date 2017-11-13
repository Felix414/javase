package testRandom;

import java.util.Random;

public class TestRandom {
	
	public static void main(String[] args) {
//		for(int i=0;i<10;i++)
//			method2();
		
		ramdomColor();
	}
	
	static void method() {
		String products = "3190 3191 3192";
		String p = "";
		if(products.contains("3190"))
			p += "3190 ";
		if(products.contains("3191"))
			p += "3191 ";
		if(products.contains("3192"))
			p += "3192 ";
		p = p.trim();
		String[] arr = p.split(" ");
		Random r = new Random();
		System.out.println(arr[r.nextInt(arr.length)]);
	}
	
	static void method1() {
		for(int i=0;i<10000;i++){
			Random r = new Random();
			System.out.print(r.nextInt(10)+" ");//Éú³É0-9µÄËæ»úÊý
		}
	}
	
	/**
	 * Ë«É«Çò
	 */
	static void method2() {
		Random r = new Random();
		Integer[] red = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,
				22,23,24,25,26,27,28,29,30,31,32,33};
		Integer[] blue = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		String result = "ºìÇòºÅÂë:";
		String indexFlag = "";
		for(int i=0;i<6;i++){
			int index = r.nextInt(red.length);
			if(!indexFlag.contains(index+",")){
				indexFlag += index+",";
				result += red[index] + " ";
			}else{
				i--;
			}
		}
		result += "À¶ÇòºÅÂë:"+blue[r.nextInt(blue.length)];
		System.out.println(result);
	}
	
	static void ramdomColor() {
		String a[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		int l = a.length;
		Random r = new Random();
		for(int i=0;i<30;i++){
			System.out.println(a[r.nextInt(l)]+a[r.nextInt(l)]+a[r.nextInt(l)]+a[r.nextInt(l)]+a[r.nextInt(l)]+a[r.nextInt(l)]);
		}
		
	}
}
