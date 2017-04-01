package com.ttnATM;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ishwar on 1/4/17.
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        AtmDaoImpl atmDao = (AtmDaoImpl) context.getBean("AtmDaoImpl");
       // atmDao.withdrawl(5000,2);
       // atmDao.pinChange(2,0);
       // atmDao.changeMobileNumber(1,"8787788787");
    }
}
