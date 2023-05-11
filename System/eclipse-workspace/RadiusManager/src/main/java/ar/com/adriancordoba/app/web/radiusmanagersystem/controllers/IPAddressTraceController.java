/*
 * 		IPAddressTraceController.java
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
 * 		IPAddressTraceController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		May 6, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.adriancordoba.app.web.radiusmanagersystem.controllers.dto.IPAddressTrace;
import ar.com.adriancordoba.app.web.radiusmanagersystem.controllers.dto.Trace;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadAcct;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.ClientService;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.RadiusService;

@Controller
@RequestMapping("/ip-address-trace")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class IPAddressTraceController {
	private static final Logger log = LogManager.getLogger(IPAddressTraceController.class);

	private RadiusService radiusService;
	private ClientService clientService;

	/**
	 * @param radiusService
	 */
	public IPAddressTraceController(RadiusService radiusService, ClientService clientService) {
		super();
		this.radiusService = radiusService;
		this.clientService = clientService;
	}

	@ModelAttribute(name = "ipAddressTrace")
	public IPAddressTrace getIPAddressTrace() {
		return new IPAddressTrace();
	}

	@GetMapping
	public String ipAddressTrace() {
		return "private/ip-address-trace";
	}

	@PostMapping
	public String processIPAddressTrace(@Valid @ModelAttribute(name = "ipAddressTrace") IPAddressTrace ipAddressTrace,
			Errors errors, Model model) {
		if (!errors.hasErrors()) {
			model.addAttribute("tracesList", getTracesList(ipAddressTrace));
			log.info(ipAddressTrace);
		}
		return "private/ip-address-trace";
	}

	private List<Trace> getTracesList(IPAddressTrace ipAddressTrace) {
		List<Trace> tracesList = new ArrayList<>();
		List<RadAcct> ipAddressTracesList = radiusService.getIPAddressTraces(ipAddressTrace.getIpAddress(),
				ipAddressTrace.getFrom(), ipAddressTrace.getTo());
		for (RadAcct radAcct : ipAddressTracesList) {
			String userName = radAcct.getUserName();
			Client client = clientService.getClientByName(userName).orElse(null);
			String clientNumber = "";
			if (client != null)
				clientNumber = client.getNumber();
			String framedIpAddress = radAcct.getFramedIpAddress();
			LocalDateTime acctStartTime = radAcct.getAcctStartTime();
			LocalDateTime acctStopTime = radAcct.getAcctStopTime();

			Trace trace = new Trace(clientNumber, userName, framedIpAddress, acctStartTime, acctStopTime);
			tracesList.add(trace);
		}
		return tracesList;
	}
}
