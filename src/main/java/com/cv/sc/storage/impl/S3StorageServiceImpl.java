package com.cv.sc.storage.impl;

import com.cv.sc.exception.IllegalTypeException;
import com.cv.sc.framework.AmazonS3Util;
import com.cv.sc.models.SearchResult;
import com.cv.sc.storage.StorageService;

import java.io.IOException;
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
        String fileContent = searchResult.getS3FileUrl();
        String fileName = "Json_Result_" + searchResult.getSearchParent().getId() + "_" + System.currentTimeMillis()+".json";
        String urlPath = amazons3Util.uploadSingleFileToS3Bucket(fileName, fileContent);

        searchResult.setS3FileUrl(urlPath);
        searchResult.setFileName(fileName);
        searchResult = (SearchResult) dbStorageService.save(searchResult);
        return searchResult;
    }

    @Override
    public Object fetch(Class entityClass, Object id) throws IOException {
        if(!entityClass.equals(String.class) || !(id instanceof String)) {
            throw new IllegalTypeException("Entity Class and id must be of type String while fetching a file from S3.");
        }
        String sourceFileName = (String) id;
        return amazons3Util.readFileFromS3(sourceFileName);
    }
}
