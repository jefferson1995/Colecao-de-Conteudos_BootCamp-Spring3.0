package com.envio.emails.barbosa.projetos.controllers;

import com.envio.emails.barbosa.projetos.dto.EmailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody EmailDTO dto){
        return ResponseEntity.noContent().build();
    }
}
