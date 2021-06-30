package com.notification.dao;

import com.notification.model.NotificationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<NotificationGroup, Integer> {

    @Override
    List<NotificationGroup> findAll();
}