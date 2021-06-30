package com.notification.controller;

import com.notification.dto.NotificationDto;
import com.notification.dto.NotificationLiteDto;
import com.notification.mapper.NotificationMapper;
import com.notification.model.Notification;
import com.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    NotificationMapper mapper;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/send_notification", method = RequestMethod.POST)
    public ResponseEntity<List<NotificationLiteDto>> sendNotification(@RequestBody NotificationDto notificationDto) {

        List<Notification> result = notificationService.sendNotification(notificationDto);

        return new ResponseEntity<>(mapper.mapList(result), HttpStatus.CREATED);
    }


}
