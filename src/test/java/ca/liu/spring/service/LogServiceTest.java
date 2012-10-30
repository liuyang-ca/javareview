package ca.liu.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.liu.domain.Log;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context.xml")
public class LogServiceTest {
	@Inject private LogService logService;

	/**
	 * For some reason this test case does't work
	 */
	@Test
	public void listTest() {
		List<Log> list = logService.list();
		for(Object obj : list) {
			System.out.println(obj);
		}
	}
}
