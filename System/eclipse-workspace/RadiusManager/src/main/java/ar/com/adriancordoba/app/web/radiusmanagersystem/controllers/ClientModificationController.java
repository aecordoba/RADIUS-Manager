/*
 * 		ClientModificationController.java
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
 * 		ClientModificationController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 4, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadUserGroup;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.ClientsRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadUserGroupRepository;

@Controller
@RequestMapping("/client-modification")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class ClientModificationController {
	private static final Logger log = LogManager.getLogger(ClientModificationController.class);

	private ClientsRepository clientsRepository;
	private RadUserGroupRepository radUserGroupRepository;

	/**
	 * @param clientsRepository
	 * @param radUserGroupRepository
	 */
	public ClientModificationController(ClientsRepository clientsRepository,
			RadUserGroupRepository radUserGroupRepository) {
		super();
		this.clientsRepository = clientsRepository;
		this.radUserGroupRepository = radUserGroupRepository;
	}

	@ModelAttribute(name = "client")
	public Client getClient() {
		return new Client();
	}

	@ModelAttribute(name = "radUserGroupList")
	public List<RadUserGroup> getRadUserGroupList() {
		return (List<RadUserGroup>) radUserGroupRepository.findAll();
	}

	@GetMapping
	public String clientModificationForm() {
		return "private/client-modification";
	}
}
