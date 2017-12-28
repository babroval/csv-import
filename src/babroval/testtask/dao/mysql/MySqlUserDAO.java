package babroval.testtask.dao.mysql;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import babroval.testtask.dao.UserDAO;
import babroval.testtask.dao.mysql.db.ConnectionPool;
import babroval.testtask.dao.mysql.db.ResultSetConverter;
import babroval.testtask.entities.User;

public class MySqlUserDAO implements UserDAO {

	@Override
	public User storeUser(User user) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection
					.prepareStatement("INSERT INTO users (name, surname, login, email, tel) VALUES (?, ?, ?, ?, ?)");
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getLogin());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getTel());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement(
					"UPDATE USERS SET name = ?, surname = ?, login = ?, email = ?, tel = ? WHERE login='"
							+ user.getLogin() + "'");
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getLogin());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getTel());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}
	}

	@Override
	public void storeUsers(List<User> users) {
		dropTableIfExists();
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `users` ("
					+ "`id`             INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,"
					+ "`name`   	    VARCHAR(255) DEFAULT \"\"," + "`surname`     	VARCHAR(255) DEFAULT \"\","
					+ "`login`      	VARCHAR(255) DEFAULT \"\"," + "`email`       	VARCHAR(255) DEFAULT \"\","
					+ "`tel`	        VARCHAR(255) DEFAULT \"\");");

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement);
		}

		for (User user : users) {

			storeUser(user);
		}
	}

	private void dropTableIfExists() {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("DROP TABLE IF EXISTS `users`;");

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement);
		}

	}

	@Override
	public List<User> loadAllUsers(String sort) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		List<User> result = new ArrayList<User>();

		try {
			connection = ConnectionPool.getPool().getConnection();
			statement = connection.prepareStatement("SELECT * from users ORDER BY " + sort + " ");
			set = statement.executeQuery();

			while (set.next()) {
				User entity = ResultSetConverter.createUserEntity(set);
				result.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}

		return result;

	}

	@Override
	public List<User> loadAllUsers(InputStream csvFile) {
		throw new UnsupportedOperationException();
	}

}
