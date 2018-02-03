package babroval.testtask.dao.factory;

import babroval.testtask.dao.UserDAO;
import babroval.testtask.dao.csv.CsvUserDAO;

public class CsvDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new CsvUserDAO();
	}

}
