package babroval.testtask.dao.mysql.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import babroval.testtask.entities.User;

public final class ResultSetConverter {

	private ResultSetConverter() {
		throw new AssertionError("Class contains static methods only. You should not instantiate it!");
	}

	public static User createUserEntity(ResultSet set) throws SQLException {
		Integer userId = set.getInt("id");
		String name = set.getString("name");
		String surname = set.getString("surname");
		String login = set.getString("login");
		String email = set.getString("email");
		String tel = set.getString("tel");

		User entity = new User();

		entity.setId(userId);
		entity.setName(name);
		entity.setSurname(surname);
		entity.setLogin(login);
		entity.setEmail(email);
		entity.setTel(tel);

		return entity;
	}

}
