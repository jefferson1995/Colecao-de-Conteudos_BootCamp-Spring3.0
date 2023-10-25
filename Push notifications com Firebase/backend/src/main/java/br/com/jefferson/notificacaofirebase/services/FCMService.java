package br.com.jefferson.notificacaofirebase.services;

import br.com.jefferson.notificacaofirebase.dto.PushNotificationRequestDTO;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {

    private Logger logger = LoggerFactory.getLogger(FCMService.class);

    @PostConstruct // Garante que o método será executado, logo após o objeto ser executado
    public void initialize() throws IOException {
        FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.getApplicationDefault()).build();  // Faz a leitura da variável de ambiente
        FirebaseApp.initializeApp(options);
    }


    //Gera o token para enviar a notificação
    public void sendMessageToToken(PushNotificationRequestDTO request) throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); //Parciar Json e vice e versa
        String jsonOutput = gson.toJson(message); // Converte para Json
        String response = sendAndGetResponse(message); // Método auxiliar
        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response + " msg " + jsonOutput);
    }


    //Métodos auxiliares para construir o Message do firebase
    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }


    private Message getPreconfiguredMessageToToken(PushNotificationRequestDTO request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken()).build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequestDTO request) {
        Notification notification = Notification.builder().setTitle(request.getTitle()).setBody(request.getMessage()).build();
        return Message.builder().setNotification(notification);
    }


}
