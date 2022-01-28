package com.example.ex3mohamadamer.demo.controllers;

import com.example.ex3mohamadamer.demo.repository.Github;
import com.example.ex3mohamadamer.demo.repository.GithubRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class Main {

    @Autowired
    public GithubRepository githubRepository;
    public GithubRepository getGithubRepositoryDatabase() {
        return githubRepository;
    }
    @PostMapping("/search")
    public String search(@RequestParam(name = "username", defaultValue = "missing")
                                 String username, Model model) {
        try {
            String inputLine;
            StringBuffer response = new StringBuffer();
            model.addAttribute("username", username + ": ");
            Github github = new Github(username);
            String url_str = github.getApi();
            URL url = new URL(url_str);
            BufferedReader reader = getBufferedReader(url);
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            JSONObject jsonObject = new JSONObject(response.toString());
            if(getGithubRepositoryDatabase().findByUsername(username).isEmpty()) {
                getApiData(jsonObject, model, github, username);
                model.addAttribute("username", username + ": ");
            }
            else{
              Github github1 = getGithubRepositoryDatabase().findByUsername(username).get(0);
              github1.setNumSearched(github1.getNumSearched());
              getGithubRepositoryDatabase().findByUsername(username).clear();
              getGithubRepositoryDatabase().save(github1);
              model.addAttribute("numoffollowers", github1.getFollowers() + " Followers");
              model.addAttribute("username", username + ": ");
            }
        } catch (Exception e) {
            model.addAttribute("username", "User Not Found");
            return "main";
        }
        return "main";
    }

    /**
     * Clear Database
     * @return page to load
     */
    @PostMapping("/clear")
    public String DatabaseClear() {
        try {
          getGithubRepositoryDatabase().deleteAllInBatch();
        }catch (Exception e){
            return "index";
        }
        return "main";
    }

    /**
     *
     * @param apiDataJson Json if the api from Github to this username
     * @param model model to contact with the view
     * @param github github object to fill the data of this user
     * @param username find the data about this username
     * @throws JSONException Exception handle the error of the json
     */
    private void getApiData(JSONObject apiDataJson, Model model, Github github, String username) throws JSONException {
        github.setFollowers(apiDataJson.getInt("followers"));
        model.addAttribute("numoffollowers", github.getFollowers() + " Followers");
        getGithubRepositoryDatabase().findByUsername(username);
        getGithubRepositoryDatabase().save(github);
        System.out.println(getGithubRepositoryDatabase().findAll());
    }

    /**
     *
     * @param url url to connect
     * @return buffer reader of the apu content
     * @throws IOException Exception to handle error
     */
    private BufferedReader getBufferedReader(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        return  bufferedReader;
    }
}
