package com.se77.currencyConverter.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security config for login/logout and user/password/role definition
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String usersQuery = "select email as principal , password as credentials, true from user where email=?";

    private static final String rolesQuery = "select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?";

    
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
       
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery(usersQuery);
        users.setAuthoritiesByUsernameQuery(rolesQuery);
        
        return users;
    }
    
     
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	   http.
           authorizeRequests()
           .requestMatchers("/").permitAll()
           .requestMatchers("/login").permitAll()
           .requestMatchers("/registration").permitAll()
           .requestMatchers("/converter").hasAuthority("USER").anyRequest()
           .authenticated().and().csrf().disable().formLogin()
           .loginPage("/login").failureUrl("/login?error=true")
           .defaultSuccessUrl("/converter")
           .usernameParameter("email")
           .passwordParameter("password")
           .and().logout()
           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
           .logoutSuccessUrl("/").and().exceptionHandling()
           .accessDeniedPage("/access-denied");

        return http.build();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
        .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}