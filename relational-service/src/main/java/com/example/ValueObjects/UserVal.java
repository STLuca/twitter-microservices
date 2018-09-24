package com.example.ValueObjects;

import lombok.Data;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class UserVal {

    private String username;
    private String profile_pic_url;
    private Long tweet_count;
    private Long following_count;
    private Long follower_count;
    private Boolean following;
    private Boolean follower;


}
