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

import java.util.ArrayList;
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
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.AuthoritiesRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.UsersRepository;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Controller
@RequestMapping("/user-register")
public class UserRegisterController {
	private static final Logger log = LogManager.getLogger(UserRegisterController.class);

	private UsersRepository usersRepository;
	private AuthoritiesRepository authoritiesRepository;
	private PasswordEncoder passwordEncoder;

	/**
	 * @param usersRepository
	 * @param auhoritiesRepository
	 * @param passwordEncoder
	 */
	public UserRegisterController(UsersRepository usersRepository, AuthoritiesRepository auhoritiesRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.usersRepository = usersRepository;
		this.authoritiesRepository = auhoritiesRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@ModelAttribute(name = "userRegister")
	public UserRegister getUserRegister() {
		return new UserRegister();
	}

	@ModelAttribute(name = "authorities")
	public List<Authority> getAuthorities() {
		List<Authority> authorities = new ArrayList<>();
		authorities = (List<Authority>) authoritiesRepository.findAll();
		return authorities;
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
				usersRepository.save(userRegister.getUser(passwordEncoder));
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
