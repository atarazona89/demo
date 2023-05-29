package com.example.demo.Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "phone")
@Data
public class PhoneEntity implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private UserEntity user;

    @Column
    private Long number;

    @Column
    private Integer citycode;

    @Column
    private String countrycode;
}
