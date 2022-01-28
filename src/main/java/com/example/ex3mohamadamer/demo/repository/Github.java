package com.example.ex3mohamadamer.demo.repository;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Github {
    /**
     * Object Variables
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "its mandatory")
    private String username;
    @NotBlank(message = "its mandatory")
    private String api;
    @NotBlank(message = "its mandatory")
    private String url;
    private int numSearched;
    private int followers;
    /**
     * Getters
     * @return function to return the data for this object
     */
    public String getUserName() {
        return username;
    }
    public String getApi() {
        return api;
    }
    public String getUrl() {
        return url;
    }
    public int getFollowers() {
        return followers;
    }
    public int getNumSearched() {
        return numSearched;
    }
    /**
     * Setters
     * @param followers set the number of followers for this username
     */
    public void setFollowers(int followers) {
        this.followers = followers;
    }
    public void setNumSearched(int num) {
        this.numSearched = num+1;
    }

    /**
     * Function to return the data of the object in one string
     * @return string of the data about the object
     */
    @Override
    public String toString() {
        return "Github{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", followers=" + followers +
                ", api='" + api + '\'' +
                ", url='" + url + '\'' +
                ", numSearched=" + numSearched +
                '}';
    }

    /**
     * Default Ctor
     */
    public Github() {
    }

    /**
     * Main Ctor
     * @param name username from the input
     */
    public Github(String name) {
        this.numSearched = 1;
        this.api = "https://api.github.com/users/" + name;
        this.url = "https://github.com/" + name;
        this.username = name;
        this.followers = 0;
    }

}
