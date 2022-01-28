package com.example.ex3mohamadamer.demo.repository;


import javax.validation.constraints.NotBlank;

/**
 * User object to hold the data from the sign in inputs
 */
public class User {
    @NotBlank(message = "its mandatory")
    private String username;

    /**
     *  get the username string
     * @return username
     */
    public String getUserName() {
        return username;
    }
    /**
     *  get the password string
     * @return password
     */
    public String getPassword() {
        return password;
    }
    @NotBlank(message="its mandatory")
    private String password;

    /**
     *
     * @param password from the input
     */
    public void setPassword(String password) {
        this.password = password;
    }
    User(){}

    /**
     * Ctor
     * @param userName from the input
     * @param password from the input
     */
    public User(String userName, String password){
        this.username = userName;
        this.password=password;
    }

}
