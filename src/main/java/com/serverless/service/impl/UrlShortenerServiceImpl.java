package com.serverless.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.model.UrlData;
import com.serverless.service.S3StorageService;
import com.serverless.service.UrlShortenerService;
import com.serverless.validator.RequestValidator;

public class UrlShortenerServiceImpl implements UrlShortenerService {

    private static final int SUBSTRING_START_INDEX = 0;
    private static final int SUBSTRING_LENGTH = 8;

    private final S3StorageService s3StorageService;
    private final ObjectMapper objectMapper;

    public UrlShortenerServiceImpl(S3StorageService s3StorageService, ObjectMapper objectMapper) {
        this.s3StorageService = null;
        this.objectMapper = objectMapper;
    }
    
    @Override
    public Map<String, String> handleUrlShortenerRequest(Map<String, Object> input) {

        Map<String, String> bodyMap = RequestValidator
            .parseRequestBody(input.get("body").toString(), objectMapper);
        
        String originalUrl = bodyMap.get("originalUrl");
        long expirationTimeInSeconds = RequestValidator.parseExpirationTime(bodyMap.get("expirationTime"));

        String shortUrlCode = this.generateUrlCode();
        UrlData urlData = new UrlData(originalUrl, expirationTimeInSeconds);

        s3StorageService.saveUrlData(urlData, shortUrlCode);

        Map<String, String> response = new HashMap<>();
        response.put("code", shortUrlCode);

        return response;
    }

    private static String generateUrlCode() {
        return UUID.randomUUID().toString().substring(SUBSTRING_START_INDEX, SUBSTRING_LENGTH);
    }
}
