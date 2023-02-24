/*
 * 		SecurityConfiguration.java
 *   Copyright (C) 2022  Adrián E. Córdoba [software.asia@gmail.com]
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 		SecurityConfig.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 8, 2022
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.configurators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ar.com.adriancordoba.app.web.radiusmanagersystem.handlers.CustomAuthenticationFailureHandler;
import ar.com.adriancordoba.app.web.radiusmanagersystem.handlers.CustomLogoutSuccessHandler;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Configuration
public class SecurityConfiguration {
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.requiresChannel().anyRequest().requiresSecure().and().authorizeRequests()
				.antMatchers("/client-search", "/client-accounting", "/client-accounting-list")
				.hasAnyAuthority("ADMIN", "USER", "OBSERVER")
				.antMatchers("/client-creation", "/client-status", "/client-modification", "/client-deletion")
				.hasAnyAuthority("ADMIN", "USER")
				.antMatchers("/user-register").hasAuthority("ADMIN")
				.antMatchers("/", "/**").permitAll()
				.and().formLogin().loginPage("/login").usernameParameter("user").passwordParameter("password")
				.failureHandler(customAuthenticationFailureHandler).and().logout()
				.logoutSuccessHandler(customLogoutSuccessHandler).logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/").and().build();
	}
}
