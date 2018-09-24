package com.example;

import com.example.Entities.User;
import com.example.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
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
            User user = userRepository.findByAuth((String) map.get("sub"));
            if (user == null) {
                return (String) map.get("sub");
            } else {
                return user;
            }
            // return map.get("sub");
        };
    }

    @Bean
    public EvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    //instead of hard typing 'api' here, should read the value from properties
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers(HttpMethod.PATCH, "/api/tweets/*").denyAll()
                .antMatchers(HttpMethod.PATCH, "/api/follows/*").denyAll()
                .antMatchers(HttpMethod.PATCH, "/api/likes/*").denyAll()
                .antMatchers("/**").authenticated();
    }

}