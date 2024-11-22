package com.serverless.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.model.UrlData;
import com.serverless.service.S3StorageService;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3StorageServiceImpl implements S3StorageService {

    private final String BUCKET = "url-shortener-tadeu-dev-bucket";
    private final S3Client s3Client = S3Client.builder().build();
    
    @Override
    public void saveUrlData(UrlData urlData, String shortUrlCode) {

        try {

            PutObjectRequest request = PutObjectRequest.builder()
            .bucket(BUCKET)
            .key(shortUrlCode + ".json")
            .build();

            String urlDataJson = new ObjectMapper().writeValueAsString(urlData);
            
            s3Client.putObject(request, RequestBody.fromString(urlDataJson));

        } catch (JsonProcessingException e) {

            String errorMessage = "Error saving url data to S3: " + e.getMessage();
            throw new RuntimeException(errorMessage, e);
        }
    }
}
