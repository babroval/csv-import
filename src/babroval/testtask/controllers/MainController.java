package babroval.testtask.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import babroval.testtask.dao.UserDAO;
import babroval.testtask.dao.factory.DAOFactory;
import babroval.testtask.dao.factory.StoradgeTypes;
import babroval.testtask.entities.User;
import babroval.testtask.utils.RequestUtils;
import babroval.testtask.utils.StringUtils;

@WebServlet("/main.htm")
public class MainController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String CSV_FILE = "/WEB-INF/users.csv";

	private static final String IMPORT = "import";

	private static final String NUM_OF_PAGE = "number";
	public static final String DEFAULT_NUM_OF_PAGE = "1";

	private static final String AMOUNT_USERS_PER_PAGE = "numUsers";
	public static final String DEFAULT_AMOUNT_USERS_PER_PAGE = "2";

	private static final String SORT = "sort";
	public static final String DEFAULT_SORT = "surname";

	private static final String VIEW_USER_TABLE = "WEB-INF/pages/view.jsp";
	private static final String VIEW_INFO = "WEB-INF/pages/info.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<User> allUsers = new ArrayList<User>();
		List<User> usersOnPage = new ArrayList<User>();

		Boolean isImport = RequestUtils.isParameterExist(request, IMPORT);

		if (isImport) {
			UserDAO dao = DAOFactory.getFactory(StoradgeTypes.Csv).getUserDAO();

			ServletContext context = getServletContext();

			String path = context.getRealPath(CSV_FILE);
			File csvFile = new File(path);
			allUsers = dao.loadAllUsers(csvFile);

			dao = DAOFactory.getFactory(StoradgeTypes.MySql).getUserDAO();
			dao.storeUsers(allUsers);

			request.setAttribute("info", "CSV file successfully imported into database");

			RequestDispatcher rd = request.getRequestDispatcher(VIEW_INFO);
			rd.forward(request, response);
			return;
		}

		String temp = request.getParameter(NUM_OF_PAGE);
		Integer numberOfPage = StringUtils.getIntegerOrDefaultParam(temp, DEFAULT_NUM_OF_PAGE);

		temp = request.getParameter(AMOUNT_USERS_PER_PAGE);
		Integer amountUsersPerPage = StringUtils.getIntegerOrDefaultParam(temp, DEFAULT_AMOUNT_USERS_PER_PAGE);

		temp = request.getParameter(SORT);
		String sort = StringUtils.getStringOrDefaultParam(temp, DEFAULT_SORT);

		UserDAO dao = DAOFactory.getFactory(StoradgeTypes.MySql).getUserDAO();
		allUsers = dao.loadAllUsers(sort);

		// Define amount of all pages
		Integer amountOfAllPages = allUsers.size() / amountUsersPerPage;
		if (allUsers.size() % amountUsersPerPage != 0) {
			amountOfAllPages++;
		}

		// Attach numbers to all pages
		Integer numbersOfPages[] = new Integer[amountOfAllPages];
		for (int i = 0; i < numbersOfPages.length; i++) {
			numbersOfPages[i] = i + 1;
		}

		// Define users on page
		Integer indexOfFirstElementOnPage = amountUsersPerPage * (numberOfPage - 1);
		for (int i = indexOfFirstElementOnPage; i < allUsers.size() && i < amountUsersPerPage * numberOfPage; i++) {
			usersOnPage.add(allUsers.get(i));
		}

		request.setAttribute("usersCount", amountUsersPerPage);
		request.setAttribute("pageCount", numbersOfPages);
		request.setAttribute("sort", sort);
		request.setAttribute("users", usersOnPage);
		RequestDispatcher rd = request.getRequestDispatcher(VIEW_USER_TABLE);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
