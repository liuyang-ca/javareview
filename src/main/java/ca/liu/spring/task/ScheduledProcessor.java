package ca.liu.spring.task;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ca.liu.spring.annotation.InjectLogger;

@Service
public class ScheduledProcessor {
    private final AtomicInteger counter = new AtomicInteger();
    @InjectLogger private Logger logger;
    
    @Inject @Named("syncWorker")
    private Worker syncWorker;
    
    @Inject @Named("asyncWorker")
    private Worker asyncWorker;
 
    @Scheduled(fixedRate=10000)//(cron="0/10 * * * * *")//(fixedDelay = 10000)
    public void processAsync() {
        logger.info("--->processing Async next 5 at " + new Date());
        for (int i = 0; i < 5; i++) {
        	asyncWorker.work(counter.incrementAndGet());
        }
    }
    
    @Scheduled(fixedDelay = 20000)
    public void processSync() {
        logger.info("===>processing Sync next 5 at " + new Date());
        for (int i = 0; i < 5; i++) {
        	syncWorker.work(counter.incrementAndGet());
        }
    }
}
