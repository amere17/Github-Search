package com.example.ex3mohamadamer.demo.controllers;

import com.example.ex3mohamadamer.demo.repository.Github;
import com.example.ex3mohamadamer.demo.repository.GithubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * History controller to get the top ten user by the number of searched
 */
@Controller
public class HistoryController {
    @Autowired
    private GithubRepository githubRepository;

    /**
     *
     * @return List of Github object from the database
     */
    @GetMapping(value = "/database")
    @ResponseBody
    List<Github> getList() {
        return githubRepository.findFirst10ByOrderByNumSearchedDesc();
    }
}
