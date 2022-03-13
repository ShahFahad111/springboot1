package com.rest.book.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
	//public final String UPLOAD_PATH="E:\\Java\\STS_Workspace\\durgesh\\springrestbook1\\src\\main\\resources\\static\\images";
	public final String UPLOAD_PATH=new ClassPathResource("static/images").getFile().getAbsolutePath();
	
	public FileUploadHelper() throws IOException {
		
	}
	
	public boolean uploadFile(MultipartFile mp) {
		boolean status=false;
		
		try {
			/*
			 * InputStream is = mp.getInputStream(); byte[] b = new byte[is.available()];
			 * is.read(b);
			 * 
			 * FileOutputStream fos = new
			 * FileOutputStream(UPLOAD_PATH+File.separator+mp.getOriginalFilename());
			 * fos.write(b);
			 * 
			 * fos.flush(); fos.close(); is.close(); status = true;
			 */
			
			Files.copy(mp.getInputStream(), Paths.get(UPLOAD_PATH+File.separator+mp.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			status=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
}
