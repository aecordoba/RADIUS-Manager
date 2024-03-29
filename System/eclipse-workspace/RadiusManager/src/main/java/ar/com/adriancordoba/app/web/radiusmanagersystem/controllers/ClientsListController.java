/*
 * 		ClientsListController.java
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
 * 		ClientsListController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		May 25, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.ClientService;

@Controller
@RequestMapping("/clients-list")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class ClientsListController {
	private static final Logger log = LogManager.getLogger(ClientsListController.class);

	private ClientService clientService;

	/**
	 * @param clientService
	 */
	public ClientsListController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@GetMapping
	public String processClientsList(@RequestParam(value = "order", defaultValue = "1") int order,
			@RequestParam(value = "sortField", defaultValue = "number") String sortField,
			@RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection, Model model) {
		Sort.Direction direction = (sortDirection.equals("ASC")) ? Sort.Direction.ASC : Sort.Direction.DESC;
		String changeSortDirection = (sortDirection.equals("ASC")) ? "DESC" : "ASC";
		Page<Client> page = clientService.getClientsListPage(order - 1, sortField, direction);
		model.addAttribute("clientsList", page.getContent());
		model.addAttribute("currentPage", order);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("changeSortDirection", changeSortDirection);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		return "private/clients-list";
	}

}
