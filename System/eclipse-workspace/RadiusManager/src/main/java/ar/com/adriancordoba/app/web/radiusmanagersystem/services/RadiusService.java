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

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Nas;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadAcct;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadCheck;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadReply;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadUserGroup;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.NasRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadAcctRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadCheckRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadReplyRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadUserGroupRepository;

@Service
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class RadiusService {
	private static final Logger log = LogManager.getLogger(RadiusService.class);

	private RadCheckRepository radCheckRepository;
	private RadReplyRepository radReplyRepository;
	private RadUserGroupRepository radUserGroupRepository;
	private NasRepository nasRepository;
	private RadAcctRepository radAcctRepository;
	private SuspendedUsersProfilesService suspendedUsersProfilesService;
	private SystemCommandService systemCommandService;
	@Value("${nas.port}")
	private String nasPort;
	@Value("${suspended-users.rate-limit}")
	private String rateLimit;

	/**
	 * @param radCheckRepository
	 * @param radReplyRepository
	 * @param radUserGroupRepository
	 * @param nasRepository
	 * @param radAcctRepository
	 * @param suspendedUsersProfilesService
	 * @param systemCommandService
	 */
	public RadiusService(RadCheckRepository radCheckRepository, RadReplyRepository radReplyRepository,
			RadUserGroupRepository radUserGroupRepository, NasRepository nasRepository,
			RadAcctRepository radAcctRepository, SuspendedUsersProfilesService suspendedUsersProfilesService,
			SystemCommandService systemCommandService) {
		super();
		this.radCheckRepository = radCheckRepository;
		this.radReplyRepository = radReplyRepository;
		this.radUserGroupRepository = radUserGroupRepository;
		this.nasRepository = nasRepository;
		this.radAcctRepository = radAcctRepository;
		this.suspendedUsersProfilesService = suspendedUsersProfilesService;
		this.systemCommandService = systemCommandService;
	}

	public void configureClient(Client client) {
		if (client.isSuspended())
			configureSuspendedClient(client);
		else
			configureRegularClient(client);
	}

	public List<RadUserGroup> getRadUserGroupList() {
		return (List<RadUserGroup>) radUserGroupRepository.findAll();
	}

	public void deleteClient(Client client) {
		radCheckRepository.deleteByUserName(client.getName());
		radReplyRepository.deleteByUserName(client.getName());
	}

	public void disconnect(Client client) {
		for (RadAcct radAcct : getActiveRadAcct(client)) {
			Nas nas = getNas(radAcct.getNasIpAddress());
			boolean result = systemCommandService.disconnect(radAcct.getAcctSessionId(), radAcct.getUserName(),
					radAcct.getNasIpAddress(), nasPort, nas.getSecret());
			if (result)
				log.info("Client '{}' disconnected.", client.getName());
			else
				log.warn("Cannot disconnect client '{}'.", client.getName());
		}
	}

	public void applyRateLimit(Client client) {
		for (RadAcct radAcct : getActiveRadAcct(client)) {
			Nas nas = getNas(radAcct.getNasIpAddress());
			boolean result = systemCommandService.changeOfAuthorization(client.getName(), "Mikrotik-Rate-Limit",
					rateLimit, radAcct.getNasIpAddress(), nasPort, nas.getSecret());
			if (result)
				log.info("Rate limit {} applied to client '{}'.", rateLimit, client.getName());
			else
				log.warn("Cannot apply rate limit {} to client '{}'.", rateLimit, client.getName());
		}
	}

	private void configureRegularClient(Client client) {
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

	private void configureSuspendedClient(Client client) {
		RadCheck radCheck = new RadCheck(client.getName(), "Cleartext-Password", ":=", client.getPassword());
		radCheckRepository.save(radCheck);
		RadUserGroup suspendedRadUserGroup = suspendedUsersProfilesService.getSuspendedRadUserGroup();
		radCheck = new RadCheck(client.getName(), "User-Profile", ":=", suspendedRadUserGroup.getUserName());
		radCheckRepository.save(radCheck);
	}

	private List<RadAcct> getActiveRadAcct(Client client) {
		List<RadAcct> activeRadAcctList = (List<RadAcct>) radAcctRepository.findActiveRadAcct(client.getName());
		if (activeRadAcctList.isEmpty())
			log.info("No active session for client '{}'.", client.getName());
		return activeRadAcctList;
	}

	private Nas getNas(String name) {
		List<Nas> nasList = (List<Nas>) nasRepository.findByName(name);
		return nasList.get(0);
	}
}
