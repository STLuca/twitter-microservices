package com.example.demo.Domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(excerptProjection = TweetExcerpt.class)
public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {

    @Override
    @PreAuthorize("#tweet?.user?.auth == authentication?.name")
    Tweet save(@Param("tweet") Tweet tweet);

    @Override
    @PreAuthorize("#tweet?.user?.auth == authentication?.name")
    void delete(@Param("tweet") Tweet tweet);


}
