package com.example.mockkafkaspring.controller;

import com.example.mockkafkaspring.dto.MessageRequest;
import com.example.mockkafkaspring.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    private final KafkaProducerService kafkaProducerService;
    private static int delay = 0;

    public MessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    /**
     * Этот метод обрабатывает post-запросы модификации задержки.
     *
     * @param request обрабатываемый запрос
     * @return значение задержки из body
     */
    @PostMapping("/post-delay")
    public String handleDelay(@RequestBody MessageRequest request) {
        log.info("Received request: {}", request);
//        return kafkaProducerService.sendMessage(request.getMsg_id()); // Отправка сообщения в кафку
        System.out.println(request.getMsg_id());
        delay=Integer.parseInt(request.getMsg_id());
        return request.getMsg_id();
    }

    /**
     * Этот метод обрабатывает post-запросы с id сообщения.
     *
     * @param request обрабатываемый запрос
     * @return id сообщения из body
     */
    @PostMapping("/post-message")
    public String handleMessage(@RequestBody MessageRequest request) throws InterruptedException {
        log.info("Received request: {}", request);
        Thread.sleep(this.delay);
//        return kafkaProducerService.sendMessage(request.getMsg_id()); // Отправка сообщения в кафку
        return request.getMsg_id();
    }

}

