package com.ttnATM.otpEvent;

import org.springframework.context.ApplicationEvent;

/**
 * Created by ishwar on 2/4/17.
 */
public class OTPEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public OTPEvent(Object source) {
        super(source);
    }
}
