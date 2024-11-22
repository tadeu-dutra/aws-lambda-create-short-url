package com.serverless.validator;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestValidator {

    public static Map<String, String> parseRequestBody(String body, ObjectMapper objectMapper) {
        
        try {

            return objectMapper.readValue(body, Map.class);

        } catch (JsonProcessingException ex) {

            String errorMessage = "Error parsing JSON body: " + ex.getMessage();
            throw new RuntimeException(errorMessage, ex);
        }
    }

    public static long parseExpirationTime(String expirationTime) {

        try {

            return Long.parseLong(expirationTime);

        } catch (NumberFormatException e) {

            String errorMessage = "Invalid expiration time format";
            throw  new RuntimeException(errorMessage, e);
        }
    }
}
