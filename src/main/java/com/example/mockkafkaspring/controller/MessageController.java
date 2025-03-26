package com.example.mockkafkaspring.controller;

import com.example.mockkafkaspring.dto.MultiDelayRequest;
import com.example.mockkafkaspring.dto.MessageRequest;
import com.example.mockkafkaspring.model.KafkaMessage;
import com.example.mockkafkaspring.service.DelayService;
import com.example.mockkafkaspring.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    private final KafkaProducerService kafkaProducerService;
    private final List<KafkaMessage> mockStorage = new ArrayList<>();
    private final DelayService delayService;

    public MessageController(KafkaProducerService kafkaProducerService, DelayService delayService) {
        this.kafkaProducerService = kafkaProducerService;
        this.delayService = delayService;
    }

    @PostMapping("/post-delay")
    public String handleMultiDelay(@RequestBody MultiDelayRequest request) {
        StringBuilder response = new StringBuilder();
        for (MultiDelayRequest.DelayEntry entry : request.getDelays()) {
            delayService.setDelay(entry.getEndpoint(), entry.getDelay());
            response.append("Set delay ")
                    .append(entry.getDelay())
                    .append("ms for ")
                    .append(entry.getEndpoint())
                    .append("\n");
        }
        return response.toString();
    }

    @PostMapping("/post-message")
    public String handleMessage(@RequestBody MessageRequest request) throws InterruptedException {
        log.info("Received request: {}", request);
        delayService.applyDelay("/post-message");

        KafkaMessage msg = new KafkaMessage();
        msg.setMsg_id(UUID.randomUUID().toString());
        msg.setTimestamp(System.currentTimeMillis());
        msg.setMethod("POST");
        msg.setUri("post/message");
        msg.setMessageText(request.getMessageText());

        mockStorage.add(msg);

//        System.out.println(request.getMessageText()); // Тестирование в консоли
//        return kafkaProducerService.sendMessage(request.getMessageText()); // Отправка сообщения в кафку
        return "Message ID: " + msg.getMsg_id() + "\n" + "Message Text: " + msg.getMessageText() + "\n" + "Timestamp: " + msg.getTimestamp();
    }

    @GetMapping("/mock-data")
    public List<KafkaMessage> getMockData() {
        delayService.applyDelay("/mock-data");

        return mockStorage;
    }
}
