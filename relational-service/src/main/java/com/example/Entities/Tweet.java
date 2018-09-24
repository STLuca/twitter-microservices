package com.example.Entities;

import com.example.ValueObjects.TweetMessage;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "Tweets")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    private TweetMessage message;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinTable(name = "Replies", joinColumns = @JoinColumn(name = "CHILD_ID"),
            inverseJoinColumns = @JoinColumn(name = "PARENT_ID"))
    private Tweet repliedTo;

    @CreatedDate
    private Long createdDate;

}
