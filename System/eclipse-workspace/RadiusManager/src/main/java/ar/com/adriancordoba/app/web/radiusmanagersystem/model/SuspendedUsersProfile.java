/*
 * 		SuspendedUsersProfile.java
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
 * 		SuspendedUsersProfile.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 13, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Suspended_Users_Profiles")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class SuspendedUsersProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToOne
	@JoinColumn(name = "radusergroup")
	private RadUserGroup radUserGroup;

	/**
	 * 
	 */
	public SuspendedUsersProfile() {
		super();
	}

	/**
	 * @param id
	 * @param radUserGroup
	 */
	public SuspendedUsersProfile(Integer id, RadUserGroup radUserGroup) {
		super();
		this.id = id;
		this.radUserGroup = radUserGroup;
	}

	/**
	 * @param radUserGroup
	 */
	public SuspendedUsersProfile(RadUserGroup radUserGroup) {
		super();
		this.radUserGroup = radUserGroup;
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
		SuspendedUsersProfile other = (SuspendedUsersProfile) obj;
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
}
