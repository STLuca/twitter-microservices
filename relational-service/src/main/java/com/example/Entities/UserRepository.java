package com.example.Entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Override
    //@PreAuthorize("#user?.auth == authentication?.name")
    User save(@Param("user") User user);

    @Override
    @PreAuthorize("#user?.id == principal?.id")
    void delete(@Param("user") User user);

    // maybe change to Optional<User>
    @RestResource(exported = false)
    User findByAuth(String auth);

    @Query(nativeQuery = true)
    UserView getUser(@Param("userID") Long userID);

    @Query(nativeQuery = true)
    List<UserView> findAllUserViews(Pageable pageable);

    @Query(nativeQuery = true)
    List<UserView> getFollowingUsers(@Param("userID") Long userID, Pageable pageable);

    @Query(nativeQuery = true)
    List<UserView> getFollowedUsers(@Param("userID") Long userID, Pageable pageable);
}
