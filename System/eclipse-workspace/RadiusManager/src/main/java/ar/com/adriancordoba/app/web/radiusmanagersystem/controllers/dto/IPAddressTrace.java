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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import ar.com.adriancordoba.app.web.radiusmanagersystem.annotations.FieldsSequence;

@FieldsSequence(fromDateFieldName = "fromDate", fromTimeFieldName = "fromTime", toDateFieldName = "toDate", toTimeFieldName = "toTime", message = "{ipaddresstrace.errors.datessequence}")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class IPAddressTrace {
	@NotBlank(message = "{ipaddresstrace.errors.ipaddressempty}")
	@Pattern(regexp = "^$|^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "{ipaddresstrace.errors.ipaddress}")
	private String ipAddress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime fromTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime toTime;

	/**
	 * @param ipAddress
	 * @param fromDate
	 * @param fromTime
	 * @param toDate
	 * @param toTime
	 */
	public IPAddressTrace(
			@NotBlank(message = "{ipaddresstrace.errors.ipaddressempty}") @Pattern(regexp = "^$|^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "{ipaddresstrace.errors.ipaddress}") String ipAddress,
			LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
		super();
		this.ipAddress = ipAddress;
		this.fromDate = fromDate;
		this.fromTime = fromTime;
		this.toDate = toDate;
		this.toTime = toTime;
	}

	/**
	 * 
	 */
	public IPAddressTrace() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Trace to " + getIpAddress() + " from " + getFrom() + " to " + getTo() + ".";
	}

	public LocalDateTime getFrom() {
		return LocalDateTime.of(fromDate, fromTime);
	}

	public LocalDateTime getTo() {
		return LocalDateTime.of(toDate, toTime);
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
	 * @return the fromDate
	 */
	public LocalDate getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the fromTime
	 */
	public LocalTime getFromTime() {
		return fromTime;
	}

	/**
	 * @param fromTime the fromTime to set
	 */
	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}

	/**
	 * @return the toDate
	 */
	public LocalDate getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the toTime
	 */
	public LocalTime getToTime() {
		return toTime;
	}

	/**
	 * @param toTime the toTime to set
	 */
	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}
}
