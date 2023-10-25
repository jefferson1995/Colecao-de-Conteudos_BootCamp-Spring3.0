package br.com.jefferson.notificacaofirebase.services;

import br.com.jefferson.notificacaofirebase.dto.PushNotificationRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    //Service para enviar a notificação

    private Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
     private FCMService fcmService;

    public void sendNotification(PushNotificationRequestDTO dto){
        try {
            fcmService.sendMessageToToken(dto);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }

    }




}
