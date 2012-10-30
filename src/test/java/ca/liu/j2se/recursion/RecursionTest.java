package ca.liu.j2se.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class RecursionTest {
	
	private String[] stringPermutation(String str) {
		if(str == null || str.isEmpty()) {
			return null;
		} else if(str.length() == 2) {
			char[] tmpArray = str.toCharArray();
			StringBuilder sf = new StringBuilder();
			return new String[]{str, sf.append(tmpArray[1]).append(tmpArray[0]).toString()};
		} else {
			int length = str.length();
			List<String> list = new ArrayList<String>();
			for(int i=1; i<=length; i++) {
				String tmpStr = str.substring(0, i-1) + str.substring(i, length);
				String keyStr = new Character(str.charAt(i-1)).toString();
				String[] strArray = stringPermutation(tmpStr);
				if(strArray != null && strArray.length > 0) {
					for(String s : strArray) {
						list.add(keyStr + s);
					}
				}
			}
			
			return list.toArray(new String[list.size()]);
		}
	}
	
	@Test
	public void stringPermutationTest() {		
		String[] strArray = stringPermutation("aba");
		System.out.println("size = " + strArray.length);
		System.out.println(Arrays.toString(strArray));
	}
}
