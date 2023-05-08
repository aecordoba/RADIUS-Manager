/*
 * 		IPAddressTrace.java
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
 * 		IPAddressTrace.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		May 6, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;

import ar.com.adriancordoba.app.web.radiusmanagersystem.annotations.FieldsSequence;

@FieldsSequence(first = "from", second = "to", message = "{ipaddresstrace.errors.datessequence}")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class IPAddressTrace {
	// @NotEmpty(message = "ipaddresstrace.errors.ipaddressempty")
	@Pattern(regexp = "^$|^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "{ipaddresstrace.errors.ipaddress}")
	private String ipAddress;
	// @NotEmpty(message = "ipaddresstrace.errors.date")
	private LocalDateTime from;
	// @NotEmpty(message = "ipaddresstrace.errors.date")
	private LocalDateTime to;

	/**
	 * @param ipAddress
	 * @param from
	 * @param to
	 */
	public IPAddressTrace(String ipAddress, LocalDateTime from, LocalDateTime to) {
		super();
		this.ipAddress = ipAddress;
		this.from = from;
		this.to = to;
	}

	/**
	 * 
	 */
	public IPAddressTrace() {
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the from
	 */
	public LocalDateTime getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public LocalDateTime getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
}
