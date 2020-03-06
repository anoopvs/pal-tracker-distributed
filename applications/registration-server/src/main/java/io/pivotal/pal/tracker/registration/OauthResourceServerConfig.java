package io.pivotal.pal.tracker.registration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
@ConditionalOnProperty(value = "application.oauth-enabled", matchIfMissing = true)
public class OauthResourceServerConfig extends WebSecurityConfigurerAdapter {

    public OauthResourceServerConfig() {
		super();
		System.err.println("OauthResourceServerConfig CTR>>>>");
	}
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // enforce authentication on our API endpoints.
        http.authorizeRequests().anyRequest().authenticated()
        .and().oauth2Login();
        http.exceptionHandling()
        .authenticationEntryPoint(new MyAuthenticationEntryPoint());

  }

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        // do not require a resource id in AccessToken.
//        resources.resourceId(null);
//    }
}