package com.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String phone;


    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    private List<NotificationGroup> notificationGroups = new ArrayList<>();




}
