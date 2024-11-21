package com.serverless.service;

import java.util.Map;

public interface UrlShortenerService {
    Map<String, String> handleUrlShortenerRequest(Map<String, Object> input);
}
