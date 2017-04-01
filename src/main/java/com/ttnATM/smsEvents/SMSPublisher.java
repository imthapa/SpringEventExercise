package com.ttnATM.smsEvents;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * Created by ishwar on 1/4/17.
 */

public class SMSPublisher implements ApplicationEventPublisherAware {

    private static Logger logger = Logger.getLogger(SMSPublisher.class);

    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        logger.debug("inside publisher setter");
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(ApplicationEvent smsEvent){
        logger.debug("inside publish method");
       // SMSEvent smsEvent = new SMSEvent(this);
        applicationEventPublisher.publishEvent(smsEvent);

    }
}
