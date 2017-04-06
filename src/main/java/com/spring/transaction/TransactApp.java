package com.spring.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.applet.AppletContext;

/**
 * Created by akhil on 29/3/17.
 */
public class TransactApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("spring-config.xml");
        AccountService accountService=applicationContext.getBean(AccountService.class);
        accountService.transferAmountTransaction("akhil",1000,"123456789","prashant","987654321");
        accountService.transferAmount("akhil",1000,"123456789","prashant","987654321");
        accountService.transferAmountTransactionUsingAnnotations("akhil",1000,"123456789","prashant","987654321");
        accountService.transferAmountTransactionUsingAnnotationsWithReadOnly("akhil",1000,"123456789","prashant","987654321");
        accountService.showDetails("123456789");
        accountService.addUser(100000,"gunjan","123123123","951951951");
        accountService.deleteUser("123123123");
        accountService.addUser(100000,"gunjan","123123123","951951951");
        accountService.getAll();
        accountService.runPropogationInRequiredNewMode();
    }
}
