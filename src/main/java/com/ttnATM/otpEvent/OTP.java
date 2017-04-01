package com.ttnATM.otpEvent;

/**
 * Created by ishwar on 2/4/17.
 */
public class OTP {
    private long optNumber;

    public OTP(long optNumber) {
        this.optNumber = optNumber;
    }

    public long getOptNumber() {
        return optNumber;
    }

    public OTP setOptNumber(long optNumber) {
        this.optNumber = optNumber;
        return this;
    }
}
