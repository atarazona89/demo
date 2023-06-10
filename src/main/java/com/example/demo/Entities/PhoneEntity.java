package com.example.demo.Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "phone")
@Data
public class PhoneEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    private Integer cityCode;

    private String countryCode;

}
