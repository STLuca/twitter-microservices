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
@Table(name = "followerview")
@Data
public class FollowerView {

    @Id
    private Long user_id;
    private UserVal userVal;
    private Long followed_date;

    @JsonIgnore
    private Long querying_user;
    @JsonIgnore
    private Long followinguser;

}
