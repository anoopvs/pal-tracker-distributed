package io.pivotal.pal.tracker.registration;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableWebSecurity
@SpringBootApplication
@ComponentScan({
    "io.pivotal.pal.tracker.accounts",
    "io.pivotal.pal.tracker.restsupport",
    "io.pivotal.pal.tracker.projects",
    "io.pivotal.pal.tracker.users",
    "io.pivotal.pal.tracker.registration"
})
@EnableEurekaClient
@Configuration
public class App {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(App.class, args);
    }
    
    @Bean
    @ConditionalOnProperty(value = "application.oauth-enabled", havingValue = "false")
    public ResourceServerConfigurerAdapter resourceServerConfigurerAdapterTest(){
    	return new NoOauthResourceServerConfig();
    }
    
    @Bean
    @ConditionalOnProperty(value = "application.oauth-enabled", matchIfMissing = true)
    public ResourceServerConfigurerAdapter resourceServerConfigurerAdapterCloud(){
    	return new OauthResourceServerConfig();
    }
}
