package com.notification.controller;

import com.notification.dto.GroupDto;
import com.notification.model.NotificationGroup;
import com.notification.model.NotificationTemplate;
import com.notification.model.User;
import com.notification.service.NotificationManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationManagementController {


    @Autowired
    NotificationManagementService notificationManagementService;

    @RequestMapping(value = "/template", method = RequestMethod.POST)
    public ResponseEntity<Object> createNotificationTemplate(@RequestBody NotificationTemplate notificationTemplate) {

        NotificationTemplate result = notificationManagementService.saveNotificationTemplate(notificationTemplate);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/template/{type}/{lang}", method = RequestMethod.GET)
    public ResponseEntity<NotificationTemplate> getNotificationMetaData(@PathVariable("type") String type, @PathVariable("lang") String lang) {

        NotificationTemplate result = notificationManagementService.getTemplate(type, lang);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User result = notificationManagementService.saveUser(user);

        return new ResponseEntity<User>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public ResponseEntity<NotificationGroup> createGroup(@RequestBody GroupDto groupDto) {


        NotificationGroup result = notificationManagementService.saveGroup(groupDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
