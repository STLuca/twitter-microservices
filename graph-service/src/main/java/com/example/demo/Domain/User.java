package com.example.demo.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.List;

@NodeEntity
@Data
public class User {

    @Id @GeneratedValue private Long id;

    @Property
    @Index(unique = true)
    private String username;

    @Index(unique = true)
    private String auth;

    /*
    @Relationship(type = "following")
    private transient List<Follow> following;

    @Relationship(type = "likes")
    private transient List<Like> likes;

    @JsonIgnore
    @Relationship(type = "tweetedBy", direction = Relationship.INCOMING)
    private transient List<Tweet> tweets;
    */

}
