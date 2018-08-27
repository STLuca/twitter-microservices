package com.example.demo.Domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
public interface FollowRepository extends PagingAndSortingRepository<Follow, Long> {

    @Override
    @PreAuthorize("#follow?.follower?.auth == authentication?.name")
    Follow save(@Param("follow") Follow follow);

    @Override
    @PreAuthorize("#follow?.follower?.auth == authentication?.name")
    void delete(@Param("follow") Follow follow);

}
