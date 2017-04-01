package com.ttnATM.smsEvents;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;

/**
 * Created by ishwar on 1/4/17.
 */

public class SMSEvent extends ApplicationEvent{
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */

    private static Logger logger = Logger.getLogger(SMSEvent.class);
    public SMSEvent(Object source) {
        super(source);
        logger.debug("inside SMSEvent ");
    }
}
