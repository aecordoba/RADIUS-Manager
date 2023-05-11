/*
 * 		Trace.java
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
 * 		Trace.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		May 11, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers.dto;

import java.time.LocalDateTime;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class Trace {
	private String clientNumber;
	private String clientName;
	private String ipAddress;
	private LocalDateTime startTime;
	private LocalDateTime stopTime;

	/**
	 * @param clientNumber
	 * @param clientName
	 * @param ipAddress
	 * @param startTime
	 * @param stopTime
	 */
	public Trace(String clientNumber, String clientName, String ipAddress, LocalDateTime startTime,
			LocalDateTime stopTime) {
		super();
		this.clientNumber = clientNumber;
		this.clientName = clientName;
		this.ipAddress = ipAddress;
		this.startTime = startTime;
		this.stopTime = stopTime;
	}

	/**
	 * 
	 */
	public Trace() {
		super();
	}

	/**
	 * @return the clientNumber
	 */
	public String getClientNumber() {
		return clientNumber;
	}

	/**
	 * @param clientNumber the clientNumber to set
	 */
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	 * @return the startTime
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the stopTime
	 */
	public LocalDateTime getStopTime() {
		return stopTime;
	}

	/**
	 * @param stopTime the stopTime to set
	 */
	public void setStopTime(LocalDateTime stopTime) {
		this.stopTime = stopTime;
	}
}
