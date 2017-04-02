package com.ttnATM.otpEvent;

/**
 * Created by ishwar on 2/4/17.
 */
public class OTP {

    private int id;
    private long optNumber;

    public OTP(){

    }

    public OTP(int id,long optNumber) {
        this.id = id;
        this.optNumber = optNumber;
    }

    public int getId() {
        return id;
    }

    public OTP setId(int id) {
        this.id = id;
        return this;
    }

    public long getOptNumber() {
        return optNumber;
    }

    public OTP setOptNumber(long optNumber) {
        this.optNumber = optNumber;
        return this;
    }
}
