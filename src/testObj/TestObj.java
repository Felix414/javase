package testObj;

import java.util.ArrayList;
import java.util.List;

public class TestObj {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object obj1 = new String("123");
		Object obj2 = new String("456");
		Object obj3 = new String("789");
		Object obj4 = new String("0");
		
		obj1 = obj3;		
		obj3 = obj4;
		
		List<Object> list = new ArrayList<Object>();
		
		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);
		System.out.println(list);
	}

}
