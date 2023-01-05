/*
 * 		CustomUserDetailsService.java
 *   Copyright (C) 2023  Adrián E. Córdoba [software.asia@gmail.com]
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
 * 		CustomUserDetailsService.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 4, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.User;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.UsersRepository;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger log = LogManager.getLogger(CustomUserDetailsService.class);
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private LoginAttemptsService loginAttemptsService;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	if(loginAttemptsService.isBlocked(username)) {
    		log.warn("User '{}' is Locked.", username);
    		throw new LockedException("User '" + username + "' is Locked");
    	}
		User user = usersRepository.findByName(username);
		if(user == null) {
			log.warn("User '{}' not found", username);
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}
		return user;
	}
}
