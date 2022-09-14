package com.cv.sc.serviceImpl;

import com.cv.sc.framework.AmazonS3Util;
import com.cv.sc.models.ApiResponse;
import com.cv.sc.models.SearchParent;
import com.cv.sc.models.SearchResult;
import com.cv.sc.repository.ConfigRepository;
import com.cv.sc.repository.SearchParentRepository;
import com.cv.sc.repository.SearchResultRepository;
import com.cv.sc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private SearchResultRepository resultModelRepository;

    @Autowired
    private SearchParentRepository searchParentRepository;

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public ApiResponse saveResult(SearchResult resultModel) {

        String timestamp = new SimpleDateFormat("YYYYMMddhhmmssSSS").format(new Date());
        String fileContent = resultModel.getJsonResult();
        String fileName = "Json_Result_" + timestamp+".txt";
        String contentType = "text/plain";
        byte[] content = null;
        File myObj = null;
        try {
            myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(myObj.getName());
            byte[] decoder = Base64.getDecoder().decode(fileContent);
            String dString = new String(decoder, StandardCharsets.UTF_8);
            myWriter.write(dString);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        AmazonS3Util amazons3 = new AmazonS3Util();
        Path path = Paths.get(fileName);
        System.out.println("Path: "+path);
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile multipartFile = new MockMultipartFile(fileName,
                fileName, contentType, content);

        String urlPath = amazons3.uploadSingleFileToS3Bucket("serielcoders", multipartFile );
        System.out.println("URL: "+urlPath);
        resultModel.setJsonResult(urlPath);

        SearchParent searchParent= searchParentRepository.findById(1);
        resultModel.setSearchParent(searchParent);
        resultModelRepository.save(resultModel);
        return new ApiResponse(HttpStatus.OK, "Success", resultModel);
    }
}
