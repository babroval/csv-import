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

	private static final String PAGE_NUM = "number";
	public static final String DEFAULT_PAGE_NUM = "1";

	private static final String NUM_USERS_FOR_PAGE = "numUsers";
	public static final String DEFAULT_NUM_USERS = "2";

	private static final String SORT = "sort";
	public static final String DEFAULT_SORT = "surname";

	private static final String VIEW_USER_TABLE = "WEB-INF/pages/view.jsp";
	private static final String VIEW_IMPORT = "WEB-INF/pages/import.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<User> allUsers = new ArrayList<User>();
		List<User> users = new ArrayList<User>();

		Boolean isImport = RequestUtils.isParameterExist(request, IMPORT);

		if (isImport) {
			UserDAO dao = DAOFactory.getFactory(StoradgeTypes.Csv).getUserDAO();

			ServletContext context = getServletContext();
			
			String path = context.getRealPath(CSV_FILE);
			File csvFile = new File(path);
			allUsers = dao.loadAllUsers(csvFile);

			dao = DAOFactory.getFactory(StoradgeTypes.MySql).getUserDAO();
			dao.storeUsers(allUsers);

			request.setAttribute("info", "CSV file sucssesful imported to MySql data base");

			RequestDispatcher rd = request.getRequestDispatcher(VIEW_IMPORT);
			rd.forward(request, response);
			return;
		}

		String temp = request.getParameter(PAGE_NUM);
		Integer pageNumber = StringUtils.getIntegerOrDefaultParam(temp, DEFAULT_PAGE_NUM);

		temp = request.getParameter(NUM_USERS_FOR_PAGE);
		Integer usersCount = StringUtils.getIntegerOrDefaultParam(temp, DEFAULT_NUM_USERS);

		temp = request.getParameter(SORT);
		String sort = StringUtils.getStringOrDefaultParam(temp, DEFAULT_SORT);

		UserDAO dao = DAOFactory.getFactory(StoradgeTypes.MySql).getUserDAO();
		allUsers = dao.loadAllUsers(sort);

		Integer i = usersCount * pageNumber - usersCount;

		do {
			users.add(allUsers.get(i));
			i++;
		} while (i < usersCount * pageNumber && i < allUsers.size());

		Integer pagesQuantity = allUsers.size() / usersCount;
		if (allUsers.size() % usersCount != 0) {
			pagesQuantity++;
		}

		Integer pageCount[] = new Integer[pagesQuantity];
		for (i = 0; i < pageCount.length; i++) {
			pageCount[i] = i + 1;
		}

		request.setAttribute("usersCount", usersCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("sort", sort);
		request.setAttribute("users", users);
		RequestDispatcher rd = request.getRequestDispatcher(VIEW_USER_TABLE);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
