package com.example.demo.Domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.time.LocalDate;
import java.util.List;

@NodeEntity
@Data
public class Tweet {

    @Id @GeneratedValue private Long id;

    private String message;

    @Relationship(type = "tweetedBy")
    private User user;

    @Relationship(type = "repliedTo")
    private Tweet repliedTo;

    @DateLong
    private LocalDate createdDate;
}
