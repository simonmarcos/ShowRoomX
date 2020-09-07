package com.SistemaGestion.ShowroomX.Config;

import com.SistemaGestion.ShowroomX.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userDetailsService;

    @Autowired
    public SecurityConfiguration(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/provider/**").hasRole("ADMIN")
                .antMatchers("/purchase/**").hasRole("ADMIN")
                .antMatchers("/client/**").hasRole("ADMIN")
                .antMatchers("/brand/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/sales/**").hasAnyRole("ADMIN", "USER")
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(jwtAuthorizationFilter())
                .formLogin();
    }

    /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("msimon")
                .password(this.bCryptPasswordEncoder().encode("simon"))
                .roles(ADMIN.name())
                .build();

        UserDetails userDetails1 = User.builder()
                .username("msola")
                .password(this.bCryptPasswordEncoder().encode("simon"))
                .roles(EMPLOYEE.name())
                .build();

        return new InMemoryUserDetailsManager(userDetails, userDetails1);
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(this.authenticationManager());
    }

}
