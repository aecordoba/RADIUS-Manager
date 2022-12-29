/*
 * 		LogoutSuccessHandler.java
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
 * 		LogoutSuccessHandler.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Dec 29, 2022
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.configurators;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	private static final Logger log = LogManager.getLogger(LogoutSuccessHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String name = authentication.getName();
		log.info("User {} logged out.", name);
		super.onLogoutSuccess(request, response, authentication);
	}
}
