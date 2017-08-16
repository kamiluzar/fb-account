package com.kzr.controller;

import com.kzr.model.Account;
import com.kzr.service.FacebookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by Kamil on 2017-08-14.
 */
@RestController
public class FacebookController {

    private final FacebookService facebookService;
    private final Account account;

    public FacebookController(FacebookService facebookService, Account account) {
        this.facebookService = facebookService;
        this.account = account;
    }

    @RequestMapping("/fb/{id}")
    public Account findById(@PathVariable String id) throws IOException {
        Account account = facebookService.findById(id);
        return account;
    }

    @RequestMapping("/count")
    public Map<String, Long> findMostCommonWords() throws IOException {
        return facebookService.findMostCommonWords();
    }

    @RequestMapping("/where/{word}")
    public Set<String> findPostIdsByKeyword(@PathVariable String word) throws IOException {
        return facebookService.findPostIdsByKeyword(word);
    }

    @RequestMapping("/list")
    public Set<Account> findAll() throws IOException {
        return facebookService.findAll();
    }



}







