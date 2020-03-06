package io.pivotal.pal.tracker.registration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@ConditionalOnProperty(value = "application.oauth-enabled", havingValue = "false")
public class NoOauthResourceServerConfig extends ResourceServerConfigurerAdapter {
	
    public NoOauthResourceServerConfig() {
		super();
		System.err.println("NoOauthResourceServerConfig CTR>>>>");
	}

	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .anyRequest().permitAll()
        .and()
        .csrf().disable();
    }
}
