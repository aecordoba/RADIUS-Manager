/*
 * 		LoginAttemptsService.java
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
 * 		LoginAttemptsService.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 4, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.services;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@Service
public class LoginAttemptsService {
	private static final int MAX_ATTEMPTS = 3;
	private LoadingCache<String, Integer> loginAttemptsCache;

	/**
	 * 
	 */
	public LoginAttemptsService() {
		loginAttemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS)
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(String key) throws Exception {
						return 0;
					}
				});
	}

	public void loginSuccess(String username) {
		loginAttemptsCache.invalidate(username);
	}

	public void loginFailed(String username) {
		int failedAttempts = 0;
		try {
			failedAttempts = loginAttemptsCache.get(username);
		} catch (ExecutionException e) {
			failedAttempts = 0;
		}
		failedAttempts++;
		loginAttemptsCache.put(username, failedAttempts);
	}

	public boolean isBlocked(String username) {
		try {
			return loginAttemptsCache.get(username) >= MAX_ATTEMPTS;
		} catch (ExecutionException e) {
			return false;
		}
	}
}
