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
                query = "SELECT * FROM getUserView(?#{principal.id}) u WHERE u.id = :userID",
                resultSetMapping = "userView"
        ),
        @NamedNativeQuery(
                name = "User.findAllUserViews",
                query = "SELECT * FROM getUserView(?#{principal.id})",
                resultSetMapping = "userView"
        ),
        @NamedNativeQuery(
                name = "User.getFollowingUsers",
                query = "SELECT * FROM getFollowingUsers(:userID, ?#{principal.id})",
                resultSetMapping = "userView"
        ),
        @NamedNativeQuery(
                name = "User.getFollowedUsers",
                query = "SELECT * FROM getFollowedUsers(:userID, ?#{principal.id})",
                resultSetMapping = "userView"
        ),
        @NamedNativeQuery(
                name = "User.getTweetLikes",
                query = "SELECT * FROM getTweetLikes(:tweetID, ?#{principal.id})",
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
    @Column(unique = true)
    private String auth;

    private String profilePicUrl;
}