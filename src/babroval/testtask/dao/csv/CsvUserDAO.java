package babroval.testtask.dao.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import babroval.testtask.dao.UserDAO;
import babroval.testtask.entities.User;

public class CsvUserDAO implements UserDAO {

	@Override
	public List<User> loadAllUsers(InputStream csvFile) {

		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile))) {

			List<User> allUsers = new ArrayList<User>();

			while ((line = br.readLine()) != null) {

				String[] csvLine = line.split(cvsSplitBy);
				User user = createUserEntity(csvLine);

				allUsers.add(user);
			}
			return allUsers;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static User createUserEntity(String[] csvLine) {

		String name = csvLine[0].trim();
		String surname = csvLine[1].trim();
		String login = csvLine[2].trim();
		String email = csvLine[3].trim();
		String tel = csvLine[4].trim();

		User entity = new User();

		entity.setName(name);
		entity.setSurname(surname);
		entity.setLogin(login);
		entity.setEmail(email);
		entity.setTel(tel);

		return entity;
	}

	@Override
	public User storeUser(User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUser(User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void storeUsers(List<User> users) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> loadAllUsers(String sort) {
		throw new UnsupportedOperationException();
	}

}
