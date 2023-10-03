package com.upload.arquivos.amazon.jefferson.controllers;

import com.upload.arquivos.amazon.jefferson.dto.UriDTO;
import com.upload.arquivos.amazon.jefferson.services.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/amazon")
public class ImageController {


    @Autowired
    UploadImageService uploadImageService;

    @PostMapping(value = "/image")
    public ResponseEntity<UriDTO> uploadImage(@RequestParam("file") MultipartFile file){
         UriDTO dto = uploadImageService.uploadFile(file);
         return ResponseEntity.ok().body(dto);
    }

}
