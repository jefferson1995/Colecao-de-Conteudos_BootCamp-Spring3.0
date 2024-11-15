package com.upload.arquivos.amazon.jefferson.services;

import com.upload.arquivos.amazon.jefferson.dto.UriDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@Service
public class UploadImageService {

    @Autowired
    private S3Service s3Service;


    public UriDTO uploadFile(MultipartFile file) {
        URL url = s3Service.uploadFile(file);
        return new UriDTO(url.toString());
    }


}
