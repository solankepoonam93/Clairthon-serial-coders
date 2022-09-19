/**
 *
 */
package com.cv.sc.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.*;

/**
 * @author Chinna
 *
 */
public class AmazonS3Util {

	private static final String SUFIX = "result/";
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
		amazonS3.putObject(awsS3Bucket + SUFIX, fileName, fileContent);
		return amazonS3.getUrl(awsS3Bucket, SUFIX + fileName).toString();
	}

	public String readFileFromS3(String sourceFileName) throws IOException {
		String s3FileName = SUFIX + sourceFileName;
		StringBuilder stringBuilder = new StringBuilder();

		InputStream inputStream = null;
		BufferedReader br = null;
		try {
			GetObjectRequest getObjectRequest = new GetObjectRequest(awsS3Bucket, s3FileName);
			S3Object s3Object = amazonS3.getObject(getObjectRequest);
			inputStream = s3Object.getObjectContent();
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (AmazonServiceException ase) {
			ase.printStackTrace();
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
		} finally {
			inputStream.close();
			br.close();
		}
		return stringBuilder.toString();
	}
}
