package com.ttnATM.smsEvents;

import com.ttnATM.ATM;
import com.ttnATM.AtmDaoImpl;
import com.ttnATM.otpEvent.OTP;
import com.ttnATM.otpEvent.OTPListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Scanner;

/**
 * Created by ishwar on 1/4/17.
 */
public class SMSListner implements ApplicationListener<ApplicationEvent> {

    @Autowired
    AtmDaoImpl atmDao;

    long userInputOTP;

    private static Logger logger = Logger.getLogger(SMSListner.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.debug("onApplicationEvent Listener");
        if (event.getSource() instanceof SMS) {
            SMS sms = (SMS) event.getSource();
            if (event instanceof SMSEvent) {
                logger.info("Dear Customer, You have made a Debit card transaction of INR " + sms.getAmountDebited() +
                        " on " + sms.getDate() + "\n, the net available balance in your Ac XXXXXXXXXX" + sms.getId() + " is INR " +
                        sms.getAmountLeft());
            }
            else if (event instanceof SMSEventFailure) {
                logger.info("Dear Customer, You tried to make a Debit card transaction of INR " + sms.getAmountDebited() +
                        " on " + sms.getDate() + "\n, the net available balance in your Ac XXXXXXXXXX" + sms.getId() + " is INR " +
                        sms.getAmountLeft() + "." +
                        " Please try with amount less than balance left in account");
            }


        } else if (event.getSource() instanceof ATM) {
            logger.info("mobile Number successfully updated.");
        } if (event.getSource() instanceof OTP) {

            long randomNum = (long) (Math.random() * 100000);
            logger.info("your OTP is " + randomNum + "for pin change request.");
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
                atmDao.updatePin(((OTP) event.getSource()).getId());
            } else {
                logger.info("OOPS wrong otp!!!");
            }
        }
        else {
            logger.info(event.getClass());
            logger.info("event is not of type SMS or ATM!!!!");
        }
    }

    private long otpGetter() {
        return new Scanner(System.in).nextLong();
    }
}
