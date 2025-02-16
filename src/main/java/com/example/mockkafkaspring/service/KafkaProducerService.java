package com.example.mockkafkaspring.service;

import com.example.mockkafkaspring.model.KafkaMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {
    private static final String TOPIC = "postedmessages";
    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ResponseEntity<String> sendMessage(String msg_id) {
        KafkaMessage message = new KafkaMessage();
        message.setMsg_id(msg_id);
        message.setTimestamp(System.currentTimeMillis());
        message.setMethod("POST");
        message.setUri("/post-message");
        try {
            kafkaTemplate.send(TOPIC, message);
            return(ResponseEntity.ok("200: OK"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500: Internal Server Error");
        }

    }

}
