package com.notification.service.notification.engine;

import com.notification.model.Notification;

import java.util.logging.Logger;

public class PushNotification implements NotificationProvider {

    Logger logger =  Logger.getLogger(PushNotification.class.getName());

    @Override
    public void sendToUser(Notification notification) {
        logger.info(notification.toString());
    }
}
