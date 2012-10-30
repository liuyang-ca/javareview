package ca.liu.spring.task;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScheduledProcessorTest {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("task.xml", ScheduledProcessorTest.class);
	}
}
