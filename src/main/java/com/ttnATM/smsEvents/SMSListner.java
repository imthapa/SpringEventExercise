package com.ttnATM.smsEvents;

import com.ttnATM.ATM;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by ishwar on 1/4/17.
 */
public class SMSListner implements ApplicationListener<ApplicationEvent> {

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
            logger.info("mobile Number successfully changed.");
        } else {
            logger.info(event.getClass());
            logger.info("event is not of type SMS!!!!");
        }
    }
}
