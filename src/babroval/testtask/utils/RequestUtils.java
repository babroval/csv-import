package babroval.testtask.utils;

import javax.servlet.http.HttpServletRequest;

public final class RequestUtils {

	private RequestUtils() {
		throw new AssertionError("Class contains static methods only. You should not instantiate it!");
	}

	public static boolean isParameterNotExist(HttpServletRequest request, String paramName) {
		return !RequestUtils.isParameterExist(request, paramName);
	}

	public static boolean isParameterExist(HttpServletRequest request, String paramName) {
		if (StringUtils.isBlank(paramName)) {
			throw new IllegalArgumentException("Request parameter is blank");
		}

		String value = request.getParameter(paramName);
		return (value != null);
	}
}
