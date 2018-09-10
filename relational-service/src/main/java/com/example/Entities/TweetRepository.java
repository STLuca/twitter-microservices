package com.example.Entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource
public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {

    @Override
    @PreAuthorize("#tweet?.user?.id == principal?.id")
    Tweet save(@Param("tweet") Tweet tweet);

    @Override
    @PreAuthorize("#tweet?.user?.id == principal?.id")
    void delete(@Param("tweet") Tweet tweet);

    TweetView getTweet(@Param("tweetID") Long tweetID, @Param("queryingUserID") Long queryingUserID);

    List<TweetView> getUsersTweets(@Param("tweetID") Long tweetID, @Param("queryingUserID") Long queryingUserID);

    List<TweetView> getFeed(@Param("tweetID") Long tweetID, @Param("queryingUserID") Long queryingUserID);



}
