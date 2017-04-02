package com.ttnATM;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by ishwar on 1/4/17.
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        AtmDaoImpl atmDao = (AtmDaoImpl) context.getBean("AtmDaoImpl");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        char temp;

        //input = scanner.nextInt();
        int id;
        try {
            outer:
            do {
                System.out.println("choose from following options.");
                System.out.println("1 : withdrawl");
                System.out.println("2 : to change atm pin");
                System.out.println("3 : to change registered mobile number.");
                System.out.println("4 : quit");
                input = Integer.parseInt(reader.readLine());
                switch (input) {
                    case 1:
                        System.out.println("enter the amount to be withdrawn : ");
                        //int withdrawlAmt = scanner.nextInt();
                        int withdrawlAmt = Integer.parseInt(reader.readLine());
                        System.out.println("enter your id : ");
                        //id = scanner.nextInt();
                        id = Integer.parseInt(reader.readLine());
                        atmDao.withdrawl(withdrawlAmt, id);
                        break;
                    case 2:
                        System.out.println("enter your id : ");
                        //id = scanner.nextInt();
                        id = Integer.parseInt(reader.readLine());
                        System.out.println("enter your current pin : ");
                        //int pin = scanner.nextInt();
                        int pin = Integer.parseInt(reader.readLine());
                        atmDao.pinChange(id, pin);
                        break;
                    case 3:
                        System.out.println("enter your id : ");
                        //id = scanner.nextInt();
                        id = Integer.parseInt(reader.readLine());
                        System.out.println("enter the new mobile number : ");
                        //String mobileNumber = scanner.next();
                        String mobileNumber = reader.readLine();
                        atmDao.changeMobileNumber(id, mobileNumber);
                        break;
                    case 4:
                        // System.out.println("Thank you for your time.");
                        break outer;
                }
                System.out.println("do you want to continue: press y for yes and n for no :");
                //temp = scanner.next().charAt(0);
                temp = reader.readLine().charAt(0);
            } while (temp != 'n');
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Thank you for your time.");
        // atmDao.withdrawl(5000,2);
        // atmDao.pinChange(2,0);
        // atmDao.changeMobileNumber(1,"8787788787");
    }
}
