/*
 * 		AuthorityServiceImpl.java
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
 * 		AuthorityServiceImpl.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 17, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Authority;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.AuthoritiesRepository;

@Service
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class AuthorityServiceImpl implements AuthorityService {
	private AuthoritiesRepository authoritiesRepository;

	/**
	 * @param authoritiesRepository
	 */
	public AuthorityServiceImpl(AuthoritiesRepository authoritiesRepository) {
		super();
		this.authoritiesRepository = authoritiesRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.adriancordoba.app.web.radiusmanagersystem.services.
	 * AuthorityService#getAuthoritiesList()
	 */
	@Override
	public List<Authority> getAuthoritiesList() {
		List<Authority> authorities = new ArrayList<>();
		authorities = (List<Authority>) authoritiesRepository.findAll();
		return authorities;
	}
}
