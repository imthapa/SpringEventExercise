package com.ttnATM;

/**
 * Created by ishwar on 1/4/17.
 */
/*
    2. Create a small appilcation for ATM machine that perform below functionality
        a. to withdrawal money at the same time generate sms of debited money.
        b. to change atm pin i.e generate sms --> accept pin --> store new pin in DB
        c. to change mobile number and alert of update.
        Note: Event Type(Sync and Async) should be based on the requirement.
 */
public class ATM {

    private int id;
    private String name;
    private double balance;
    private String mobileNumber;
    private int pin;

    public ATM() {
    }

    public ATM(String name, int balance, String mobileNumber,int pin) {
        this.name = name;
        this.balance = balance;
        this.mobileNumber = mobileNumber;
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public ATM setPin(int pin) {
        this.pin = pin;
        return this;
    }

    public int getId() {
        return id;
    }

    public ATM setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ATM setName(String name) {
        this.name = name;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public ATM setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public ATM setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    @Override
    public String toString() {
        return "ATM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
