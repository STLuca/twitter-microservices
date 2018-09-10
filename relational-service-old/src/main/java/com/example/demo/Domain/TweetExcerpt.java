package com.example.demo.Domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "tweetSummary", types = { Tweet.class })
public interface TweetExcerpt {

    String getMessage();

    @Value("#{target.user.username}")
    String getUsername();

}
