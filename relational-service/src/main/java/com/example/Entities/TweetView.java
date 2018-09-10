package com.example.Entities;

import lombok.Value;

import java.util.Date;

@Value
public class TweetView {

    private Long id;
    private String message;
    private Long userid;
    private String username;
    private Date createdDate;
    private Long likeCount;
    private Long replyCount;
    private Long replyTo;
    private Boolean liked;

}
