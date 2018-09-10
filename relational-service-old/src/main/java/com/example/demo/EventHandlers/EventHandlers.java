package com.example.demo.EventHandlers;

import com.example.demo.Domain.Follow;
import com.example.demo.Domain.Like;
import com.example.demo.Domain.Tweet;
import com.example.demo.Domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@RepositoryEventHandler
public class EventHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventHandlers.class);

    private RestTemplate restTemplate;

    public EventHandlers(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HandleBeforeCreate
    public void handleUserSave(User user) {
        LOGGER.info(SecurityContextHolder.getContext().getAuthentication().toString());
        LOGGER.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        user.setAuth(name);
    }

    @HandleBeforeCreate
    public void handleFollowSave(Follow follow) {
        follow.setCreatedDate(LocalDate.now());
    }

    @HandleBeforeCreate
    public void handleLikeSave(Like like) {
        like.setCreatedDate(LocalDate.now());
    }

    @HandleBeforeCreate
    public void handleTweetSave(Tweet tweet) {
        tweet.setCreatedDate(LocalDate.now());
    }
}
