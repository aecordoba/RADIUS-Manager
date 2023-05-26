/*
 * 		ClientService.java
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
 * 		ClientService.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 17, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public interface ClientService {
	Client createClient(Client client);

	void deleteClient(Client client);

	Optional<Client> getClientByName(String name);

	Optional<Client> getClientByNumber(String number);

	Client updateClient(Client client);

	Page<Client> getClientsListPage(int pageNumber, String sortField, Sort.Direction sortDirection);

	Page<Client> getClientsListPage(int pageNumber, int pageSize, String sortField,
			Sort.Direction sortDirection);
}
