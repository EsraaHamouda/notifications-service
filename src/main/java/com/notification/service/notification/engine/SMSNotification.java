package com.notification.service.notification.engine;

import com.notification.model.Notification;

import java.util.logging.Logger;

public class SMSNotification implements NotificationProvider {

    Logger logger =  Logger.getLogger(SMSNotification.class.getName());
    @Override
    public void sendToUser(Notification notification) {

        logger.info(notification.toString());

    }


}
