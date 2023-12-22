package com.doan.ecofootprint_be.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

	String uploadImage(MultipartFile image) throws IOException;

}
