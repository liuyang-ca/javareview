package ca.liu.groovy
import org.junit.Test

class BookTest {
	@Test
	public void getBookTest() {
		Book book = new Book()
		book.title = "Groovy In Action"
		println book.title
	}
}
