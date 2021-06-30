package com.notification.model.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public enum NotificationStatusEnum {

    Processed("processed"), Pending("pending");


    String status;


    public static   NotificationStatusEnum  fromText(String text) {
        Optional<NotificationStatusEnum> result = Arrays.stream(values())
                .filter(bl -> bl.status.equalsIgnoreCase(text))
                .findFirst();
        return result.orElse(null);
    }

    public String getStatus() {
        return status;
    }

}
