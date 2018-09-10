package com.example.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Users")
@SqlResultSetMapping(
        name = "userView",
        classes = {
                @ConstructorResult(
                        targetClass = UserView.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "username", type = String.class),
                                @ColumnResult(name = "tweetCount", type = Long.class),
                                @ColumnResult(name = "followingCount", type = Long.class),
                                @ColumnResult(name = "followerCount", type = Long.class),
                                @ColumnResult(name = "following", type = Boolean.class),
                                @ColumnResult(name = "follower", type = Boolean.class)
                        }
                )
        }
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "User.getUser",
                query = "SELECT * FROM getUserView(:queryingUserID) u WHERE u.id = :userID",
                resultSetMapping = "userView"
        ),
        @NamedNativeQuery(
                name = "User.findAllUserViews",
                query = "SELECT * FROM getUserView(:queryingUserID)",
                resultSetMapping = "userView"
        ),
        @NamedNativeQuery(
                name = "User.getFollowingUsers",
                query = "SELECT * FROM getFollowingUsers(:userID, :queryingUserID)",
                resultSetMapping = "userView"
        ),
        @NamedNativeQuery(
                name = "User.getFollowedUsers",
                query = "SELECT * FROM getFollowedUsers(:userID, :queryingUserID)",
                resultSetMapping = "userView"
        ),
        @NamedNativeQuery(
                name = "User.getTweetLikes",
                query = "SELECT * FROM getTweetLikes(:tweetID, :queryingUserID)",
                resultSetMapping = "userView"
        )
})
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @JsonIgnore
    private String auth;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    //lombok not working
    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}



/*
@SqlResultSetMapping(
        name = "mapToUserView",
        columns = {
            @ColumnResult(name = "id", type = Long.class),
            @ColumnResult(name = "username", type = String.class),
            @ColumnResult(name = "tweetCount", type = Long.class),
            @ColumnResult(name = "followingCount", type = Long.class),
            @ColumnResult(name = "followerCount", type = Long.class),
            @ColumnResult(name = "following", type = Boolean.class),
            @ColumnResult(name = "follower", type = Boolean.class)
        }
)
*/