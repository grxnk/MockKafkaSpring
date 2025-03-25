package com.example.mockkafkaspring.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DelayService {

    private final Map<String, Integer> delayMap = new HashMap<>();

    public void setDelay(String endpoint, int delayMs) { delayMap.put(endpoint, delayMs); }

    public int getDelay(String endpoint) { return delayMap.getOrDefault(endpoint, 0); }

    public void applyDelay(String endpoint) {
        int delay = getDelay(endpoint);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Map<String, Integer> getAllDelays() {
        return delayMap;
    }
}
