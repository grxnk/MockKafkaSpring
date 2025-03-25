package com.example.mockkafkaspring.dto;

import java.util.List;

public class MultiDelayRequest {
    private List<DelayEntry> delays;

    public List<DelayEntry> getDelays() { return delays; }
    public void setDelays(List<DelayEntry> delays) { this.delays = delays; }

    public static class DelayEntry {
        private String endpoint;
        private int delay;

        public String getEndpoint() { return endpoint; }
        public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

        public int getDelay() { return delay; }
        public void setDelay(int delay) { this.delay = delay; }
    }
}
