package com.example.demo.Domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    private String message;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tweet repliedTo;

    @CreatedDate
    private LocalDate createdDate;
}
