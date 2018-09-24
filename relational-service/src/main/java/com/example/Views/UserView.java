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
@Table(name = "userview")
@Data
public class UserView {

    @Id
    private Long user_id;
    private UserVal userVal;

    @JsonIgnore
    private Long queryingUser;

}
