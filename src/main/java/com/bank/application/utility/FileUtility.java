package com.bank.application.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

import com.bank.application.enums.FileConstants;

public class FileUtility {

	private static String getUploadDirectory(String filePath) {
		StringBuilder str = new StringBuilder();
		str.append(FileConstants.DATA_FOLDER.getStrValue());
		str.append(File.separator);
		str.append(filePath);
		str.append(File.separator);
		return str.toString();
	}
	public static String saveFile(MultipartFile file, String fileType, String fileName) {
		
		String uploadDir = getUploadDirectory(fileType);
		Path uploadPath = Paths.get(uploadDir);
		
		try (InputStream inputStream = file.getInputStream()) {
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);				
			}
			fileName = getFileName(fileName);
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return fileName;
	}
	
	public static String getFileName(String fileName) {
		StringBuilder str = new StringBuilder();
		str.append(fileName.substring(0,fileName.lastIndexOf(".")));
		str.append(getUniqueCode());
		str.append(fileName.substring(fileName.lastIndexOf(".")));
		return str.toString();
	}
	
	public static String getUniqueCode() {
		StringBuffer string = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			string.append(random.nextInt(10));
		}
		return string.toString().substring(25, 30);
	}

}
