package com.example.Entities;

public interface IUserView {

    Long getid();
    String getusername();
    Long gettweetCount();
    Long getfollowingCount();
    Long getfollowerCount();
    Boolean getfollowing();
    Boolean getfollower();

}
