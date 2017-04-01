package com;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Created by ishwar on 31/3/17.
 */

public class MyAsync {

    private static Logger logger = Logger.getLogger(MyAsync.class);
  //  MyAsync(){}

    @Async
    public Future<String> doAsyncTask(){
        logger.info("Execute method asynchronously. " + Thread.currentThread().getName());
        try {
            logger.info("sleeping for 3 seconds");
            Thread.sleep(3000);
            logger.info("sleep over of async");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("");
    }
    @Async
    public void doSyncTask(){
        logger.info("Execute method synchronously. " + Thread.currentThread().getName());
        try {
            logger.info("sleeping for 5 seconds");
            Thread.sleep(5000);
            logger.info("sleep over of sync");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
