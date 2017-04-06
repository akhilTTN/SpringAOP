package com.spring.AOP;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by akhil on 29/3/17.
 */
public class SpringAOPMain {
    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config-AOP.xml");
        User user = applicationContext.getBean(User.class);
//        System.out.println(user);
//        com.spring.AOP.subPackage.WithinAdvice withinAdvice=applicationContext.getBean(com.spring.AOP.subPackage.WithinAdvice.class);
        user.addUser();
        user.deleteUser();
        user.deprecatedMethod();
        user.returnsSometing();
        user.addDeprecatedMethod();
        user.throwsException();
//        withinAdvice.AdviceCheck();
        user.addUser();
        user.checkThis();
        user.checkThat();


    }
}
