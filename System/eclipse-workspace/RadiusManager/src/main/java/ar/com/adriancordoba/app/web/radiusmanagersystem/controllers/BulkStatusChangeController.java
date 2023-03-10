/*
 * 		BulkStatusChangeController.java
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
 * 		BulkStatusChangeController.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Mar 6, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bulk-status-change")
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class BulkStatusChangeController {
	@GetMapping
	public String clientModificationForm() {
		return "private/bulk-status-change";
	}
}
