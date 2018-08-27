package com.example.demo.Domain;

import lombok.Data;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@RelationshipEntity(type = "following")
@Data
public class Follow {

    @Id @GeneratedValue private Long followID;

    @CreatedDate
    @DateLong
    private LocalDate createdDate;

    @StartNode private User follower;
    @EndNode private User followee;

}
