package com.serverless.service.impl;

import com.serverless.model.UrlData;
import com.serverless.service.S3StorageService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class S3StorageServiceImpl implements S3StorageService {

    @Override
    public void saveUrlData(UrlData urlData, String shortUrlCode) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
