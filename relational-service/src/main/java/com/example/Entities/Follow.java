package com.example.Entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Follows")
public class Follow {

    @Id
    @GeneratedValue
    private Long followID;

    private Timestamp createdDate;

    @ManyToOne
    private User follower;

    @ManyToOne
    private User followee;

    public Follow(Long followID, User follower, User followee) {
        this.followID = followID;
        this.follower = follower;
        this.followee = followee;
    }
}
