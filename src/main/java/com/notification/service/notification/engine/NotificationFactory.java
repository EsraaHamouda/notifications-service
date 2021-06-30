package com.notification.service.notification.engine;

import com.notification.exception.CustomException;
import com.notification.exception.ErrorCode;
import com.notification.model.enums.NotificationProviderEnum;

public class NotificationFactory {


    public static NotificationProvider getProviderInstance(NotificationProviderEnum providerEnum) {

        switch (providerEnum) {
            case SMS:
                return new SMSNotification();
            case Push:
                return new PushNotification();

            default:
                throw new CustomException(ErrorCode.ProviderNotSupported);
        }
    }
}
