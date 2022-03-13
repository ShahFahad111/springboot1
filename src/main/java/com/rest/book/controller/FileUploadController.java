package com.rest.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rest.book.helper.FileUploadHelper;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploadHelper fileUploadHelper;

	@PostMapping(value = "/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile m) {
		System.out.println(m.getOriginalFilename());
		System.out.println(m.getContentType());
		System.out.println(m.getName());
		System.out.println(m.getSize());

		try {
			if (m.isEmpty()) {
				return new ResponseEntity<>("Request must contain file", HttpStatus.INTERNAL_SERVER_ERROR);
			}

			if (!m.getContentType().equals("image/jpeg")) {
				return new ResponseEntity<>("Only Jpeg file allowed", HttpStatus.INTERNAL_SERVER_ERROR);
			}

			boolean uploadFile = fileUploadHelper.uploadFile(m);
			if (uploadFile)
				return new ResponseEntity<>("File Uploaded Successfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Error Eploading file", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
