package com.example.demo.Domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource(excerptProjection = TweetExcerpt.class)
public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {

    @Override
    @PreAuthorize("#tweet?.user?.auth == authentication?.name")
    Tweet save(@Param("tweet") Tweet tweet);

    @Override
    @PreAuthorize("#tweet?.user?.auth == authentication?.name")
    void delete(@Param("tweet") Tweet tweet);

    @Query(
            "MATCH (tweets:Tweet)-[:tweetedBy]->(u:User)    \n" +
            "WHERE ID(u) = {userID}                         \n" +
            "RETURN tweets"
    )
    List<Tweet> findByUser(@Param("userID") Long userID, Pageable pageable);

    @Query(
            "MATCH (replies:Tweet)-[:repliedTo]->(tweet:User)   \n" +
            "WHERE ID(tweet) = {tweetID}                        \n" +
            "RETURN replies"
    )
    List<Tweet> getTweetReplies(@Param("tweetID") Long tweetID, Pageable pageable);


}
