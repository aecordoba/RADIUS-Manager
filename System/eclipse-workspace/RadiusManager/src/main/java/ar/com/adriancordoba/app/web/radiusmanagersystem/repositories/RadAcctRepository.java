/*
 * 		RadAcctRepository.java
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
 * 		RadAcctRepository.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 31, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadAcct;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public interface RadAcctRepository extends CrudRepository<RadAcct, Long> {
	@Query(value = "SELECT * FROM radacct WHERE username = ?1 AND acctstoptime IS NULL;", nativeQuery = true)
	Iterable<RadAcct> findActiveRadAcct(String name);
}
