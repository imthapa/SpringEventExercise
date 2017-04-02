package com.ttnATM.otpEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * Created by ishwar on 2/4/17.
 */
public class OTPPublisher implements ApplicationEventPublisherAware{

    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("inside OTP publisher");
        this.applicationEventPublisher = applicationEventPublisher;
    }

  /*  public void  publish(OTPEvent event){
        applicationEventPublisher.publishEvent(event);
    }*/
}
