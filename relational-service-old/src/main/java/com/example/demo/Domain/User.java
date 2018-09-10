package com.example.demo.Domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String auth;

}
