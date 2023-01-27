/*
 * 		Nas.java
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
 * 		Nas.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 25, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nas")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class Nas {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "nasname")
	private String name;
	@Column(name = "shortname")
	private String shortName;
	private String type;
	private Integer ports;
	private String secret;
	private String server;
	private String community;
	private String description;

	/**
	 * 
	 */
	public Nas() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param shortName
	 * @param type
	 * @param ports
	 * @param secret
	 * @param server
	 * @param community
	 * @param description
	 */
	public Nas(Integer id, String name, String shortName, String type, Integer ports, String secret, String server,
			String community, String description) {
		super();
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.type = type;
		this.ports = ports;
		this.secret = secret;
		this.server = server;
		this.community = community;
		this.description = description;
	}

	/**
	 * @param name
	 * @param shortName
	 * @param type
	 * @param ports
	 * @param secret
	 * @param server
	 * @param community
	 * @param description
	 */
	public Nas(String name, String shortName, String type, Integer ports, String secret, String server,
			String community,
			String description) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.type = type;
		this.ports = ports;
		this.secret = secret;
		this.server = server;
		this.community = community;
		this.description = description;
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
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the ports
	 */
	public Integer getPorts() {
		return ports;
	}

	/**
	 * @param ports the ports to set
	 */
	public void setPorts(Integer ports) {
		this.ports = ports;
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @return the community
	 */
	public String getCommunity() {
		return community;
	}

	/**
	 * @param community the community to set
	 */
	public void setCommunity(String community) {
		this.community = community;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
