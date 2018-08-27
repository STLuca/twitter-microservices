package com.example.demo.EventHandlers;

import com.example.demo.Domain.Tweet;
import lombok.Data;

@Data
public class SearchTweet {

    public Long id;
    public String message;

    public SearchTweet(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public static SearchTweet createFromTweet(Tweet tweet) {
        return new SearchTweet(tweet.getId(), tweet.getMessage());
    }


}
