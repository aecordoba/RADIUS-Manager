/*
 * 		RadiusService.java
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
 * 		RadiusService.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 17, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadAcct;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadUserGroup;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public interface RadiusService {

	void configureClient(Client client);

	List<RadUserGroup> getRadUserGroupList();

	void deleteClient(Client client);

	Page<RadAcct> getClientAccountingPage(String userName, int pageNumber);

	Page<RadAcct> getClientAccountingPage(String userName, int pageNumber, int pageSize, String sortField,
			Sort.Direction sortDirection);

	List<RadAcct> getIPAddressTraces(String ipAddress, LocalDateTime from, LocalDateTime to);

	void disconnect(Client client);

	void applyRateLimit(Client client);
}