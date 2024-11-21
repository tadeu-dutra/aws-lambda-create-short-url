package com.serverless.service;

import com.serverless.model.UrlData;

public interface S3StorageService {
    void saveUrlData(UrlData urlData, String shortUrlCode);
}
