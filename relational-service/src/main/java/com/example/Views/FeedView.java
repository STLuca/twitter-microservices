package com.example.Views;

import com.example.ValueObjects.TweetVal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "feedview")
@Data
public class FeedView {

    @Id
    private Long tweet_id;
    private TweetVal tweetVal;

    @JsonIgnore
    private Long querying_user;

    @JsonIgnore
    private Long followed_by;

}