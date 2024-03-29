/*
 * 		SuspendedUsersProfilesService.java
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
 * 		SuspendedUsersProfilesService.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 14, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadUserGroup;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.SuspendedUsersProfile;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.SuspendedUsersProfilesRepository;

@Service
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class SuspendedUsersProfilesService {
	private SuspendedUsersProfilesRepository suspendedUsersProfilesRepository;

	/**
	 * @param suspendedUsersProfilesRepository
	 */
	public SuspendedUsersProfilesService(SuspendedUsersProfilesRepository suspendedUsersProfilesRepository) {
		super();
		this.suspendedUsersProfilesRepository = suspendedUsersProfilesRepository;
	}

	public boolean isSuspendedUsersProfile(RadUserGroup radUserGroup) {
		boolean result = false;
		if (radUserGroup.equals(getSuspendedUsersProfile().getRadUserGroup()))
			result = true;
		return result;
	}

	public RadUserGroup getSuspendedRadUserGroup() {
		return getSuspendedUsersProfile().getRadUserGroup();
	}

	private SuspendedUsersProfile getSuspendedUsersProfile() {
		List<SuspendedUsersProfile> suspendedUsersProfilesList = (List<SuspendedUsersProfile>) suspendedUsersProfilesRepository
				.findAll();
		return suspendedUsersProfilesList.get(0);
	}
}
