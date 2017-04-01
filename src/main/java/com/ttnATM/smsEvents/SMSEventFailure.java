package com.ttnATM.smsEvents;

import org.springframework.context.ApplicationEvent;

/**
 * Created by ishwar on 1/4/17.
 */
public class SMSEventFailure extends ApplicationEvent{

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public SMSEventFailure(Object source) {
        super(source);
        System.out.println("inside SMSEventFailure constructor");
    }
}
