package com;

/**
 * Created by ishwar on 31/3/17.
 */

/*1. create class to store data of Employee(id, name,mailId, salary).
     Add multiple employee at run time and generate alert if employee salary is greater than 30000
     using Asyn event.

        */

public class Employee {

    private int id;
    private String name;
    private String mailId;
    private int salary;

    public int getId() {
        return id;
    }

    public Employee setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getMailId() {
        return mailId;
    }

    public Employee setMailId(String mailId) {
        this.mailId = mailId;
        return this;
    }

    public int getSalary() {
        return salary;
    }

    public Employee setSalary(int salary) {
        this.salary = salary;
        return this;
    }
}
