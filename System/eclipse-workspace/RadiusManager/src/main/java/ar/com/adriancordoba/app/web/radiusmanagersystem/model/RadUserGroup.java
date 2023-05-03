/*
 * 		RadUserGroup.java
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
 * 		RadUserGroup.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 25, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "radusergroup")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class RadUserGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "username")
	private String userName;
	@Column(name = "groupname")
	private String groupName;
	private int priority;

	/**
	 * 
	 */
	public RadUserGroup() {
		super();
	}

	/**
	 * @param id
	 * @param userName
	 * @param groupName
	 * @param priority
	 */
	public RadUserGroup(Integer id, String userName, String groupName, int priority) {
		super();
		this.id = id;
		this.userName = userName;
		this.groupName = groupName;
		this.priority = priority;
	}

	/**
	 * @param userName
	 * @param groupName
	 * @param priority
	 */
	public RadUserGroup(String userName, String groupName, int priority) {
		super();
		this.userName = userName;
		this.groupName = groupName;
		this.priority = priority;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RadUserGroup other = (RadUserGroup) obj;
		return Objects.equals(id, other.id);
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
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
