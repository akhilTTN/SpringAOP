package com.spring.transaction;

import org.springframework.stereotype.Service;

/**
 * Created by akhil on 29/3/17.
 */
@Service
public class UserAccount {
    String name;
    String acount_no;
    double balance;
    String mobile_no;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcount_no() {
        return acount_no;
    }

    public void setAcount_no(String acount_no) {
        this.acount_no = acount_no;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", acount_no='" + acount_no + '\'' +
                ", balance=" + balance +
                ", mobile_no=" + mobile_no +
                '}';
    }
}
