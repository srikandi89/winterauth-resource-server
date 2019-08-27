package com.srikandi.winterauth.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource-server-rest-api";

    @Bean
    public ResourceServerTokenServices tokenService() {
        CustomRemoteTokenService tokenServices = new CustomRemoteTokenService();
        return tokenServices;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(tokenService());
        return authenticationManager;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).authenticationManager(authenticationManagerBean())
                .tokenExtractor(new CustomTokenExtractor());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable().anonymous().and()
                .authorizeRequests()
                .antMatchers("/private/**").authenticated()
                .antMatchers("/public/**").permitAll();
    }
}
