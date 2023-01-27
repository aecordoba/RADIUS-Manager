/*
 * 		RadReply.java
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
 * 		RadReply.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 26, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "radreply")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class RadReply {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "username")
	private String userName;
	private String attribute;
	@Column(name = "op")
	private String operator;
	private String value;

	/**
	 * 
	 */
	public RadReply() {
		super();
	}

	/**
	 * @param id
	 * @param userName
	 * @param attribute
	 * @param operator
	 * @param value
	 */
	public RadReply(Integer id, String userName, String attribute, String operator, String value) {
		super();
		this.id = id;
		this.userName = userName;
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
	}

	/**
	 * @param userName
	 * @param attribute
	 * @param operator
	 * @param value
	 */
	public RadReply(String userName, String attribute, String operator, String value) {
		super();
		this.userName = userName;
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
