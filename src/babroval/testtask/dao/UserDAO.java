package babroval.testtask.dao;

import java.io.File;
import java.util.List;

import babroval.testtask.entities.User;

public interface UserDAO {
	
	User storeUser(User user);

	void updateUser(User user);
	
	List<User> loadAllUsers(File csvFile);

	void storeUsers(List<User> users);

	List<User> loadAllUsers(String sort);
	
}
