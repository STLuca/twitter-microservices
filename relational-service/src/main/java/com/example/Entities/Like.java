package com.example.Entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Likes")
public class Like {

    @Id
    @GeneratedValue
    private Long followID;

    private Timestamp createdDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tweet tweet;

}
