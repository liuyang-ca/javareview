package ca.liu.j2se.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class StringTest {
	private String removeSpecifedChars(String str, String remove) {
		boolean [] ascii = new boolean [128];
		for(char c : remove.toCharArray()) {
			ascii[c] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		for(char c : str.toCharArray()) {
			if(!ascii[c]) {
				sb.append(c);
			}
		}
		
		return sb.toString();
	}
	
	@Test
	public void removeSpecifedCharsTest() {
		System.out.println(removeSpecifedChars("my name is liu", "mi"));
	}
	
	private String reverseWords1(String str) {
		String[] strArray = str.split(" ");
		List<String> list = Arrays.asList(strArray);
		Collections.reverse(list);
		StringBuilder sb = new StringBuilder();
		for(String s : list) {
			sb.append(s).append(" ");
		}
		return sb.toString().substring(0, sb.length()-1);
	}
	
	private String reverseWords2(String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.reverse();
		StringBuilder sbb = new StringBuilder();
		for(String s : sb.toString().split(" ")) {
			sbb.append(new StringBuilder(s).reverse().toString()).append(" ");
		}
		return sbb.toString().substring(0, sbb.length()-1);
	}
	
	@Test
	public void reverseWordsTest() {
		System.out.println(reverseWords1("my name is liu"));
		System.out.println(reverseWords2("my name is liu"));
	}
}
