package com.ttnATM;

import com.ttnATM.otpEvent.OTPEvent;
import com.ttnATM.otpEvent.OTPPublisher;
import com.ttnATM.smsEvents.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
//@Component
public class AtmDaoImpl implements AtmDao {

    private static Logger logger = Logger.getLogger(AtmDaoImpl.class);

    @Autowired
    SMSPublisher smsPublisher;
    @Autowired
    OTPPublisher otpPublisher;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        logger.debug("hello from jdbcTemplate");
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        //jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void withdrawl(int withdrawlAmount, int id) {
//        String selectSql = "select * from ATM where id = :id";
//        Map map = new HashMap();
//        map.put("id", id);
//        //  ATM atm = jdbcTemplate.queryForObject(selectSql,map);
//        ATM atm = jdbcTemplate.queryForObject(selectSql, map, new RowMapper<ATM>() {     //new Object[]{id}
//            @Override
//            public ATM mapRow(ResultSet rs, int rowNum) throws SQLException {
//                ATM atm1 = new ATM();
//                atm1.setId(rs.getInt("id"));
//                atm1.setBalance(rs.getDouble("balance"));
//                atm1.setMobileNumber(rs.getString("mobileNumber"));
//                atm1.setName(rs.getString("name"));
//                return atm1;
//            }
//        });
        ATM atm = findATM(id);
        logger.debug(atm);
        if (atm.getBalance() > 0 && atm.getBalance() > withdrawlAmount) {
            String updateSql = "update ATM set balance = balance - :withdrawlAmount where id = :id";
            Map map2 = new HashMap();
            map2.put("withdrawlAmount", withdrawlAmount);
            map2.put("id", id);
            jdbcTemplate.update(updateSql, map2);
            atm.setBalance(atm.getBalance() - withdrawlAmount);
            smsEvent(atm, withdrawlAmount, true);
        } else {
            smsEvent(atm, withdrawlAmount, false);
        }


    }

    private void smsEvent(ATM atm, int withdrawlAmount, boolean flag) {

        SMS sms = new SMS();
        sms.setAmountDebited(withdrawlAmount);
        sms.setAmountLeft(atm.getBalance());
        sms.setDate(new Date());
        sms.setId(atm.getId());
        if (flag) {
            SMSEvent smsEvent = new SMSEvent(sms);
            smsPublisher.publish(smsEvent);
        } else {
            SMSEventFailure smsEvent = new SMSEventFailure(sms);
            smsPublisher.publish(smsEvent);
        }


    }

    @Override
    //new pin would be asked by user
    public void pinChange(int id, int oldPin) {
        //finding the user whose pin is to be updated
        ATM atm = findATM(id);

        //starting event for pin change
        OTPEvent otpEvent = new OTPEvent(atm);

        //publishing the event
        otpPublisher.publish(otpEvent);

    }


    private ATM findATM(int id){
        String selectSql = "select * from ATM where id = :id";
        Map map = new HashMap();
        map.put("id", id);
        ATM atm = jdbcTemplate.queryForObject(selectSql, map, new RowMapper<ATM>() {     //new Object[]{id}
            @Override
            public ATM mapRow(ResultSet rs, int rowNum) throws SQLException {
                ATM atm1 = new ATM();
                atm1.setId(rs.getInt("id"));
                atm1.setBalance(rs.getDouble("balance"));
                atm1.setMobileNumber(rs.getString("mobileNumber"));
                atm1.setName(rs.getString("name"));
                return atm1;
            }
        });
        return atm;
    }

    public void updatePin(ATM atm){
        Scanner scanner = new Scanner(System.in);
        System.out.println("otp matched enter the new pin.(must be of 4 digit integer)");
        int newPin = scanner.nextInt();
        String updateSql = "update ATM set pin = :newPin where id = :id and pin = :oldPin";
        Map map2 = new HashMap();
        map2.put("newPin", newPin);
        map2.put("id", atm.getId());
        map2.put("oldPin",atm.getPin());
        jdbcTemplate.update(updateSql, map2);
        logger.info("PIN successfully changed");
    }

    @Override
    public void changeMobileNumber(int id, String mobileNumber) {
        ATM atm = findATM(id);
       // int newPin = scanner.nextInt();
        String updateSql = "update ATM set mobileNumber = :mobileNumber where id = :id";
        Map map2 = new HashMap();
        map2.put("mobileNumber", mobileNumber);
        map2.put("id", id);
        jdbcTemplate.update(updateSql, map2);

        SMSEvent smsEvent = new SMSEvent(atm);
        smsPublisher.publish(smsEvent);
    }

}
