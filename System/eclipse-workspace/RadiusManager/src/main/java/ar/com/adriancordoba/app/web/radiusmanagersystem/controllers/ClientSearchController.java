/*
 * 		ClientSearchController.java
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
 * 		ClientSearchController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 8, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.ClientService;

@RestController
@CrossOrigin(origins = "*")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class ClientSearchController {
	private ClientService clientService;

	/**
	 * @param clientService
	 */
	public ClientSearchController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@RequestMapping(value = "/client-search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> searchClient(@RequestParam("number") String number,
			@RequestParam("name") String name) {
		if (number.isBlank())
			return ResponseEntity.of(clientService.getClientByName(name));
		else
			return ResponseEntity.of(clientService.getClientByNumber(number));
	}
}
