package com.example;

import com.example.Entities.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfiguration.class);


    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            LOGGER.info(map.toString());
            return userRepository.findByAuth((String) map.get("sub"));
            // return map.get("sub");
        };
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.PATCH, "/tweets/*").denyAll()
                .antMatchers(HttpMethod.PATCH, "/follows/*").denyAll()
                .antMatchers(HttpMethod.PATCH, "/likes/*").denyAll()
                .antMatchers("/**").authenticated();
    }

}