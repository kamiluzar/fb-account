package com.kzr.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kzr.model.Facebook;
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
 * Created by Kamil on 2017-08-16.
 */

@Service
public class FbAccountService implements FacebookService {

    static final String IGNORED_CHARS = "[\\-+.\\^:,?!'\"()\\[\\];<>]";

    public FbAccountService() throws IOException {
    }

    public Facebook findById(String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Facebook obj = mapper.readValue(new File("src\\main\\resources\\json\\f" + id + ".json"), Facebook.class);
        return obj;
    }

    public Map<String, Long> findMostCommonWords() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Long> mapOfWords = new TreeMap<>();
        String allPosts = "";
        for(int i = 1; i<6; i++) {
            Facebook facebook = mapper.readValue(new File("src\\main\\resources\\json\\f" + i + ".json"), Facebook.class);
            String facebookString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(facebook);
            JSONObject jsonObject = new JSONObject(facebookString);
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
            Facebook facebook = mapper.readValue(new File("src\\main\\resources\\json\\f" + i + ".json"), Facebook.class);
            String facebookString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(facebook);
            JSONObject jsonObject = new JSONObject(facebookString);
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

    public Set<Facebook> findAll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Set<Facebook> accounts = new TreeSet<>();
        for(int i = 1; i<6; i++) {
            Facebook facebook = mapper.readValue(new File("src\\main\\resources\\json\\f" + i + ".json"), Facebook.class);
            accounts.add(facebook);
        }
        return accounts;
    }
}