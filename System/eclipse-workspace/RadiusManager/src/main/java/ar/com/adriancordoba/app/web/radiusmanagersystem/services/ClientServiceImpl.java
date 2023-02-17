/*
 * 		ClientServiceImpl.java
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
 * 		ClientServiceImpl.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 17, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.ClientsRepository;

@Service
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class ClientServiceImpl implements ClientService {
	private ClientsRepository clientsRepository;

	/**
	 * @param clientsRepository
	 */
	public ClientServiceImpl(ClientsRepository clientsRepository) {
		super();
		this.clientsRepository = clientsRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.ClientService#
	 * createClient(ar.com.adriancordoba.app.web.radiusmanagersystem.model.
	 * Client)
	 */
	@Override
	public Client createClient(Client client) {
		return clientsRepository.save(client);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.ClientService#
	 * deleteClient(ar.com.adriancordoba.app.web.radiusmanagersystem.model.
	 * Client)
	 */
	@Override
	public void deleteClient(Client client) {
		clientsRepository.delete(client);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.ClientService#
	 * getClientByName(java.lang.String)
	 */
	@Override
	public Optional<Client> getClientByName(String name) {
		return clientsRepository.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.ClientService#
	 * getClientByNumber(java.lang.String)
	 */
	@Override
	public Optional<Client> getClientByNumber(String number) {
		return clientsRepository.findByNumber(number);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.adriancordoba.app.web.radiusmanagersystem.services.ClientService#
	 * saveClient(ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client)
	 */
	@Override
	public Client updateClient(Client client) {
		return clientsRepository.save(client);
	}
}
