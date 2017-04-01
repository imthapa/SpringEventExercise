package com.ttnATM.otpEvent;

import com.ttnATM.ATM;
import com.ttnATM.AtmDao;
import com.ttnATM.AtmDaoImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;

import java.util.Scanner;

/**
 * Created by ishwar on 2/4/17.
 */
public class OTPListener implements ApplicationListener<OTPEvent> {

    @Autowired
    AtmDaoImpl atmDao;

    private static Logger logger = Logger.getLogger(OTPListener.class);
    long userInputOTP;

    @Override
    public void onApplicationEvent(OTPEvent event) {
        if (event.getSource() instanceof ATM) {
            // OTP otp = (OTP)event.getSource();
            ATM atm = (ATM) event.getSource();
            long randomNum = (long) (Math.random() * 100000);
            logger.info("your OTP is " + randomNum);
            System.out.println("please enter the otp received ");
//             otp.setOptNumber(randomNum);
//            Scanner scanner = new Scanner(System.in);
//            long otp1 = scanner.nextLong();
//            if (otp1 == randomNum){

//            }
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("your OTP is " + randomNum);
//                }
//            });

//            Scanner scanner = new Scanner(System.in);
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
//                    userInputOTP = scanner.nextLong();
                    userInputOTP = otpGetter();
                }
            });
            thread1.start();
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//                thread.start();
            if (randomNum == userInputOTP) {
                atmDao.updatePin(atm);
            } else {
                logger.info("OOPS wrong otp!!!");
            }
        }
    }

    private long otpGetter() {
        return new Scanner(System.in).nextLong();
    }
}
