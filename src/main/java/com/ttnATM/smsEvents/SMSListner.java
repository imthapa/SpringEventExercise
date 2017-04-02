package com.ttnATM.smsEvents;

import com.ttnATM.ATM;
import com.ttnATM.AtmDaoImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

import java.util.Scanner;

/**
 * Created by ishwar on 1/4/17.
 */
public class SMSListner implements ApplicationListener<ApplicationEvent> {
    static int counter = 0;
    @Autowired
    AtmDaoImpl atmDao;

    long userInputOTP;

    private static Logger logger = Logger.getLogger(SMSListner.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.debug("onApplicationEvent Listener");
        if (event.getSource() instanceof SMS) {
            logger.debug("counter from smsdebit: " + ++counter);
            SMS sms = (SMS) event.getSource();
            if (event instanceof SMSEvent) {
                successfulTransaction(sms);
            } else if (event instanceof SMSEventFailure) {
                failureTransaction(sms);
            }
        } else if (event.getSource() instanceof ATM) {
            logger.debug("counter from mobileNumber: " + ++counter);
            logger.info("mobile Number successfully updated.");
        } else if (event.getSource() instanceof OTP) {
            otpTransaction((OTP)event.getSource());
        } else {
            logger.debug("counter from other : " + ++counter);
            logger.info(event.getClass());
            logger.info("event is not of type SMS or ATM!!!!");
        }
    }

    private long otpGetter() {
        return new Scanner(System.in).nextLong();
    }

    @Async
    private void successfulTransaction(SMS sms){
        logger.info("Execute method asynchronously. " + Thread.currentThread().getName());
        try {
            logger.info("successfull transaction and sleeping for 3secs");
            Thread.sleep(3000);
            logger.info("sleep over");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Dear Customer, You have made a Debit card transaction of INR " + sms.getAmountDebited() +
                " on " + sms.getDate() + "\n, the net available balance in your Ac XXXXXXXXXX" + sms.getId() + " is INR " +
                sms.getAmountLeft());
    }

    @Async
    private void failureTransaction(SMS sms) {

        logger.info("Dear Customer, You tried to make a Debit card transaction of INR " + sms.getAmountDebited() +
                " on " + sms.getDate() + "\n, the net available balance in your Ac XXXXXXXXXX" + sms.getId() + " is INR " +
                sms.getAmountLeft() + "." +
                " Please try with amount less than balance left in account");
    }

    private void otpTransaction(OTP otp){
        logger.debug("counter from otp : " + ++counter);
        long randomNum = (long) (Math.random() * 100000);
        logger.info("your OTP is " + randomNum + " for pin change request.");
        System.out.println("please enter the otp received ");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                userInputOTP = otpGetter();
            }
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //otp verified
        if (randomNum == userInputOTP) {
            //atmDao.updatePin(atm);
            //atmDao.updatePin(((OTP) event.getSource()).getId());
            atmDao.updatePin(otp.getId());
        } else {
            logger.info("OOPS wrong otp!!!");
        }
    }
}
