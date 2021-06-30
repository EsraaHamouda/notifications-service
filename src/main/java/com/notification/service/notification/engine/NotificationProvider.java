package com.notification.service.notification.engine;

import com.notification.model.Notification;

public interface NotificationProvider {

    void sendToUser(Notification notification);

}
