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

    @HandleBeforeCreate
    public void handleUserSave(User user) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        user.setAuth(name);
        user.setProfilePicUrl("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user.png");
    }

}