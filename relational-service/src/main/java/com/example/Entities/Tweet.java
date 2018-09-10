package com.example.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Tweets")
@SqlResultSetMapping(
        name = "tweetView",
        classes = {
                @ConstructorResult(
                        targetClass = TweetView.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "message", type = String.class),
                                @ColumnResult(name = "userid", type = Long.class),
                                @ColumnResult(name = "username", type = String.class),
                                @ColumnResult(name = "likeCount", type = Long.class),
                                @ColumnResult(name = "replyCount", type = Long.class),
                                @ColumnResult(name = "replyTo", type = Long.class),
                                @ColumnResult(name = "liked", type = Boolean.class)
                        }
                )
        }
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Tweet.getTweet",
                query = "SELECT * FROM getTweetView(:queryingUserID) t WHERE t.id = :tweetID",
                resultSetMapping = "tweetView"
        ),
        @NamedNativeQuery(
                name = "Tweet.getUsersTweets",
                query = "SELECT * FROM getUsersTweets(:queringUserID, :userID)",
                resultSetMapping = "tweetView"
        ),
        @NamedNativeQuery(
                name = "Tweet.getFeed",
                query = "SELECT * FROM getFeed(:userID, :queryingUserID)",
                resultSetMapping = "tweetView"
        )
})
@Data
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    private String message;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinTable(name = "Replies", joinColumns = @JoinColumn(name = "CHILD_ID"),
            inverseJoinColumns = @JoinColumn(name = "PARENT_ID"))
    private Tweet repliedTo;

    /*
    @CreatedDate
    private LocalDate createdDate;
    */

    public Tweet(Long id, String message, User user, Tweet repliedTo) {
        this.id = id;
        this.message = message;
        this.user = user;
        this.repliedTo = repliedTo;
    }
}
