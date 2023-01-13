/*
 * 		UserRegister.java
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
 * 		UserRegisterForm.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 11, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers.web;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.password.PasswordEncoder;

import ar.com.adriancordoba.app.web.radiusmanagersystem.annotations.FieldMatch;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Authority;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.User;

@FieldMatch(first = "password", second = "confirmPassword", message = "{userregister.errors.password.mismatch}")
/**
 * @author Adrián E. Córdoba [softwa super(); re.asia@gmail.com]
 */
public class UserRegister {
	@NotEmpty(message = "{userregister.errors.user.required}")
	private String name;
	@NotEmpty(message = "{userregister.errors.password.required}")
	private String password;
	@NotEmpty(message = "{userregister.errors.confirmpassword.required}")
	private String confirmPassword;
	private boolean enabled;
	@NotEmpty(message = "{userregister.errors.authorities.required}")
	private Set<Authority> authorities = new HashSet<>();
	@NotEmpty(message = "{userregister.errors.firstname.required}")
	private String firstName;
	private String middleName;
	@NotEmpty(message = "{userregister.errors.lastname.required}")
	private String lastName;

	/**
	 * @param name
	 * @param password
	 * @param confirmPassword
	 * @param enabled
	 * @param authorities
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 */
	public UserRegister(String name, String password, String confirmPassword, boolean enabled,
			Set<Authority> authorities, String firstName, String middleName, String lastName) {
		super();
		this.name = name;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.authorities = authorities;
		this.enabled = enabled;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	/**
	 * 
	 */
	public UserRegister() {
	}

	public User getUser(PasswordEncoder passwordEncoder) {
		return new User(name, passwordEncoder.encode(password), authorities, enabled, firstName, middleName, lastName);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the authorities
	 */
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
