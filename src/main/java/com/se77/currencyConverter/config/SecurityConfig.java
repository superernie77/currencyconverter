package com.se77.currencyConverter.config;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security config for login/logout and user/password/role definition
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String usersQuery = "select u.email as principal , u.password as credentials, true from userdata u where u.email=?";

	private static final String rolesQuery = "select u.email, r.role from userdata u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?";

	// These three Beans are needed for the custom Authentication Manager
	
	// 1. Bean to get the Userdetails
	@Bean
	public UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.setUsersByUsernameQuery(usersQuery);
		users.setAuthoritiesByUsernameQuery(rolesQuery);

		return users;
	}

	// 2. Bean to decrypt Password
	@Bean
    public PasswordEncoder  passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
	}
	
	// Authentication manager to wire the two above together
	@Bean
	public AuthenticationManager authenticationManager(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}
	
	 

	// Specify where authentication + roles are needed
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// Specify URLs
		http.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/").permitAll()
				.requestMatchers("/registration").permitAll()
				.requestMatchers("/converter").hasAuthority("USER")
				)
		// configure form login in
		.formLogin(form -> form
				.loginPage("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/converter")
				.permitAll()
			);

		return http.build();
	}

	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**",
				"/images/**");
	}

}