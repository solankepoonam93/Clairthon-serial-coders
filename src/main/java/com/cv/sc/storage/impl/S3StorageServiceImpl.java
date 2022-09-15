package com.cv.sc.storage.impl;

import com.cv.sc.framework.AmazonS3Util;
import com.cv.sc.models.SearchParent;
import com.cv.sc.models.SearchResult;
import com.cv.sc.storage.EntityManagerProvider;
import com.cv.sc.storage.StorageService;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import static com.cv.sc.framework.AmazonS3Util.AWS_S3_BUCKET;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 15/09/22
 */
public class S3StorageServiceImpl implements StorageService {
    private AmazonS3Util amazons3; // TODO make singleton
    private StorageService dbStorageService;
    public S3StorageServiceImpl() {
        amazons3 = new AmazonS3Util();
        dbStorageService = new DBStorageServiceImpl();
    }

    @Override
    public Object save(Object result) throws UnsupportedEncodingException {
        if(!(result instanceof SearchResult)) {
            throw new RuntimeException("result to store must be of Type SearchResult");
        }
        SearchResult searchResult = (SearchResult) result;
        String fileContent = searchResult.getJsonResult();
        String fileName = "Json_Result_" + searchResult.getSearchParent().getId() + "_" + System.currentTimeMillis()+".json";
        String contentType = "text/plain";
        byte[] content = fileContent.getBytes("UTF-8");
        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, contentType, content);
        String urlPath = amazons3.uploadSingleFileToS3Bucket(multipartFile);

        searchResult.setJsonResult(urlPath);
        searchResult = (SearchResult) dbStorageService.save(searchResult);
        return searchResult;
    }

    @Override
    public Object fetch(Class entityClass, Object id) {
        return null; //TODO fetch from S3 and return
    }
}
