package com.kzr.service;

import com.kzr.model.Facebook;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface FacebookService {
    Facebook findById(String id) throws IOException;
    Map<String, Long> findMostCommonWords() throws IOException;
    Set<String> findPostIdsByKeyword(String word) throws IOException;
    Set<Facebook> findAll() throws IOException;
}