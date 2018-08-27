package com.example.demo.Domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(excerptProjection = UserExcerpt.class)
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

    @Override
    @PreAuthorize("#user?.auth == authentication?.name")
    User save(@Param("user") User user);

    @Override
    @PreAuthorize("#user?.auth == authentication?.name")
    void delete(@Param("user") User user);

    @Query( "MATCH (f:User)-[:following]->(u:User) " +
            "WHERE ID(u) = {userID} " +
            "RETURN f")
    List<User> findFollowersByID(@Param("userID") Long userID, Pageable pageable);

    @Query( "MATCH (f:User)-[:following]->(u:User) " +
            "WHERE u.username = {username} " +
            "RETURN f")
    List<User> findFollowers(@Param("username") String username, Pageable pageable);

    @Query( "MATCH (u:User)-[:following]->(f:User) " +
            "WHERE ID(u) = {userID} " +
            "RETURN f")
    List<User> findFollowingByID(@Param("userID") Long userID, Pageable pageable);

    @Query( "MATCH (u:User)-[:following]->(f:User) " +
            "WHERE u.username = {username} " +
            "RETURN f")
    List<User> findFollowing(@Param("username") String username, Pageable pageable);
}
