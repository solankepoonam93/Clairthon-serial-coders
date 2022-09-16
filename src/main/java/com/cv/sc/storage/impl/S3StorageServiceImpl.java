package com.cv.sc.storage.impl;

import com.cv.sc.exception.IllegalTypeException;
import com.cv.sc.framework.AmazonS3Util;
import com.cv.sc.models.SearchResult;
import com.cv.sc.storage.StorageService;

import java.io.UnsupportedEncodingException;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 15/09/22
 */
public class S3StorageServiceImpl implements StorageService {
    private final AmazonS3Util amazons3Util;
    private final StorageService dbStorageService;
    public S3StorageServiceImpl() {
        amazons3Util = AmazonS3Util.getInstance();
        dbStorageService = new DBStorageServiceImpl();
    }

    @Override
    public Object save(Object result) throws UnsupportedEncodingException {
        if(!(result instanceof SearchResult)) {
            throw new IllegalTypeException("Result to store must be of Type SearchResult");
        }
        SearchResult searchResult = (SearchResult) result;
        String fileContent = searchResult.getJsonResult();
        String fileName = "Json_Result_" + searchResult.getSearchParent().getId() + "_" + System.currentTimeMillis()+".json";
        String urlPath = amazons3Util.uploadSingleFileToS3Bucket(fileName, fileContent);

        searchResult.setJsonResult(urlPath);
        searchResult = (SearchResult) dbStorageService.save(searchResult);
        return searchResult;
    }

    @Override
    public Object fetch(Class entityClass, Object id) {
        return null; //TODO fetch from S3 and return
    }
}
