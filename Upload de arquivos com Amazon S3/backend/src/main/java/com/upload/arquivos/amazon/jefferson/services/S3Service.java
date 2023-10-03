package com.upload.arquivos.amazon.jefferson.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;

@Service
public class S3Service {


    private static Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;


    @Value("${s3.bucket}")
    private String bucketName;

    /*
    Desativado para usar no endpoint

    public void uploadFile(String localFilePath) {
        try {
            File file = new File(localFilePath);
            LOG.info("Upload start");
            s3client.putObject(new PutObjectRequest(bucketName, "test.jpg", file));
            LOG.info("Upload end");
        } catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status code: " + e.getErrorCode());
        } catch (AmazonClientException e) {
            LOG.info("AmazonClientException: " + e.getMessage());
        }
    }

     */
    public URL uploadFile(MultipartFile file) {
        try {


            String originalName = file.getOriginalFilename(); //Nome original do arquivo
            String extension = FilenameUtils.getExtension(originalName); // para extrair a extensao do arquivo
            String fileName = Instant.now().toEpochMilli() + "." + extension; //nome que irá para s3 da aws - data do upload + extensao

            InputStream inputStream = file.getInputStream(); //Pega o arquivo para enviar
            String contentType = file.getContentType(); //tipo do arquivo

            return uploadFileAmazon(inputStream, fileName, contentType);


        } catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status code: " + e.getErrorCode());
        } catch (AmazonClientException e) {
            LOG.info("AmazonClientException: " + e.getMessage());
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return null;
    }

    private URL uploadFileAmazon(InputStream inputStream, String fileName, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);

        LOG.info("Upload start");
        s3client.putObject(bucketName, fileName, inputStream, objectMetadata); //Realiza o upload no s3
        LOG.info("Upload end");
        return s3client.getUrl(bucketName, fileName); //pega a url do arquivo no bucket informado
    }
}
