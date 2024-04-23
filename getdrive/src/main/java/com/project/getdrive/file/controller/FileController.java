package com.project.getdrive.file.controller;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.getdrive.drive.handler.Aws;
import com.project.getdrive.drive.model.service.DriveService;
import com.project.getdrive.drive.model.vo.Drive;
import com.project.getdrive.file.model.service.FileService;
import com.project.getdrive.file.model.vo.File;
import com.project.getdrive.folder.model.service.FolderService;
import com.project.getdrive.folder.model.vo.Folder;

@Controller
public class FileController {

	@Autowired
	private DriveService driveService;
	@Autowired
	private FolderService folderService;
	@Autowired
	private FileService fileService;
	
	// 파일 업로드
	@RequestMapping(value = "upload.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String uploadFile(@RequestParam("file") MultipartFile file, Model model, File setFile) throws IOException {

	    Aws aws = new Aws();

	    // 업로드 위치 기록용 드라이브 추출
	    Drive drive = driveService.selectDrive(setFile.getFlDID());
	    // 아마존 버킷과 연동할 번호 추출
	    int count = setFile.getFlDID() - 1;
	    // 업로드 위치 기록용 폴더 추출
	    Folder folder = folderService.selectFolder(setFile.getFlFDID());

	    // 파일 확장자명을 알아내기 위한 인덱스 추출
	    String fileName = file.getOriginalFilename();
	    int lastIndex = fileName.lastIndexOf('.');
	    setFile.setFlName(fileName);
	    setFile.setFlExt(fileName.substring(lastIndex + 1));
	    setFile.setFlLoc(drive.getdName() + "/" + folder.getFdName());
	    setFile.setFlSize(file.getSize());

	    // MultipartFile을 File로 변환하여 업로드
	    java.io.File convertedFile = aws.convertMultiPartToFile(file);
	    aws.uploadFile("team" + setFile.getFlTID() + "drive-" + count, setFile.getFlName() + "/", convertedFile);

	    // 파일 업로드 후 데이터베이스에 파일 정보 저장
	    if (fileService.uploadFile(setFile) > 0) {
	        return "redirect:fpage.do?fdTID=" + drive.getdTID() + "&fdDID=" + folder.getFdDID() + "&fdCRUID="
	                + folder.getFdCRUID() + "&fdNo=" + folder.getFdNo();
	    } else {
	        model.addAttribute("message", "파일 업로드에 실패했습니다.");
	        return "common/error";
	    }
	}
	
	 // 파일 다운로드
    @GetMapping("fdown.do")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("fileId") int flNo) {
    	
    	Aws aws = new Aws();
        
        File setFile = fileService.selectFile(flNo);
        
        // 아마존 버킷과 연동할 번호 추출
        int count = setFile.getFlDID() - 1;
        
        String bucketName = "team" + setFile.getFlTID() + "drive-" + count;
        String objectKey = setFile.getFlName();
        
        String userHome = System.getProperty("user.home");
        
        String saveFilePath = userHome + "//" + objectKey;
    	
        try {
            URL presignedUrl = aws.getURL(bucketName, objectKey + "/");

            // URL을 사용하여 S3 객체 다운로드
            byte[] fileBytes = aws.downloadFileFromUrl(presignedUrl.toString(), saveFilePath);

            // 클라이언트로 다운로드할 파일과 함께 ResponseEntity 반환
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", objectKey);
            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
