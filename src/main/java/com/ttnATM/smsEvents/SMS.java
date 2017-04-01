package com.ttnATM.smsEvents;

import java.util.Date;

/**
 * Created by ishwar on 1/4/17.
 */
public class SMS {

    private int id;
    private double amountLeft;
    private double amountDebited;
    private Date date;
    private String content;
    private String name;

    public SMS() {
    }

    public SMS(double amountLeft,double amountDebited, Date date, String content, String name) {
        this.amountLeft = amountLeft;
        this.amountDebited = amountDebited;
        this.date = date;
        this.content = content;
        this.name = name;
    }

    public double getAmountLeft() {
        return amountLeft;
    }

    public SMS setAmountLeft(double amountLeft) {
        this.amountLeft = amountLeft;
        return this;
    }

    public int getId() {
        return id;
    }

    public SMS setId(int id) {
        this.id = id;
        return this;
    }

    public double getAmountDebited() {
        return amountDebited;
    }

    public SMS setAmountDebited(double amountDebited) {
        this.amountDebited = amountDebited;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public SMS setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SMS setContent(String content) {
        this.content = content;
        return this;
    }

    public String getName() {
        return name;
    }

    public SMS setName(String name) {
        this.name = name;
        return this;
    }
}
