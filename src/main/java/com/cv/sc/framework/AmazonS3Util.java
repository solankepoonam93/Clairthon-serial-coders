/**
 * 
 */
package com.cv.sc.framework;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chinna
 *
 */
public class AmazonS3Util {

	private static final String SUFFIX = "/";
	private static String bucketName = "serielcoders";
	private static final Logger log = LogManager.getLogger(AmazonS3Util.class);
	public static final String AWS_S3_BUCKET = "bkpunebucket1";
	public static final String AWS_ACCESS_KEY = "AKIA4WVCDFY4UI5YTM4H";
	public static final String AWS_SECRET_KEY = "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";

	public static AmazonS3 amazonS3;


	// added by Poonam
	public AmazonS3Util() {
		AWSCredentials myCredentials = new BasicAWSCredentials(String.valueOf(AWS_ACCESS_KEY),
				String.valueOf(AWS_SECRET_KEY));
		// AmazonS3Client s3Client = new AmazonS3Client(myCredentials);
		amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(myCredentials))
				.withRegion(Regions.AP_SOUTH_1).build();
	}

	public static String uploadSingleFileToS3Bucket(final String bucketName, MultipartFile file) {

		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
		amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion("ap-south-1").build();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMddhhmmssSSS");
		String timestamp = simpleDateFormat.format(new java.util.Date());

		File fileToUpload = new File(file.getOriginalFilename());
		String fileName = fileToUpload.getName();

		String extensionForFile = "";
		if (fileName != null && !fileName.isEmpty()) {
			extensionForFile = fileName.substring(fileName.lastIndexOf("."));
		}

		File resFileName = new File("JsonResult_" + timestamp + extensionForFile);

		File tempFile = null;
		try {
			tempFile = File.createTempFile("JsonResult_" + timestamp, extensionForFile);
			file.transferTo(tempFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tempFile.renameTo(resFileName);
		String urlPath = null;

		PutObjectRequest putObjectRequest = new PutObjectRequest(AWS_S3_BUCKET + SUFFIX + "result",
				resFileName.getName(), resFileName);

		putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

		amazonS3.putObject(putObjectRequest);

		urlPath = amazonS3.getUrl(AWS_S3_BUCKET, "result" + SUFFIX + resFileName.getName()).toString();

		// ask JVM to delete it upon JVM exit if you forgot / can't delete due exception
		resFileName.deleteOnExit();

		// tidy up
		resFileName.delete();

		return urlPath;
	}


	/* @Authorization Poonam */
	public String GetFileFromS3(String s3Prefix, String sourceFileName, String destFolder) throws IOException {
		String s3FileName = s3Prefix + "/" + sourceFileName;
		String destFileName = destFolder + sourceFileName;
		log.info("Download file" + s3FileName);
		log.info("Download dest file" + destFileName);
		try {
			GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, s3FileName);
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
