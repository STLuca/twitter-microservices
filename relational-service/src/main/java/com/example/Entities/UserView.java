package com.example.Entities;

import lombok.Value;

@Value
public class UserView {

    private Long id;
    private String username;
    private Long tweetCount;
    private Long followingCount;
    private Long followerCount;
    private boolean following;
    private boolean follower;

}
