package com.example.EventHandlers;

import com.example.Entities.Follow;
import com.example.Entities.Like;
import com.example.Entities.Tweet;
import com.example.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RepositoryEventHandler
public class EventHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventHandlers.class);

    @HandleBeforeCreate
    public void handleUserSave(User user) {
        LOGGER.info(SecurityContextHolder.getContext().getAuthentication().toString());
        LOGGER.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        LOGGER.info(SecurityContextHolder.getContext().getAuthentication().getName());
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        user.setAuth(name);
        user.setProfilePicUrl("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user.png");
    }

    @HandleBeforeCreate
    public void handleTweetSave(Tweet tweet) {
        tweet.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
    }

    @HandleBeforeCreate
    public void handleFollowSave(Follow follow) {
        follow.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
    }

    @HandleBeforeCreate
    public void handleLikeSave(Like like) {
        like.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
    }

}