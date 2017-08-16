package com.kzr.controller;

import com.kzr.model.Facebook;
import com.kzr.service.FbAccountService;
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

    private final FbAccountService fbAccountService;
    private final Facebook facebook;

    public FacebookController(FbAccountService fbAccountService, Facebook facebook) {
        this.fbAccountService = fbAccountService;
        this.facebook = facebook;
    }

    @RequestMapping("/fb/{id}")
    public Facebook findById(@PathVariable String id) throws IOException {
        Facebook facebook = fbAccountService.findById(id);
        return facebook;
    }

    @RequestMapping("/count")
    public Map<String, Long> findMostCommonWords() throws IOException {
        return fbAccountService.findMostCommonWords();
    }

    @RequestMapping("/where/{word}")
    public Set<String> findPostIdsByKeyword(@PathVariable String word) throws IOException {
        return fbAccountService.findPostIdsByKeyword(word);
    }

    @RequestMapping("/sort-list")
    public Set<Facebook> findAll() throws IOException {
        return fbAccountService.findAll();
    }
}