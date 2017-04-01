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
public interface AtmDao {

    void withdrawl(int withdrawlAmount,int id);
    void pinChange(int id,int oldPin);
    void changeMobileNumber(int id,String mobileNumber);

}
