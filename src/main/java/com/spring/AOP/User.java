package com.spring.AOP;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by akhil on 29/3/17.
 */
@Component
public class User implements Temp {







    public void addUser(){
        System.out.println("Adding user");
    }

    public void deleteUser(){
        System.out.println("Deleting user");
    }
    @Deprecated
    public void deprecatedMethod(){
        System.out.println("This mehtod is deprecated");
    }

    @Deprecated
    public void addDeprecatedMethod(){
        System.out.println("Add method that is depricated");
    }

    public int returnsSometing(){
        return 1;
    }

    public void throwsException()throws IOException{
        throw new IOException();
    }

    public void checkThat(){
        System.out.println("method of class user");
    }

    @Override
    public void checkThis() {
        System.out.println("Method of Interface Temp");
    }
}
