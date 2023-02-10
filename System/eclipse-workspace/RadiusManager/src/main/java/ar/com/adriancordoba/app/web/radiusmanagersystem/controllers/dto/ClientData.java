/*
 * 		ClientData.java
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
 * 		ClientData.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 31, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers.dto;

import ar.com.adriancordoba.app.web.radiusmanagersystem.annotations.FieldsCombination;

@FieldsCombination(first = "number", second = "name", message = "{clientdata.errors.numbername.combination}")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class ClientData {
	private String number;
	private String name;

	/**
	 * 
	 */
	public ClientData() {
		super();
	}

	/**
	 * @param number
	 * @param name
	 */
	public ClientData(String number, String name) {
		super();
		this.number = number;
		this.name = name;
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
}
