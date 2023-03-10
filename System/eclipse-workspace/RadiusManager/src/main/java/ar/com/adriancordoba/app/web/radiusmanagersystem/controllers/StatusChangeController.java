/*
 * 		StatusChangeController.java
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
 * 		StatusChangeController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Mar 7, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.ClientService;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService;

@RestController
@CrossOrigin(origins = "*")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class StatusChangeController {
	private static final Logger log = LogManager.getLogger(StatusChangeController.class);

	private ClientService clientService;
	private RadiusService radiusService;

	/**
	 * @param clientService
	 * @param radiusService
	 */
	public StatusChangeController(ClientService clientService, RadiusService radiusService) {
		super();
		this.clientService = clientService;
		this.radiusService = radiusService;
	}

	@RequestMapping(value = "/status-change", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> changeStatus(@RequestParam("number") String number,
			@RequestParam("suspend") Boolean suspend) {
		if (number.contains(",")) {
			String[] components = number.split(",");
			number = components[0];
			suspend = Boolean.valueOf(components[1]);
		}

		String result = changeClientStatus(number, suspend);
		return ResponseEntity.ok(new Response(number, result));
	}

	private String changeClientStatus(String number, boolean suspend) {
		String result = "OK";
		Optional<Client> clientOptional = clientService.getClientByNumber(number);
		if (clientOptional.isPresent()) {
			Client client = clientOptional.get();
			if (client.isSuspended() != suspend) {
				client.setSuspended(suspend);
				clientService.updateClient(client);
				radiusService.deleteClient(client);
				radiusService.configureClient(client);
				if (client.isSuspended())
					radiusService.applyRateLimit(client);
				else
					radiusService.disconnect(client);
			}
		} else
			result = "NG";
		return result;
	}

	public class Response {
		String number;
		String message;

		/**
		 * @param number
		 * @param message
		 */
		public Response(String number, String message) {
			super();
			this.number = number;
			this.message = message;
		}

		/**
		 * @return the number
		 */
		public String getNumber() {
			return number;
		}

		/**
		 * @param number the number to set
		 */
		public void setNumber(String number) {
			this.number = number;
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @param message the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}
	}
}
