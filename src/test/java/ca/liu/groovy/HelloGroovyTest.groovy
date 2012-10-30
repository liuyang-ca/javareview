package ca.liu.groovy;

import java.io.File;

import org.junit.Test

class HelloGroovyTest {

	private String path = "src/main/resources"
	
	@Test
	void safeNavigationTest() {
		Book book
		println book?.title
	}
	
	@Test
	void numberTest() {
		println 2**3
	}
	
	@Test
	void systemPropertyTest() {
		println System.getProperty('os.version')
	}
	
	@Test
	void closureTest() {
		[1, 5, 3].each {entry -> println entry}
		println '-'.center(20, '-')
		[1, 2, 3].each {println it}
	}
	
	@Test
	void asListTest() {
		List list = ('A'..'Z') + (0..9) as List
		println list
		println list.getClass()
	}
	
	@Test
	void rangeTest() {
		def range = 1..10
		assert range.contains(5)
		assert range.contains(15) == false
		assert range.size()    == 10
		assert range.from      == 1
		assert range.to        == 10
		assert range.reverse() == 10..1
		println range.getClass()
	}
	
	@Test
	void mapTest() {
		def http = [
			100 : 'CONTINUE',
			200 : 'OK',
			400 : 'BAD REQUEST',
		]
		
		assert http[200] == 'OK'
		http['A'] = 'LETTER'
		http['B'] = 123
		
		println http
		println http.get('C', 0)	//put a 0 if get('C') is null
		println http.getClass()
		
		println('-'.center(20, '-'))
		http.each{k,v -> println k + '->' + v}
		
		println('-'.center(20, '-'))
		println http.values().join(',')
	}
	
	@Test
	void listTest() {
		def list = ['A', 'B', 'C', 'D']
		assert list[1] == 'B'
		list[7] = 'X'
		list.add(12)
		assert list == ['A', 'B', 'C', 'D', null, null, null, 'X', 12]
		println list.getClass()
	}
	
	@Test
	void assertionTest() {
		assert (123 == 123)
	}
	
	@Test
	void numbersAreObjectTest() {
		def x = 1
		def y = 2
		assert x + y == 3
		assert x.plus(y) == 3
		assert x.plus(y).equals(3)
		assert x instanceof Integer
	}
	
	@Test
	void gStringTest() {
		def name = 'Leo'
		def title = 'Groovy in Action'
		def gstr = "$name is studying $title"
		println gstr
		println gstr.getClass()
	}
	
	@Test
	void regexTest() {
		assert 'Spring' =~ /Spring|Hibernate|Groovy/
		println path.replaceAll(/\\//, '\\#')
	}
	
	
	@Test
	void groovyListFilesTest() {
		new File(path).eachFileRecurse {println it}
	}
	
	@Test
	void printLineTest() {
		println('test'.center(20, '-'))
	}
	
	@Test
	void reverseStringTest() {
		println 'title'.reverse()
	}
	
	@Test
	void forLoopTest() {
		def var = ''
		10.times {var += it}
		assert var == '0123456789'
		
		var = ''
		1.upto(5) {var += it}
		assert var == '12345'
		
		var = ''
		2.downto(-2) {var += it + ' '}
		assert var == '2 1 0 -1 -2 '
		
		var = ''
		0.step(0.5, 0.1) {var += it + ' '}
		assert var == '0 0.1 0.2 0.3 0.4 '
	}
	
	@Test
	void javaListFilesTest() {
		listFiles(new File(path))
	}
	
	void listFiles(File file) {
		System.out.println(file);
		if(file.isDirectory()) {
			for(File f : file.listFiles()) {
				listFiles(f);
			}
		}
	}
}
