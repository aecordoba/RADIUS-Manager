/*
 * 		ClientCreationController.java
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
 * 		ClientCreationController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 25, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Nas;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadUserGroup;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.ClientsRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.NasRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadUserGroupRepository;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Controller
@RequestMapping("/client-creation")
public class ClientCreationController {
	private static final Logger log = LogManager.getLogger(ClientCreationController.class);

	private ClientsRepository clientsRepository;
	private NasRepository nasRepository;
	private RadUserGroupRepository radUserGroupRepository;

	/**
	 * @param clientsRepository
	 * @param nasRepository
	 * @param radUserGroupRepository
	 */
	public ClientCreationController(ClientsRepository clientsRepository, NasRepository nasRepository,
			RadUserGroupRepository radUserGroupRepository) {
		super();
		this.clientsRepository = clientsRepository;
		this.nasRepository = nasRepository;
		this.radUserGroupRepository = radUserGroupRepository;
	}

	@ModelAttribute(name = "client")
	public Client getClient() {
		return new Client();
	}

	@ModelAttribute(name = "nasList")
	public List<Nas> getNasList() {
		return (List<Nas>) nasRepository.findAll();
	}

	@ModelAttribute(name = "radUserGroupList")
	public List<RadUserGroup> getRadUserGroupList() {
		return (List<RadUserGroup>) radUserGroupRepository.findAll();
	}

	@GetMapping
	public String userRegisterForm() {
		return "private/client-creation";
	}

	@PostMapping
	public String processUserRegister(@Valid Client client, Errors errors) {
		if (errors.hasErrors())
			return "private/client-creation";
		else {
			clientsRepository.save(client);
			log.info("Client '{}' created.", client.getName());
		}
		return "redirect:/";
	}
}
