package babroval.testtask.dao.factory;

import babroval.testtask.dao.UserDAO;

public abstract class DAOFactory {

	public abstract UserDAO getUserDAO();

	public static DAOFactory getFactory(StoradgeTypes type) {

		switch (type) {
			case MySql:
				return new MySqlDAOFactory();
				
			case Csv:
				return new CsvDAOFactory();
				
			default:
				throw new RuntimeException("Couldn't create DAOFactory: " + type);
		}
	}
}
