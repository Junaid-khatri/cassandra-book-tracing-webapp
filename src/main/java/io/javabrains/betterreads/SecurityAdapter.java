package io.javabrains.betterreads;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityAdapter {

	/**
	 * Spring Security configuration that sets up OAuth login and enables all URLs
	 * to be accessible without authentication. Also sets up CSRF protection.
	 */
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(requests -> requests.anyRequest().permitAll()
				)
				.exceptionHandling(e -> e
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				)
				.csrf(c -> c
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				)
				.logout(l -> l
						.logoutSuccessUrl("/").permitAll()
				)
				.oauth2Login()
				.and().build();
	}


}
