package com.example;

import com.example.EventHandlers.EventHandlers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableResourceServer
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class RelationalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationalServiceApplication.class, args);
	}

	@Bean
	EventHandlers userEventHandler() {
		return new EventHandlers();
	}

	/*
	@Bean
	public CommandLineRunner loadData(
			UserRepository userRepository,
			TweetRepository tweetRepository,
            FollowRepository followRepository
	) {
		return args -> {
			// userRepository.save(new User(1, "bob"));
			User bob = new User(new Long(1), "bob");
			User sally = new User(new Long(2), "sally");
			Tweet bobTweet1 = new Tweet(new Long(3), "hi", bob, null);
			Tweet bobTweet2 = new Tweet(new Long(4), "hey me", bob, bobTweet1);
            Follow bobFollowSally = new Follow(new Long(5), bob, sally);
            Follow sallyFollowBob = new Follow(new Long(6), sally, bob);
            Follow bobFollowBob = new Follow(new Long(7), bob, bob);

			userRepository.save(bob);
			userRepository.save(sally);
			tweetRepository.save(bobTweet1);
			tweetRepository.save(bobTweet2);
			followRepository.save(bobFollowSally);
			followRepository.save(sallyFollowBob);
			// followRepository.save(bobFollowBob);
		};
	}
	*/
}
