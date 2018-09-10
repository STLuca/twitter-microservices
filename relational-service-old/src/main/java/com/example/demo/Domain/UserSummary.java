package com.example.demo.Domain;

import lombok.Value;

@Value
public class UserSummary {

    private final User user;
    private final int numOfTweets;
    private final int numOfFollowers;
    private final int numOfFollowing;
    private final boolean following;
    private final boolean followed;

}
