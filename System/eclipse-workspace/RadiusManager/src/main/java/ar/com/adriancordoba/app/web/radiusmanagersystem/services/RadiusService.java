/*
 * 		RadiusService.java
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
 * 		RadiusService.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 14, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.services;

import org.springframework.stereotype.Service;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadCheck;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadReply;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadUserGroup;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadCheckRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadReplyRepository;

@Service
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class RadiusService {
	private RadCheckRepository radCheckRepository;
	private RadReplyRepository radReplyRepository;
	private SuspendedUsersProfilesService suspendedUsersProfilesService;

	/**
	 * @param radCheckRepository
	 * @param radReplyRepository
	 * @param suspendedUsersProfilesService
	 */
	public RadiusService(RadCheckRepository radCheckRepository, RadReplyRepository radReplyRepository,
			SuspendedUsersProfilesService suspendedUsersProfilesService) {
		super();
		this.radCheckRepository = radCheckRepository;
		this.radReplyRepository = radReplyRepository;
		this.suspendedUsersProfilesService = suspendedUsersProfilesService;
	}

	public void configureClient(Client client) {
		RadCheck radCheck = new RadCheck(client.getName(), "Cleartext-Password", ":=", client.getPassword());
		radCheckRepository.save(radCheck);
		if (client.getIpAddress() != null) {
			RadReply radReply = new RadReply(client.getName(), "Framed-IP-Address", ":=",
					client.getIpAddress());
			radReplyRepository.save(radReply);
		} else if (client.getRadUserGroup() != null) {
			radCheck = new RadCheck(client.getName(), "User-Profile", ":=",
					client.getRadUserGroup().getUserName());
			radCheckRepository.save(radCheck);
		}
	}

	public void configureSuspendedClient(Client client) {
		RadCheck radCheck = new RadCheck(client.getName(), "Cleartext-Password", ":=", client.getPassword());
		radCheckRepository.save(radCheck);
		RadUserGroup suspendedRadUserGroup = suspendedUsersProfilesService.getSuspendedRadUserGroup();
		radCheck = new RadCheck(client.getName(), "User-Profile", ":=", suspendedRadUserGroup.getUserName());
		radCheckRepository.save(radCheck);
	}
}
