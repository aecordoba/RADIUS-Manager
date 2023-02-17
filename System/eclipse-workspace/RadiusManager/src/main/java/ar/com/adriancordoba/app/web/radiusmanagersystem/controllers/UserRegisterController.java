/*
 * 		UserRegisterController.java
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
 * 		AddUserController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 10, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.adriancordoba.app.web.radiusmanagersystem.controllers.dto.UserRegister;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Authority;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.AuthorityService;
import ar.com.adriancordoba.app.web.radiusmanagersystem.services.UserService;

@Controller
@RequestMapping("/user-register")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class UserRegisterController {
	private static final Logger log = LogManager.getLogger(UserRegisterController.class);

	private UserService userService;
	private AuthorityService authorityService;
	private PasswordEncoder passwordEncoder;

	/**
	 * @param userService
	 * @param authorityService
	 * @param passwordEncoder
	 */
	public UserRegisterController(UserService userService, AuthorityService auhorityService,
			PasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.authorityService = auhorityService;
		this.passwordEncoder = passwordEncoder;
	}

	@ModelAttribute(name = "userRegister")
	public UserRegister getUserRegister() {
		return new UserRegister();
	}

	@ModelAttribute(name = "authorities")
	public List<Authority> getAuthorities() {
		return authorityService.getAuthoritiesList();
	}

	@GetMapping
	public String userRegisterForm() {
		return "private/user-register";
	}

	@PostMapping
	public String processUserRegister(@Valid UserRegister userRegister, Errors errors, Model model) {
		if (errors.hasErrors())
			return "private/user-register";
		else {
			try {
				userService.createUser(userRegister.getUser(passwordEncoder));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				log.info("User '{}' registered by {}.", userRegister.getName(), auth.getName());
			} catch (DataIntegrityViolationException e) {
				model.addAttribute("exception", "common.exception.dataintegrityviolation");
				return "private/user-register";
			}

		}
		return "redirect:/";
	}
}
