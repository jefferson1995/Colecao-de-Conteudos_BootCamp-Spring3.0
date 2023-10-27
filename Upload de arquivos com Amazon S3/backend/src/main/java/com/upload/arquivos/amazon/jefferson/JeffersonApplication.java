package com.upload.arquivos.amazon.jefferson;

import com.upload.arquivos.amazon.jefferson.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JeffersonApplication implements CommandLineRunner {

	@Autowired
	S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(JeffersonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		s3Service.uploadFile("C://Users//jeffe//Downloads//2023-09-20_08h51_52.png");
12
		 */

	}
}
