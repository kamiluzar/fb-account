package com.kzr.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kzr.model.Account;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Kamil on 2017-08-14.
 */

@Service
public class FacebookService {

    static final String IGNORED_CHARS = "[\\-+.\\^:,?!'\"()\\[\\];<>]";

    public FacebookService() throws IOException {
    }

    public Account findById(String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Account obj = mapper.readValue(new File("src\\main\\resources\\json\\f" + id + ".json"), Account.class);
        return obj;
    }

    public Map<String, Long> findMostCommonWords() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Long> mapOfWords = new TreeMap<>();
        String allPosts = "";
        for(int i = 1; i<6; i++) {
            Account account = mapper.readValue(new File("src\\main\\resources\\json\\f" + i + ".json"), Account.class);
            String accountString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(account);
            JSONObject jsonObject = new JSONObject(accountString);
            JSONArray jsonArray = jsonObject.getJSONArray("posts");
            //for (int j = 0; i < jsonArray.length(); j++) {
            int j = 0;
            while(j<3) {
                String post = jsonArray.getJSONObject(j).getString("message");
                allPosts = allPosts + post;
                j++;
            }
        }
        allPosts = allPosts.replaceAll(IGNORED_CHARS,"").trim().toLowerCase();
        String[] result = allPosts.split(" ");
        for(int i = 0; i < result.length; i++){
            long wordFound = StringUtils.countMatches(allPosts, result[i]);
            mapOfWords.put(result[i], wordFound);
        }
        return mapOfWords;
    }

    public Set<String> findPostIdsByKeyword(String word) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Set<String> message = new TreeSet<>();
        for(int i = 1; i<6; i++) {
            Account account = mapper.readValue(new File("src\\main\\resources\\json\\f" + i + ".json"), Account.class);
            String accountString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(account);
            JSONObject jsonObject = new JSONObject(accountString);
            JSONArray jsonArray = jsonObject.getJSONArray("posts");
            int j=0;
            while (j < 3){
                String post = jsonArray.getJSONObject(j).getString("message");
                String post_id = jsonArray.getJSONObject(j).getString("id");
                if(post.toLowerCase().contains(word.toLowerCase()))
                    message.add(post_id);
                j++;
            }
        }
        return message;
    }

    public Set<Account> findAll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Set<Account> accounts = new TreeSet<>();
        for(int i = 1; i<6; i++) {
            Account account = mapper.readValue(new File("src\\main\\resources\\json\\f" + i + ".json"), Account.class);
            accounts.add(account);
        }
        return accounts;
    }

}


