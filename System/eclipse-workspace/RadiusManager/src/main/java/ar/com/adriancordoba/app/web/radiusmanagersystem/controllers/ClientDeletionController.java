/*
 * 		ClientDeletionController.java
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
 * 		ClientDeletionController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 31, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.adriancordoba.app.web.radiusmanagersystem.controllers.dto.ClientDeletion;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Client;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.Nas;
import ar.com.adriancordoba.app.web.radiusmanagersystem.model.RadAcct;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.ClientsRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.NasRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadAcctRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadCheckRepository;
import ar.com.adriancordoba.app.web.radiusmanagersystem.repositories.RadReplyRepository;

@Controller
@RequestMapping("/client-deletion")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class ClientDeletionController {
	private static final Logger log = LogManager.getLogger(ClientDeletionController.class);

	private RadAcctRepository radAcctRepository;
	private ClientsRepository clientsRepository;
	private NasRepository nasRepository;
	private RadCheckRepository radCheckRepository;
	private RadReplyRepository radReplyRepository;

	/**
	 * @param radAcctRepository
	 * @param clientsRepository
	 * @param nasRepository
	 * @param radCheckRepository
	 * @param radReplyRepository
	 */
	public ClientDeletionController(RadAcctRepository radAcctRepository, ClientsRepository clientsRepository,
			NasRepository nasRepository, RadCheckRepository radCheckRepository, RadReplyRepository radReplyRepository) {
		super();
		this.radAcctRepository = radAcctRepository;
		this.clientsRepository = clientsRepository;
		this.nasRepository = nasRepository;
		this.radCheckRepository = radCheckRepository;
		this.radReplyRepository = radReplyRepository;
	}

	@ModelAttribute(name = "clientDeletion")
	public ClientDeletion getClientDeletion() {
		return new ClientDeletion();
	}

	@GetMapping
	public String clientDeletionForm() {
		return "private/client-deletion";
	}

	@PostMapping
	public String processUserRegister(@Valid ClientDeletion clientDeletion, Errors errors, Model model) {
		if (errors.hasErrors())
			return "private/client-deletion";
		else {
			try {
				Client client = getClient(clientDeletion);
				clientsRepository.delete(client);
				for (RadAcct radAcct : getActiveRadAcct(client)) {
					Nas nas = getNas(radAcct.getNasIpAddress());
					// Send system command.
				}
			} catch (Exception e) {
				model.addAttribute("exception", "common.exception.nodata");
				return "private/client-deletion";
			}
		}
		return "redirect:/";
	}

	private Client getClient(ClientDeletion clientDeletion) throws Exception {
		List<Client> clientsList = null;
		if (!clientDeletion.getNumber().isBlank())
			clientsList = (List<Client>) clientsRepository.findByNumber(clientDeletion.getNumber());
		else
			clientsList = (List<Client>) clientsRepository.findByName(clientDeletion.getName());
		if (clientsList.isEmpty())
			throw new Exception("No client found.");
		return clientsList.get(0);
	}

	private List<RadAcct> getActiveRadAcct(Client client) {
		List<RadAcct> activeRadAcctList = (List<RadAcct>) radAcctRepository.findActiveRadAcct(client.getName());
		return activeRadAcctList;
	}

	private Nas getNas(String name) {
		List<Nas> nasList = (List<Nas>) nasRepository.findByName(name);
		return nasList.get(0);
	}
}
