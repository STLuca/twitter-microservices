package com.example.Entities;

import lombok.Value;

@Value
public class TweetView {

    private Long id;
    private String message;
    private Long userid;
    private String username;
    private Long likeCount;
    private Long replyCount;
    private Long replyTo;
    private Boolean liked;

}
