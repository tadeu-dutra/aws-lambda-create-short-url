package com.serverless.service.impl;

import java.util.Map;

import com.serverless.service.UrlShortenerService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Override
    public Map<String, String> handleUrlShortenerRequest(Map<String, Object> input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
