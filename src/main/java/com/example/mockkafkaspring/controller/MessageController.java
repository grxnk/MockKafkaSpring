package com.example.mockkafkaspring.controller;

import com.example.mockkafkaspring.dto.MessageRequest;
import com.example.mockkafkaspring.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    private final KafkaProducerService kafkaProducerService;

    public MessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/post-message")
    public ResponseEntity<String> handleMessage(@RequestBody MessageRequest request) {
        log.info("Received request: {}", request);
        return kafkaProducerService.sendMessage(request.getMsg_id());
    }
}
