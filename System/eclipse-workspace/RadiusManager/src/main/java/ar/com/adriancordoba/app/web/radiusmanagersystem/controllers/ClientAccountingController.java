/*
 * 		ClientAccountingController.java
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
 * 		ClientAccountingController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 19, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadAcct;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService;

@Controller
@RequestMapping("/client-accounting")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class ClientAccountingController {
	private static final Logger log = LogManager.getLogger(ClientAccountingController.class);

	private RadiusService radiusService;

	/**
	 * @param radiusService
	 */
	public ClientAccountingController(RadiusService radiusService) {
		super();
		this.radiusService = radiusService;
	}

	@GetMapping
	public String clientStatusForm() {
		return "private/client-accounting";
	}

	@GetMapping("/client")
	public String processClient(@RequestParam String name) {
		Page<RadAcct> page = radiusService.getClientAccountingPage(name, 0);
		return "redirect:/";
	}
}
