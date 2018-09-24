package com.example.ValueObjects;

import lombok.Data;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class TweetVal {

    private String message;
    private Long userid;
    private String username;
    private Long tweet_timestamp;
    private Long likecount;
    private Long replycount;
    private Long replyto;
    private Boolean liked;

}
