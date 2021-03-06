package babroval.testtask.dao.factory;

import babroval.testtask.dao.UserDAO;
import babroval.testtask.dao.mysql.MySqlUserDAO;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new MySqlUserDAO();
	}

}
