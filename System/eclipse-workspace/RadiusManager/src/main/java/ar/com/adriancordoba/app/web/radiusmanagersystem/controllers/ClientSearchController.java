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

import java.util.NoSuchElementException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.adriancordoba.app.web.radiusmanagersystem.controllers.dto.ClientData;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.ClientsRepository;

@RestController
@CrossOrigin(origins = "*")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class ClientSearchController {
	private ClientsRepository clientRepository;

	/**
	 * @param clientRepository
	 */
	public ClientSearchController(ClientsRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	@RequestMapping(value = "/client-search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> searchClient(@RequestParam("number") String number,
			@RequestParam("name") String name) {
		// Client client = null;
		// try {
		// if (number.isBlank())
		// client = clientRepository.findByName(name).get();
		// else
		// client = clientRepository.findByNumber(number).get();
		// } catch (NoSuchElementException exception) {
		//
		// }
		// HttpHeaders headers = new HttpHeaders();
		// ResponseEntity<Client> entity = null;
		// if (client != null)
		// entity = new ResponseEntity<>(client, headers, HttpStatus.CREATED);
		// return entity;
		if (number.isBlank())
			return ResponseEntity.of(clientRepository.findByName(name));
		else
			return ResponseEntity.of(clientRepository.findByNumber(number));
	}

	// @PostMapping("/client-search")
	// public ResponseEntity<?> getClient(@Valid @RequestBody ClientData data,
	// Errors errors) {
	// System.out.println("Data: " + data.getNumber());
	// ClientResponse clientResponse = new ClientResponse();
	// if (errors.hasErrors()) {
	// clientResponse.setMessage(
	// errors.getAllErrors().stream().map(x ->
	// x.getDefaultMessage()).collect(Collectors.joining(",")));
	// return ResponseEntity.badRequest().body(clientResponse);
	// }
	// clientResponse.setMessage("Success");
	// try {
	// clientResponse.setClient(getClient(data));
	// } catch (NoSuchElementException exception) {
	// clientResponse.setMessage("No client found.");
	// }
	// return ResponseEntity.ok(clientResponse);
	// }

	private Client getClient(ClientData data) throws NoSuchElementException {
		Client client = null;
		if (data.getNumber() != null)
			client = clientRepository.findByNumber(data.getNumber()).get();
		else
			client = clientRepository.findByName(data.getName()).get();
		return client;
	}
}
