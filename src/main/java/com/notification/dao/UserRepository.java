package com.notification.dao;

import com.notification.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query( "select u from User u where u.id in :ids" )
    List<User> findByUsersIn(@Param("ids") List<Integer> userList);

}