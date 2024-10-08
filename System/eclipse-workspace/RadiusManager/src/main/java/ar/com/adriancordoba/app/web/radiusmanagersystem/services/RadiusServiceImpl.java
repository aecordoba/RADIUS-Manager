/*
 * 		RadiusServiceImpl.java
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
 * 		RadiusServiceImpl.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 14, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.services;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
public class RadiusServiceImpl implements RadiusService {
	private static final Logger log = LogManager.getLogger(RadiusServiceImpl.class);

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
	@Value("${accounting-paging.page-size}")
	private int pageSize;

	/**
	 * @param radCheckRepository
	 * @param radReplyRepository
	 * @param radUserGroupRepository
	 * @param nasRepository
	 * @param radAcctRepository
	 * @param suspendedUsersProfilesService
	 * @param systemCommandService
	 */
	public RadiusServiceImpl(RadCheckRepository radCheckRepository, RadReplyRepository radReplyRepository,
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService#
	 * configureClient(ar.com.adriancordoba.app.web.radiusmanagersystem.model.
	 * Client)
	 */
	@Override
	public void configureClient(Client client) {
		if (client.isSuspended())
			configureSuspendedClient(client);
		else
			configureRegularClient(client);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService#
	 * getRadUserGroupList()
	 */
	@Override
	public List<RadUserGroup> getRadUserGroupsList() {
		return (List<RadUserGroup>) radUserGroupRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService#
	 * getActiveRadUserGroupsList()
	 */
	@Override
	public List<RadUserGroup> getActiveRadUserGroupsList() {
		return (List<RadUserGroup>) radUserGroupRepository.getActiveRadUserGroupsList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService#
	 * deleteClient(ar.com.adriancordoba.app.web.radiusmanagersystem.model.
	 * Client)
	 */
	@Override
	public void deleteClient(Client client) {
		radCheckRepository.deleteByUserName(client.getName());
		radReplyRepository.deleteByUserName(client.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService#
	 * getClientAccountingPage(ar.com.adriancordoba.app.web.radiusmanagersystem.
	 * model.Client, int)
	 */
	@Override
	public Page<RadAcct> getClientAccountingPage(String userName, int pageNumber) {
		return getClientAccountingPage(userName, pageNumber, pageSize, "acctStartTime", Sort.Direction.DESC);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService#
	 * getClientAccountingPage(ar.com.adriancordoba.app.web.radiusmanagersystem.
	 * model.Client, int, int)
	 */
	@Override
	public Page<RadAcct> getClientAccountingPage(String userName, int pageNumber, int pageSize, String sortField,
			Sort.Direction sortDirection) {
		Order order = new Order(sortDirection, sortField);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order));
		return radAcctRepository.findByUserName(userName, pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService#
	 * getIPAddressSesions(java.lang.String, java.time.LocalDateTime,
	 * java.time.LocalDateTime)
	 */
	@Override
	public List<RadAcct> getIPAddressTraces(String ipAddress, LocalDateTime from, LocalDateTime to) {
		return (List<RadAcct>) radAcctRepository.findIPAddressInPeriod(ipAddress, from, to);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService#
	 * disconnect(ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client)
	 */
	@Override
	public void disconnect(Client client) {
		log.info("Trying to disconnect client '{}'.", client.getName());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService#
	 * applyRateLimit(ar.com.adriancordoba.app.web.radiusmanagersystem.model.
	 * Client)
	 */
	@Override
	public void applyRateLimit(Client client) {
		log.info("Trying to apply rate limit to client '{}'.", client.getName());
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
