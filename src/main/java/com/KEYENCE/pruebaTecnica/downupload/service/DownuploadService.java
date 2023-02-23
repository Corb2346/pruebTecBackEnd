package com.KEYENCE.pruebaTecnica.downupload.service;

import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import com.KEYENCE.pruebaTecnica.properties.FileUploadProperties;
import com.KEYENCE.pruebaTecnicaFileNotFoundExceptions.FileNotFoundException;
import com.KEYENCE.pruebaTecnicaInterface.IFileSystemStorage;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

public class DownuploadService implements IFileSystemStorage {

	private final Path dirLocation;
	
	@Autowired
	public DownuploadService( FileUploadProperties fileUploadProperties ) {
		this.dirLocation = Paths.get(fileUploadProperties.getLocation() ).toAbsolutePath().normalize();
	}
	
	@Override
	@PostConstruct
	public void init() throws Throwable {
		try {
			Files.createDirectories(this.dirLocation);
		}
		catch (Exception e) {
			throw new Exception ("no se creaó la carga");
		}
	}
	
	@Override 
	public String saveFile(MultipartFile file) {
		
		try {
			String fileName = file.getOriginalFilename();
			Path dfile = this.dirLocation.resolve( fileName );
			Files.copy(file.getInputStream( ), dfile, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		}catch(Exception e) {
			throw new Exception("Could not upload file");
		}
		

	@Override
	public Resource loadFile(String fileName) throws MalformedURLException {
		Path file = this.dirLocation.resolve(fileName).normalize();
		Resource resource = (Resource) new UrlResource(file.toUri() );
		if( ((AbstractFileResolvingResource) resource).exists() || ((org.springframework.core.io.Resource) resource).isReadable() ) {
			return resource;
		} else {
			throw new FileNotFoundException();
		}
	}
	
	
	
	
	
}
