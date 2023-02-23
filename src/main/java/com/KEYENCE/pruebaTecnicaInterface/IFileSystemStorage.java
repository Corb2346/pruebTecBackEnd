package com.KEYENCE.pruebaTecnicaInterface;

import java.net.MalformedURLException;

import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;

public interface IFileSystemStorage {

	void init() throws Throwable;
	String saveFile(MultipartFile file);
	Resource loadFile(String fileName) throws MalformedURLException;
}
