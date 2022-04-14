package com.project.ecorea.controller.rest;

import java.io.*;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;


@RestController
public class ImageRestController {
	@Value("${upload.image.folder}")
	private String imgFolder;	
	@Value("${ck.image.folder}")
	private String CKFolder;
	
	@GetMapping("/images/{imagename}")
	public ResponseEntity<byte[]> showImage(@PathVariable String imagename) {
		File file = new File(CKFolder, imagename);
		if(file.exists()==false)
			file = new File(imgFolder, imagename);
		if(file.exists()==false)
			return null;
		
		HttpHeaders headers = new HttpHeaders();
		String extension = imagename.substring(imagename.lastIndexOf(".")+1).toUpperCase();
		MediaType type = null;
		if(extension.equals("JPG") || extension.equals("JPEG"))
			type = MediaType.IMAGE_JPEG;
		else if(extension.equals("PNG"))
			type = MediaType.IMAGE_PNG;
		else if(extension.equals("GIF"))
			type = MediaType.IMAGE_GIF;
		else
			return null;
		
		headers.setContentType(type);
		
		headers.add("Content-Disposition", "inline;filename="  + imagename);
		
		try {
			return ResponseEntity.ok().headers(headers).body(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
