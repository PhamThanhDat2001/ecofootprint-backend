package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.service.IFileService;

import com.doan.ecofootprint_be.utils.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1")
@Validated
public class FileController {

	@Autowired
	private IFileService fileService;

	@PostMapping(value = "/image")
	public ResponseEntity<?> upLoadImage(@RequestParam(name = "image") MultipartFile image) throws IOException {

		if (!new FileManager().isTypeFileImage(image)) {
			return new ResponseEntity<>("File must be image!", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return new ResponseEntity<>(fileService.uploadImage(image), HttpStatus.OK);
	}
}
