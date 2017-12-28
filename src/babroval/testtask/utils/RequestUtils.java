package babroval.testtask.utils;

import javax.servlet.http.HttpServletRequest;

public final class RequestUtils {

	private RequestUtils() {
		super();
	}

	public static boolean isParameterNotExist(HttpServletRequest request, String paramName) {
		return !RequestUtils.isParameterExist(request, paramName);
	}

	public static boolean isParameterExist(HttpServletRequest request, String paramName) {
		if (StringUtils.isBlank(paramName)) {
			throw new IllegalArgumentException();
		}

		String value = request.getParameter(paramName);
		return (value != null);
	}
}
