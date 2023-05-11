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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class FieldsSequenceValidator implements ConstraintValidator<FieldsSequence, Object> {
	private String fromDateFieldName;
	private String fromTimeFieldName;
	private String toDateFieldName;
	private String toTimeFieldName;
	private String message;

	@Override
	public void initialize(final FieldsSequence constraintAnnotation) {
		fromDateFieldName = constraintAnnotation.fromDateFieldName();
		fromTimeFieldName = constraintAnnotation.fromTimeFieldName();
		toDateFieldName = constraintAnnotation.toDateFieldName();
		toTimeFieldName = constraintAnnotation.toTimeFieldName();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean result = false;
		try {
			final LocalDate fromDate = LocalDate.parse(BeanUtils.getProperty(value, fromDateFieldName));
			final LocalTime fromTime = LocalTime.parse(BeanUtils.getProperty(value, fromTimeFieldName));
			final LocalDate toDate = LocalDate.parse(BeanUtils.getProperty(value, toDateFieldName));
			final LocalTime toTime = LocalTime.parse(BeanUtils.getProperty(value, toTimeFieldName));

			final LocalDateTime from = LocalDateTime.of(fromDate, fromTime);
			final LocalDateTime to = LocalDateTime.of(toDate, toTime);

			if (from.isBefore(to))
				result = true;
		} catch (final Exception ignore) {
			// Ignore.
		}
		if (!result) {
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(toTimeFieldName)
					.addConstraintViolation()
					.disableDefaultConstraintViolation();
		}
		return result;
	}
}
