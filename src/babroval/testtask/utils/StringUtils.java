package babroval.testtask.utils;

public final class StringUtils {

	public static final String EMPTY_STR = "";

	public StringUtils() {
		super();
	}

	public static boolean isEmpty(String str) {
		if (str != null) {
			return EMPTY_STR.equals(str);
		}

		return true;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(String str) {
		if (isNotEmpty(str)) {
			return EMPTY_STR.equals(str.trim());
		}

		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static Integer getIntegerOrDefaultParam(String paramName, String dflt) {
		if (StringUtils.isEmpty(paramName)) {
			paramName = dflt;
		}

		try {
			return new Integer(paramName);
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer getIntegerParam(String paramName) {

		try {
			return new Integer(paramName);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getStringOrDefaultParam(String paramName, String defaultSort) {
		if (StringUtils.isEmpty(paramName)) {
			return defaultSort;
		}
		return paramName;
	}

}
