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
