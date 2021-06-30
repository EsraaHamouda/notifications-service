package com.notification.service;

import com.notification.dao.GroupRepository;
import com.notification.dao.NotificationTemplateRepository;
import com.notification.dao.UserRepository;
import com.notification.dto.GroupDto;
import com.notification.exception.CustomException;
import com.notification.exception.ErrorCode;
import com.notification.model.NotificationGroup;
import com.notification.model.NotificationTemplate;
import com.notification.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationManagementService {


    @Autowired
    NotificationTemplateRepository templateRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    public NotificationTemplate saveNotificationTemplate(NotificationTemplate notificationTemplate) {

        return templateRepository.save(notificationTemplate);
    }

    public NotificationGroup saveGroup(GroupDto groupDto) {

        NotificationGroup notificationGroup = new NotificationGroup();

        notificationGroup.setName(groupDto.getName());
        List<User> users = userRepository.findByUsersIn(groupDto.getUsers());
        users.forEach(user -> {
            user.getNotificationGroups().add(notificationGroup);
        });

        notificationGroup.setUsers(users);
        return groupRepository.save(notificationGroup);
    }

    public User saveUser(User user) {

        return userRepository.save(user);
    }


    public NotificationTemplate getTemplate(String type, String lang) {
        Optional<NotificationTemplate> result = templateRepository.findByLanguageAndName(lang, type);
        if (result.isPresent())
            return result.get();
        throw new CustomException(ErrorCode.NotTemplateFound);
    }
}
