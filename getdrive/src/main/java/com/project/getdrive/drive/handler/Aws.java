package com.project.getdrive.drive.handler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutBucketPolicyRequest;
import software.amazon.awssdk.services.s3.model.PutBucketPolicyResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class Aws {

    private final S3Client s3Client;

    // 아마존 액세스 API 키
    private final String accessKey = "AKIAYS2NXNON3FNNTRVI";
    private final String secretKey = "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";

    // 생성자
    public Aws() {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        this.s3Client = S3Client.builder()
            .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
            .region(Region.AP_NORTHEAST_2) // 여기에 원하는 리전을 설정합니다.
            .build();   	
    }

    // 파일 변환 메서드
    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    // 버킷 생성
    public void createBucket(String bucketName) {
        try {
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build();
            s3Client.createBucket(createBucketRequest);
            System.out.format("Bucket %s has been created successfully.\n", bucketName);
        } catch (S3Exception e) {
            System.err.println("Bucket creation failed: " + e.awsErrorDetails().errorMessage());
        }
    }
    
    // 버킷 공개로 설정
    public void makeBucketPublic(String bucketName) {
        try {
            // 버킷에 대한 공개 정책 생성
            Map<String, String> policyMap = new HashMap<>();
            policyMap.put("Version", "2012-10-17");
            policyMap.put("Statement", "[{\"Sid\":\"PublicReadGetObject\",\"Effect\":\"Allow\",\"Principal\":\"*\",\"Action\":\"s3:GetObject\",\"Resource\":\"arn:aws:s3:::" + bucketName + "/*\"}]");
            String policyString = new ObjectMapper().writeValueAsString(policyMap);

            // 버킷 정책을 설정하여 버킷을 공개로 만듭니다.
            PutBucketPolicyRequest putBucketPolicyRequest = PutBucketPolicyRequest.builder()
                    .bucket(bucketName)
                    .policy(policyString)
                    .build();
            PutBucketPolicyResponse putBucketPolicyResponse = s3Client.putBucketPolicy(putBucketPolicyRequest);

            System.out.format("Bucket %s has been made public successfully.\n", bucketName);
        } catch (S3Exception | JsonProcessingException e) {
            System.err.println("Failed to make bucket public: " + e.getMessage());
        }
    }

    // 폴더 생성
    public void createFolder(String bucketName, String folderName) {
        try {
            // 폴더 이름에 슬래시('/')를 추가하여 폴더를 나타냅니다.
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(folderName + "/") // 폴더 이름에 슬래시를 추가하여 폴더를 나타냅니다.
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            // 빈 객체를 업로드합니다. 빈 내용의 ByteBuffer를 사용합니다.
            ByteBuffer data = ByteBuffer.wrap(new byte[0]);
            s3Client.putObject(request, RequestBody.fromByteBuffer(data));
            System.out.format("Folder %s has been created successfully.\n", folderName);
        } catch (S3Exception e) {
            System.err.println("Folder creation failed: " + e.awsErrorDetails().errorMessage());
        }
    }

    // 파일 업로드
    public void uploadFile(String bucketName, String folderName, File file) {
        try {
            s3Client.putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(folderName)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build(), file.toPath());
            System.out.format("File %s has been uploaded successfully.\n", folderName);
        } catch (S3Exception e) {
            System.err.println("File upload failed: " + e.awsErrorDetails().errorMessage());
        }
    }

    // 파일 다운로드 URL
    public URL getURL(String bucketName, String keyName) {
    	
    	URL url = null;
    	
        try {
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .build();

            url = s3Client.utilities().getUrl(request);
            System.out.println("The URL for  " + keyName + " is " + url);

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        
        return url;
    }
	  
    // 파일 다운로드
    public byte[] downloadFileFromUrl(String fileUrl, String saveFilePath) throws IOException {
        URL url = new URL(fileUrl);
        
        byte[] buffer = null;
        
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(saveFilePath)) {
            buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("File downloaded successfully.");
        }
        return buffer;
    }
}
