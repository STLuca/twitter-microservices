package com.example.demo.Domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userSummary", types = { User.class })
public interface UserExcerpt {

    String getUsername();

    /*
    @Value("#{target.getFollowing().size()}")
    int getFollowing();
    */

}
