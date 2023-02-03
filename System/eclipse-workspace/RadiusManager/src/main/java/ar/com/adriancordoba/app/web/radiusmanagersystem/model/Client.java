/*
 * 		Client.java
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
 * 		Clients.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 25, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import ar.com.adriancordoba.app.web.radiusmanagersystem.annotations.FieldsCombination;

@Entity
@Table(name = "Clients")
@FieldsCombination(first = "radUserGroup", second = "ipAddress", message = "{clientcreation.errors.usergroupipaddress.combination}")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotEmpty(message = "{clientcreation.errors.number.required}")
	private String number;
	@NotEmpty(message = "{clientcreation.errors.name.required}")
	private String name;
	@NotEmpty(message = "{clientcreation.errors.password.required}")
	private String password;
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "radusergroup")
	private RadUserGroup radUserGroup;
	@Column(name = "ip_address")
	@Pattern(regexp = "^$|^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "{clientcreation.errors.ipaddress}")
	private String ipAddress;

	/**
	 * 
	 */
	public Client() {
		super();
	}

	/**
	 * @param id
	 * @param number
	 * @param name
	 * @param password
	 * @param radUserGroup
	 * @param ipAddress
	 */
	public Client(Integer id, String number, String name, String password, RadUserGroup radUserGroup,
			String ipAddress) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.password = password;
		this.radUserGroup = radUserGroup;
		this.ipAddress = ipAddress;
	}

	/**
	 * @param number
	 * @param name
	 * @param password
	 * @param radUserGroup
	 * @param ipAddress
	 */
	public Client(String number, String name, String password, RadUserGroup radUserGroup, String ipAddress) {
		super();
		this.number = number;
		this.name = name;
		this.password = password;
		this.radUserGroup = radUserGroup;
		this.ipAddress = ipAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [id=" + id + ", number=" + number + ", name=" + name + ", password=" + password
				+ ", radUserGroup=" + radUserGroup + ", ipAddress=" + ipAddress + "]";
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the radUserGroup
	 */
	public RadUserGroup getRadUserGroup() {
		return radUserGroup;
	}

	/**
	 * @param radUserGroup the radUserGroup to set
	 */
	public void setRadUserGroup(RadUserGroup radUserGroup) {
		this.radUserGroup = radUserGroup;
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
}
