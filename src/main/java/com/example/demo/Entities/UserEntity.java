package com.example.demo.Entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue/*(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )*/
    private  UUID id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    @CreationTimestamp
    private Date create;

    @Column(name = "last_login")
    @UpdateTimestamp
    private Date lastLogin;

    @Column
    private Boolean isActive;
}
