package com.dpco.business.config;

import com.dpco.business.exception.CustomException;
import com.dpco.business.security.JwtAuthenticationEntryPoint;
import com.dpco.business.security.JwtAuthenticationProvider;
import com.dpco.business.security.JwtAuthenticationTokenFilter;
import com.dpco.business.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public AuthenticationManager authenticationManager() {
        try {
            return new ProviderManager(Collections.singletonList(authenticationProvider));
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        try {
            JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
            filter.setAuthenticationManager(authenticationManager());
            filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
            return filter;
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        try {
            http.csrf().disable()
                    .authorizeRequests().antMatchers("**/member/**").authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(entryPoint)
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
            http.headers().cacheControl();
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }

    }
}
