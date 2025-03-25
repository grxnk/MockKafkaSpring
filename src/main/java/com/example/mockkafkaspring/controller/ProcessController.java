package com.example.mockkafkaspring.controller;

import com.example.mockkafkaspring.service.DelayService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/process")
public class ProcessController {

    private final Map<String, Integer> sessionState = new HashMap<>();
    private final DelayService delayService;

    public ProcessController(DelayService delayService) {
        this.delayService = delayService;
    }

    @PostMapping("/session/init")
    public String initSession() {
        delayService.applyDelay("/session/init");
        String sessionId = UUID.randomUUID().toString();
        sessionState.put(sessionId, 0);
        return "Session ID: " + sessionId;
    }

    @PostMapping("/session/{sessionId}/step1")
    public String step1(@PathVariable String sessionId) {
        delayService.applyDelay("/session/{sessionId}/step1");
        if (!sessionState.containsKey(sessionId)) return "Invalid session";
        if (sessionState.get(sessionId) != 0) return "Step 1 not allowed";
        sessionState.put(sessionId, 1);
        return "Step 1 complete";
    }

    @PostMapping("/session/{sessionId}/step2")
    public String step2(@PathVariable String sessionId) {
        delayService.applyDelay("/session/{sessionId}/step2");
        if (!sessionState.containsKey(sessionId)) return "Invalid session";
        if (sessionState.get(sessionId) != 1) return "Step 2 not allowed";
        sessionState.put(sessionId, 2);
        return "Step 2 complete";
    }

    @PostMapping("/session/{sessionId}/step3")
    public String step3(@PathVariable String sessionId) {
        delayService.applyDelay("/session/{sessionId}/step3");
        if (!sessionState.containsKey(sessionId)) return "Invalid session";
        if (sessionState.get(sessionId) != 2) return "Step 3 not allowed";
        sessionState.put(sessionId, 3);
        return "Step 3 complete";
    }

    @GetMapping("/session/{sessionId}/result")
    public String getResult(@PathVariable String sessionId) {
        delayService.applyDelay("/session/{sessionId}/result");
        if (!sessionState.containsKey(sessionId)) return "Invalid session";
        if (sessionState.get(sessionId) != 3) return "Process not complete";
        return "Process complete for session: " + sessionId;
    }
}
