package ca.liu.spring.task;

import javax.inject.Named;

import org.slf4j.Logger;

import ca.liu.spring.annotation.InjectLogger;

@Named
public class SyncWorker implements Worker {
	@InjectLogger Logger logger;
	
	public void work(int i) {
        String threadName = Thread.currentThread().getName();
        logger.info("   " + threadName + " beginning work on " + i);
        try {
            Thread.sleep(5000); // simulates work
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("   " + threadName + " completed work on " + i);
	}
}
