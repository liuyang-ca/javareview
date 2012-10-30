package ca.liu.groovy;

import java.io.File;

import org.junit.Test;

public class HelloJavaTest {
	private String path = "src/main/resources";
	
	public void listFiles(File file) {
		System.out.println(file);
		if(file.isDirectory()) {
			for(File f : file.listFiles()) {
				listFiles(f);
			}
		}
	}
	
	/**
	 * assert won't work for Java, but works for groovy
	 */
	@Test
	public void assertTest() {
		assert(false);
	}
	
	@Test
	public void listFilesTest() {
		listFiles(new File(path));
	}
	
	@Test
	public void sandbox() {
		//new StringBuffer().r
	}
}
