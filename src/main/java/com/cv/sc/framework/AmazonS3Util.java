/**
 * 
 */
package com.cv.sc.framework;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * @author Chinna
 *
 */
public class AmazonS3Util {

	private static final String SUFFIX = "/";
	private static final Logger log = LogManager.getLogger(AmazonS3Util.class);
	public static final String awsS3Bucket = "bkpunebucket1";
	private String awsAccessKey;
	private String awsSecretKey;
	private final static String AWS_REGION = "ap-south-1";
	private AmazonS3 amazonS3;

	private static AmazonS3Util instance;

	// added by Poonam
	private AmazonS3Util() {
		awsAccessKey = System.getenv("AWS_ACCESS_KEY");
		awsSecretKey = System.getenv("AWS_SECRET_KEY");
		amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.withRegion(Regions.AP_SOUTH_1).build();
	}

	public static AmazonS3Util getInstance() {
		if (instance == null) {
			instance = new AmazonS3Util();
		}
		return instance;
	}

	public String uploadSingleFileToS3Bucket(String fileName, String fileContent) {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
		amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(AWS_REGION).build();
		amazonS3.putObject(awsS3Bucket + SUFFIX + "result", fileName, fileContent);
		return amazonS3.getUrl(awsS3Bucket, "result" + SUFFIX + fileName).toString();
	}

	public String getFileFromS3(String s3Prefix, String sourceFileName, String destFolder) throws IOException {
		String s3FileName = s3Prefix + "/" + sourceFileName;
		String destFileName = destFolder + sourceFileName;
		log.info("Download file" + s3FileName);
		log.info("Download dest file" + destFileName);
		try {
			GetObjectRequest getObjectRequest = new GetObjectRequest(awsS3Bucket, s3FileName);
			S3Object s3Object = amazonS3.getObject(getObjectRequest);

			InputStream inputStream = s3Object.getObjectContent();

			OutputStream outputStream = new FileOutputStream(destFileName);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			log.info(s3FileName + " File is downloded from S3 and put at " + destFileName);
		} catch (AmazonServiceException ase) {
			log.info("Caught an AmazonServiceException, " + "which means your request made it "
					+ "to Amazon S3, but was rejected with an error response " + "for some reason.");
			log.info("Error Message:    " + ase.getMessage());
			log.info("HTTP Status Code: " + ase.getStatusCode());
			log.info("AWS Error Code:   " + ase.getErrorCode());
			log.info("Error Type:       " + ase.getErrorType());
			log.info("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			log.info("Caught an AmazonClientException, " + "which means the client encountered "
					+ "an internal error while trying to communicate" + " with S3, "
					+ "such as not being able to access the network.");
			log.info("Error Message: " + ace.getMessage());
		}
		return destFileName;
	}


}
