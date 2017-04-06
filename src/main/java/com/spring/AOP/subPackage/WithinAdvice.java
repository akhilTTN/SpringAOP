package com.spring.AOP.subPackage;

import org.springframework.stereotype.Service;

/**
 * Created by akhil on 31/3/17.
 */
@Service
public class WithinAdvice
{
    public void AdviceCheck(){
        System.out.println("in com.spring.AOP.subPackage");
    }
}
