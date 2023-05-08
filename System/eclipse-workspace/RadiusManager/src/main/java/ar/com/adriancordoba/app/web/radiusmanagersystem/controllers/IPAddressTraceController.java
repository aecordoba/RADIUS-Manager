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

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.adriancordoba.app.web.radiusmanagersystem.controllers.dto.IPAddressTrace;

@Controller
@RequestMapping("/ip-address-trace")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class IPAddressTraceController {
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
		System.out.println("from: " + ipAddressTrace.getFrom());
		System.out.println("to: " + ipAddressTrace.getTo());
		if (errors.hasErrors())
			return "private/ip-address-trace";
		else
			return "redirect:/";
	}
}
