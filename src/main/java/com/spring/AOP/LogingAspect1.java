package com.spring.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by akhil on 29/3/17.
 */
@Aspect
@Component
public class LogingAspect1 {

    @Before("execution(public * *User(..))")
    public void logBefore(){
        System.out.println("logging before");
    }

    final String s="execution(public * return*(..))";
    @Pointcut("execution(public * add*())")
    void logPointcut(){

    }
    @After("abc()")
    void logingSubPackages(){
        System.out.println("Fetching methods of sub package");
    }

    @After("@annotation(Deprecated) && logPointcut()")
    public void logAnnotationMethod(){
        System.out.println("logging annotated methods that begins with add");
    }

    @AfterReturning(value = s, returning = "ret")
    public void logReturningMethod(int ret){
        System.out.println("This method returns "+ret);
    }


    @AfterThrowing(value = "execution(public * thro*(..))", throwing = "th")
    public void logThrowingMethod(JoinPoint joinPoint,Throwable th){
String tt=th.toString();
System.out.println("!!!!!!!!!!EXCEPTION!!!!!!!!!!");
        System.out.println(th.getClass());
        System.out.println("Exception occured in "+joinPoint.getSignature()+" method");
        System.out.println(joinPoint.getClass());
        if(th.getClass().isInstance(new IOException())){
            System.out.println("IOException Occured");
        }
        else
            System.out.println("Other Exception");
        if(tt.equals("java.io.IOException")){
            System.out.println("IOException Occured");
        }
        else
            System.out.println("Other Exception");
        System.out.println(joinPoint.getArgs());
        System.out.println(joinPoint.getKind());
        System.out.println(joinPoint.getTarget());
        th.printStackTrace();
    }
    @Pointcut("within(com.spring.AOP..*)")
    void abc(){

    }

    @After("execution(public void check*(..)) && this(com.spring.AOP.Temp)")
    void logTemp(){
        System.out.println("logging Temp interface");
    }
}
