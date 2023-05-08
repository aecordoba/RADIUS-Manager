/*
 * 		FieldsSequenceValidator.java
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
 * 		FieldsSequenceValidator.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		May 7, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.annotations;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class FieldsSequenceValidator implements ConstraintValidator<FieldsSequence, LocalDateTime> {
	private LocalDateTime first;
	private LocalDateTime second;
	private String message;

	@Override
	public void initialize(final FieldsSequence constraintAnnotation) {
		first = LocalDateTime.parse(constraintAnnotation.first());
		second = LocalDateTime.parse(constraintAnnotation.second());
		message = constraintAnnotation.message();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
		boolean result = false;
		if (first.isAfter(second))
			result = true;
		return result;
	}

}
