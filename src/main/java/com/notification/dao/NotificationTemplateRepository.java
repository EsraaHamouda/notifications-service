package com.notification.dao;

import com.notification.model.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Long> {

    // @Query( "select nt from NotificationTemplate nt where     nt.language = ?1 AND nt.name = ?2 " )
    Optional<NotificationTemplate> findByLanguageAndName( String language, String name);
 }