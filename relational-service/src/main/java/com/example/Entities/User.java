package com.example.Entities;

import com.example.ValueObjects.Username;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "Users")
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @JsonIgnore
    @Column(unique = true)
    private String auth;

    private String profilePicUrl;

}