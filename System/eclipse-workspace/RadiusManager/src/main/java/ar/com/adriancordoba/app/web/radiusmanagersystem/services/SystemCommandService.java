/*
 * 		SystemCommandService.java
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
 * 		SystemCommandService.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Feb 2, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.services;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class SystemCommandService {
	private static final Logger log = LogManager.getLogger(SystemCommandService.class);

	private ProcessBuilder processBuilder = new ProcessBuilder();

	/**
	 * 
	 */
	public SystemCommandService() {
		super();
	}

	public boolean disconnect(String sessionId, String userName, String nasIpAddress, String nasPort,
			String nasSecret) {
		boolean result = false;
		String command = "echo Acct-Session-Id=" + sessionId + ",User-Name=" + userName + ",NAS-IP-Address="
				+ nasIpAddress + " | /usr/bin/radclient -r 1 " + nasIpAddress + ":" + nasPort + " disconnect "
				+ nasSecret;
		processBuilder.command("bash", "-c", command);
		try {
			Process process = processBuilder.start();
			int exitVal = process.waitFor();
			if (exitVal == 0)
				result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}
}
