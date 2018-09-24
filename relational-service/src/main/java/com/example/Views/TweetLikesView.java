package com.example.Views;

import com.example.ValueObjects.UserVal;
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
public class TweetLikesView {

    @Id
    private Long user_id;
    private UserVal userVal;
    private Long liked_date;

    @JsonIgnore
    private Long querying_user;

    @JsonIgnore
    private Long likedtweet;

}
