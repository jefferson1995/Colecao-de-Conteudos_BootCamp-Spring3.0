package br.com.jefferson.notificacaofirebase.controllers;

import br.com.jefferson.notificacaofirebase.dto.PushNotificationRequestDTO;
import br.com.jefferson.notificacaofirebase.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody PushNotificationRequestDTO dto){
        notificationService.sendNotification(dto);
        return ResponseEntity.noContent().build();
    }

}
