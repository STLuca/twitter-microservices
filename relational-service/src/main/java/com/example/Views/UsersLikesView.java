package com.example.Views;

import com.example.ValueObjects.TweetVal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "userslikesview")
@Data
public class UsersLikesView {

    @Id
    private Long tweet_id;
    private TweetVal tweetVal;
    private Long liked_date;

    @JsonIgnore
    private Long querying_user;

    @JsonIgnore
    private Long likedBy;

}
