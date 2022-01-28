package com.example.ex3mohamadamer.demo.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *  User Validation Component
 */
@Component
public class UserValidation implements Serializable {

    private boolean isOnline;// user Status
    //Ctor
    public UserValidation(){
        this.isOnline = false;
    }

    /**
     *
     * @return string of user status
     */
    @Override
    public String toString() {
        return "Message{" +
                "isOnline=" + isOnline +
                '}';
    }
    //Getter

    /**
     *
     * @return user status
     */
    public boolean isOnline() {
        return isOnline;
    }
    //Setter

    /**
     *
     * @param online set true or false
     */
    public void setOnline(boolean online) {
        isOnline = online;
    }
}
