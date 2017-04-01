package com;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by ishwar on 31/3/17.
 */
public class Application {

    private static Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        EmployeeDao employeeDao = (EmployeeDaoImpl)context.getBean("employeeDao");
        MyAsync myAsync =  (MyAsync) context.getBean("MyAsync");

        String name;
        String mailId;
        int salary;

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter 'e' to enter data in DB or 'q' to quit");
        if(scanner.nextLine().charAt(0) == 'e'){
            String temp = "";
            do{
                System.out.println("Enter details for to be entered in DB:" );
                System.out.println("enter your name");
                name = scanner.nextLine();
                System.out.println("enter your mailId");
                mailId = scanner.nextLine();
                System.out.println("enter your salary");
                salary = scanner.nextInt();
                scanner.nextLine();
                if(salary > 30000){
                    Future<String> future = myAsync.doAsyncTask();
              //      try {
                    try {
                        logger.info(future.get(0,TimeUnit.SECONDS));
                    } catch (InterruptedException e) {
                       // e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }

                    System.out.println("afer Async");
                  ///  myAsync.doSyncTask();
                }
                Employee employee = new Employee().setName(name)
                        .setMailId(mailId)
                        .setSalary(salary);
                employeeDao.insert(employee);
                System.out.println("enter 'e' to enter another data in DB or 'q' to quit");
                temp = scanner.nextLine();
                System.out.println(temp);
            }while (temp.charAt(0) != 'q');
        }else{
            scanner.close();
            System.out.println("closing the session");
        }


    }
}
