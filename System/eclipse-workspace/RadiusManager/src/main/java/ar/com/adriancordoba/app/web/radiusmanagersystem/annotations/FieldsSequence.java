/*
 * 		FieldsSequence.java
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
 * 		FieldsSequence.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		May 7, 2023
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = FieldsSequenceValidator.class)
/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public @interface FieldsSequence {
	String message() default "Fields sequence.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String fromDateFieldName();

	String fromTimeFieldName();

	String toDateFieldName();

	String toTimeFieldName();

	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		FieldsSequence[] value();
	}
}
