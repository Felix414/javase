package algorithm;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int total = 287;
		int time = 15;
		int ever = 287/time;
		for(int i=time;i>0;i--){
			total = total - ever;
			System.out.println(total);
			
			System.out.println(ever*i);
		}

	}
	
	public String substr(){
		System.out.println("123");
		return null;
	}

}
