package dev.haroon.blog.blogging.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadFile(String path, MultipartFile file) throws IOException {
		
		
		String originalFileName = file.getOriginalFilename();
		
		String randomId = UUID.randomUUID().toString();
		String randomFileName = randomId
				                .concat(originalFileName
				                .substring(originalFileName
				                .lastIndexOf('.')));
		
		String filePath = path + File.separator + randomFileName;
	
		File directory = new File(path);
		
		if(!directory.exists()) {
			directory.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		

		return randomFileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		String fullPath = path + File.separator + fileName;
		
		InputStream inputStream = new FileInputStream(fullPath);
		
		return inputStream;
	}

}
