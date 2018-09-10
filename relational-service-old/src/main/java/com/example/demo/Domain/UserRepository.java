package com.example.demo.Domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(excerptProjection = UserExcerpt.class)
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

    @Override
    @PreAuthorize("#user?.auth == authentication?.name")
    User save(@Param("user") User user);

    @Override
    @PreAuthorize("#user?.auth == authentication?.name")
    void delete(@Param("user") User user);

}
