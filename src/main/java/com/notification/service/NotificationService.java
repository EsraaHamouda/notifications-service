package com.notification.service;

import com.notification.dto.NotificationDto;
import com.notification.model.Notification;
import com.notification.service.notification.engine.NotificationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {


    @Autowired
    NotificationHandler notificationHandler;


    public List<Notification> sendNotification(NotificationDto notificationDto) {

        return notificationHandler.processNotification(notificationDto);

    }
}
