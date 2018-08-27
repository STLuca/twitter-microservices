package com.example.imageservice;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ImageRepository extends ReactiveCrudRepository<Image, String> {

    Flux<Image> findByAuthID(String authID);

}
