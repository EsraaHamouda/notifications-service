package com.notification.model.enums;

import java.util.Arrays;
import java.util.Optional;
public enum NotificationTypeEnum {

    Individual("individual"), Group("group");



    String type;
    NotificationTypeEnum(){}
    NotificationTypeEnum(String type) {
        this.type = type;
    }

    public static   NotificationTypeEnum  fromText(String text) {
        Optional<NotificationTypeEnum> result = Arrays.stream(values())
                .filter(bl -> bl.type.equalsIgnoreCase(text))
                .findFirst();
        return result.orElse(null);
    }

    public String getType() {
        return type;
    }

}
