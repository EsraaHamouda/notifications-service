package com.notification.model.enums;

import java.util.Arrays;
import java.util.Optional;
public enum NotificationProviderEnum {


    Push("push"), SMS("sms");


    String provider;

    NotificationProviderEnum(){}
    NotificationProviderEnum(String provider ){this.provider = provider;}

    public static   NotificationProviderEnum  fromText(String text) {
        Optional<NotificationProviderEnum> result = Arrays.stream(values())
                .filter(bl -> bl.provider.equalsIgnoreCase(text))
                .findFirst();
        return result.orElse(null);
    }

    public String getProvider() {
        return provider;
    }
}
