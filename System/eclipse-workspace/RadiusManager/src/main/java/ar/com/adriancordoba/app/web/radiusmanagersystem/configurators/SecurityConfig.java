/*
 * 		SecurityConfig.java
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ar.com.adriancordoba.app.web.radiusmanagersystem.data.UsersRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.domain.User;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Configuration
public class SecurityConfig {
	private static final Logger log = LogManager.getLogger(SecurityConfig.class);

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(UsersRepository usersRepository) {
		return username -> {
			User user = usersRepository.findByName(username);
			if (user != null) {
				log.info("User {} logged in.", user.getName());
				return user;
			}
			throw new UsernameNotFoundException("User '" + username + "' not found");
		};
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeRequests().antMatchers("/add-user").hasAuthority("ADMIN").antMatchers("/add-task").hasAnyAuthority("ADMIN", "USER").antMatchers("/running-jobs").hasAnyAuthority("ADMIN", "USER", "OBSERVER").antMatchers("/", "/**").permitAll().and().formLogin().loginPage("/login").usernameParameter("name").passwordParameter("password").and().logout().logoutSuccessUrl("/").and().build();
	}
}
