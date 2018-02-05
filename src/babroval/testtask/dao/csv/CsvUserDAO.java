package babroval.testtask.dao.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import babroval.testtask.dao.UserDAO;
import babroval.testtask.entities.User;

public class CsvUserDAO implements UserDAO {

	@Override
	public List<User> loadAllUsers(File csvFile) {

		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			if (csvFile.length() == 0) {
				throw new RuntimeException("CSV file is empty");
			}

			List<User> allUsers = new ArrayList<User>();

			while ((line = br.readLine()) != null) {

				String[] csvLine = line.split(cvsSplitBy);
				User user = createUserEntity(csvLine);

				allUsers.add(user);
			}

			return allUsers;

		} catch (FileNotFoundException e) {
			throw new RuntimeException("CSV file was not found", e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static User createUserEntity(String[] csvLine) {

		try {
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

		} catch (Exception e) {
			throw new RuntimeException("incorrect data in CSV file", e);
		}
	}

	@Override
	public User storeUser(User user) {
		throw new UnsupportedOperationException("method has not implemented yet");
	}

	@Override
	public void updateUser(User user) {
		throw new UnsupportedOperationException("method has not implemented yet");
	}

	@Override
	public void storeUsers(List<User> users) {
		throw new UnsupportedOperationException("method has not implemented yet");
	}

	@Override
	public List<User> loadAllUsers(String sort) {
		throw new UnsupportedOperationException("method has not implemented yet");
	}

}