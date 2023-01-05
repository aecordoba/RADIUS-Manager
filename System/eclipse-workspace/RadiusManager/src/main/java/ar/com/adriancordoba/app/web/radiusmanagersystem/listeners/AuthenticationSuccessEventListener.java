/*
 * 		AuthenticationSuccessEventListener.java
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
 * 		AuthenticationSuccessListener.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 4, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.User;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.LoginAttemptsService;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
	private static final Logger log = LogManager.getLogger(AuthenticationSuccessEventListener.class);
	@Autowired
	private LoginAttemptsService loginAttemptsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.
	 * springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		User user = (User) event.getAuthentication().getPrincipal();
		loginAttemptsService.loginSuccess(user.getUsername());
		log.info("User '{}' logged in.", user.getUsername());
	}

}
