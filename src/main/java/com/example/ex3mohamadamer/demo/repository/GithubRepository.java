package com.example.ex3mohamadamer.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for the github data to return from the database
 * Top 10 users searched
 * Function to find user by the name from the database
 */
public interface GithubRepository extends JpaRepository<Github, Long> {
    List<Github> findFirst10ByOrderByNumSearchedDesc();
    List<Github> findByUsername(String username);

}
