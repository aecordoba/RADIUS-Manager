/*
 * 		DataSourceTests.java
 *   Copyright (C) 2022  Adrián E. Córdoba [software.asia@gmail.com]
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
 * 		DatasourceTests.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 18, 2022
 */
package ar.com.adriancordoba.app.web.radiusmanagersystem.datasource;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
@SpringBootTest
public class DataSourceTests {
	@Autowired
	private DataSource dataSource;
	private final int initialUsersCount = 3;

	@Test
	public void initialUsersTest() throws SQLException {
		ResultSet resultSet = null;
		int dbUsersCount = 0;
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT count(*) FROM Users");
			resultSet = statement.executeQuery();
			while(resultSet.next())
				dbUsersCount = resultSet.getInt(1);
			assertThat(dbUsersCount).isEqualTo(initialUsersCount);
		}finally {
			if(resultSet != null)
				resultSet.close();
		}
	}
}
