package com.smuniov.addressbook.configuration;

import com.smuniov.addressbook.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/springfox-swagger-ui/**", "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.GET,"/address-book/persons/**").permitAll()//.hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
                .antMatchers(HttpMethod.POST, "/address-book/persons").permitAll()
                .antMatchers(HttpMethod.DELETE, "/address-book/persons/person/*")
                    .hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers(HttpMethod.PUT, "/address-book/persons/person/*")
                    .hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers(HttpMethod.POST, "/address-book/users").permitAll()
                .antMatchers(HttpMethod.GET, "/address-book/users").permitAll()
                .antMatchers(HttpMethod.GET, "/address-book/users/user/*")
                    .hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers(HttpMethod.PUT, "/address-book/users/user/*")
                    .hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers(HttpMethod.DELETE, "/address-book/users/user/*")
                    .hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
//                .sessionManagement()
//                .disable()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic()
        ;
    }

}
