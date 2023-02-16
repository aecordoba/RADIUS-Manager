/*
 * 		ClientStatusController.java
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
 * 		ClientStatusController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 16, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadUserGroup;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.ClientsRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService;

@Controller
@RequestMapping("/client-status")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class ClientStatusController {
	private static final Logger log = LogManager.getLogger(ClientStatusController.class);

	private ClientsRepository clientsRepository;
	private RadiusService radiusService;

	/**
	 * @param clientsRepository
	 * @param radiusService
	 */
	public ClientStatusController(ClientsRepository clientsRepository, RadiusService radiusService) {
		super();
		this.clientsRepository = clientsRepository;
		this.radiusService = radiusService;
	}

	@ModelAttribute(name = "client")
	public Client getClient() {
		return new Client();
	}

	@ModelAttribute(name = "radUserGroupList")
	public List<RadUserGroup> getRadUserGroupList() {
		return radiusService.getRadUserGroupList();
	}

	@GetMapping
	public String clientStatusForm() {
		return "private/client-status";
	}

	@PostMapping
	public String processClientStatus(@Valid Client client, Errors errors, Model model) {
		if (errors.hasErrors())
			return "private/client-status";
		else {
			try {
				clientsRepository.save(client);
				radiusService.deleteClient(client);
				radiusService.configureClient(client);
				radiusService.applyRateLimit(client);
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				log.info("Status of client '{}' changed to {} by {}.", client.getName(), client.getStatus(),
						auth.getName());
			} catch (DataIntegrityViolationException e) {
				model.addAttribute("exception", "common.exception.dataintegrityviolation");
				return "private/client-modification";
			}
		}
		return "redirect:/";
	}
}
