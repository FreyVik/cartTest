package com.gonzalo.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CartCleanupSchedule {

    Logger log = LoggerFactory.getLogger(CartCleanupSchedule.class);

    private final ConcurrentHashMap<String, Object> sessionData = new ConcurrentHashMap<>();

    public void addSessionAttribute(String attributeName, Object attributeValue) {
        if (sessionData.containsKey(attributeName)) {
            sessionData.replace(attributeName, attributeValue);
        } else {
            sessionData.put(attributeName, attributeValue);
        }
    }

    public Object getSessionAttribute(String attributeName) {
        return sessionData.get(attributeName);
    }

    @Scheduled(fixedRate = 600000)
    public void cleanupCart() {
        Date lastCardUpdate = (sessionData.containsKey("lastCardUpdate")) ? (Date) sessionData.get("lastCardUpdate") : new Date();

        if (new Date().getTime() - lastCardUpdate.getTime() > 10000) {
            String attributeName = "cart";

            if (sessionData.containsKey(attributeName)) {
                sessionData.remove(attributeName);
            }
        }
    }
}