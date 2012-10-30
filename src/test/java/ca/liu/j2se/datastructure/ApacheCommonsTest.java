package ca.liu.j2se.datastructure;

import static org.junit.Assert.assertEquals;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.junit.Test;

public class ApacheCommonsTest {
	/**
	 * Bi-directional map only allow one key/value pair
	 */
	@Test
	public void collectionTest() {
		BidiMap map = new TreeBidiMap();
		map.put("one", 1);
		map.put("two", 1);
		
		assertEquals(map.size(), 1);
	}
}
