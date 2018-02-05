package babroval.testtask.dao.mysql;

import java.io.File;
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

		String query = "INSERT INTO users (name, surname, login, email, tel) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = ConnectionPool.getPool().getConnection();
				PreparedStatement statement = connection.prepareStatement(query))
		{
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getLogin());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getTel());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public void updateUser(User user) {

		String query = "UPDATE USERS SET name = ?, surname = ?, login = ?, email = ?, tel = ? WHERE login='"
				        + user.getLogin() + "'";
	
		try (Connection connection = ConnectionPool.getPool().getConnection();
			PreparedStatement statement = connection.prepareStatement(query))
		{
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getLogin());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getTel());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void storeUsers(List<User> users) {
		
		String query = "CREATE TABLE IF NOT EXISTS `users` ("
			      + "`id`             INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,"
			      + "`name`   	    VARCHAR(255) DEFAULT \"\"," + "`surname`     	VARCHAR(255) DEFAULT \"\","
			      + "`login`      	VARCHAR(255) DEFAULT \"\"," + "`email`       	VARCHAR(255) DEFAULT \"\","
		          + "`tel`	        VARCHAR(255) DEFAULT \"\");";
		
		dropTableIfExists();

		try (Connection connection = ConnectionPool.getPool().getConnection();
        	 PreparedStatement statement = connection.prepareStatement(query))
		{
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		for (User user : users) {

			storeUser(user);
		}
	}

	private void dropTableIfExists() {

		String query = "DROP TABLE IF EXISTS `users`;";
		
		try (Connection connection = ConnectionPool.getPool().getConnection();
 			 PreparedStatement statement = connection.prepareStatement(query)) {

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> loadAllUsers(String sort) {

		String query = "SELECT * from users ORDER BY " + sort + " ";
		
		List<User> result = new ArrayList<User>();

		try (Connection connection = ConnectionPool.getPool().getConnection();
			 PreparedStatement statement = connection.prepareStatement(query);
			 ResultSet set = statement.executeQuery()) {

			while (set.next()) {
				User entity = ResultSetConverter.createUserEntity(set);
				result.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		if (result.isEmpty()) {
			throw new RuntimeException("database table is empty");
		}

		return result;

	}

	@Override
	public List<User> loadAllUsers(File csvFile) {
		throw new UnsupportedOperationException("Method has not implemented yet");
	}

}
