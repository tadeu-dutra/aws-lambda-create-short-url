package com.serverless;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.service.UrlShortenerService;
import com.serverless.service.impl.S3StorageServiceImpl;
import com.serverless.service.impl.UrlShortenerServiceImpl;

public class App implements RequestHandler<Map<String, Object>, Map<String, String>> {

    private final UrlShortenerService urlShortenerService;

    public App(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }
    
    public App() {
        this(new UrlShortenerServiceImpl(new S3StorageServiceImpl(), new ObjectMapper()));
    }

    @Override
    public Map<String, String> handleRequest(Map<String, Object> input, Context context) {
        return urlShortenerService.handleUrlShortenerRequest(input);
    }
}
