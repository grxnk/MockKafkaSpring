package com.example.mockkafkaspring.service;

import org.springframework.stereotype.Service;

@Service
public class DelayService {

    private int delay = 0; // миллисекунды

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public void applyDelay() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
