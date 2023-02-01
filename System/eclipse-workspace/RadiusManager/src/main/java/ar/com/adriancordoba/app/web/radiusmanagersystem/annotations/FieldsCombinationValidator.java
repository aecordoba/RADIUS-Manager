/*
 * 		FieldsCombinationValidator.java
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
 * 		FieldsCombinationValidator.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Jan 27, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class FieldsCombinationValidator implements ConstraintValidator<FieldsCombination, Object> {
	private String firstFieldName;
	private String secondFieldName;
	private String message;

	@Override
	public void initialize(final FieldsCombination constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean valid = false;
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
			if (firstObj instanceof String)
				valid = !((String) firstObj).equals("") || !((String) secondObj).equals("");
			else
				valid = firstObj != null || !((String) secondObj).equals("");
		} catch (final Exception ignore) {
			// ignore
		}
		if (!valid) {
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(firstFieldName)
					.addConstraintViolation()
					.disableDefaultConstraintViolation();
		}
		return valid;
	}

}
