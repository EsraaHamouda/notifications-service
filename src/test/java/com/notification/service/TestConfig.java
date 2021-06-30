package com.notification.service;

import com.notification.service.notification.engine.NotificationHandler;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration

public class TestConfig {
    @Bean
    public NotificationHandler notificationHandler() {
        final NotificationHandler notificationHandlerInstance = new NotificationHandler();
        return notificationHandlerInstance;
    }



}
