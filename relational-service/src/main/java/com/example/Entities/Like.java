package com.example.Entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Likes")
public class Like {

    @Id
    @GeneratedValue
    private Long followID;

    @CreatedDate
    private LocalDate createdDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tweet tweet;

}
