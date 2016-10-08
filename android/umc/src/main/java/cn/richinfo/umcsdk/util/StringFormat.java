package cn.richinfo.umcsdk.util;

/**
 * @author JeremyTang
 * @version 1.2.0
 * @title 字符串格式化
 * @copyright Copyright (c) 2016
 * @company
 * @date ${date} ${tags}
 */
public class StringFormat {

	private static final int ROW_LENGTH = 58;

	public static String logcatFormat(String resource) {
		if (resource.contains(",") || (resource.contains(";"))) {
			return jsonFormat(resource);
		}
		return strngFormat(resource);
	}

	/**
	 * 类json文件格式转换
	 * 
	 * @param resource
	 * @return
	 */
	private static String jsonFormat(String resource) {
		resource = resource.replace("\n", "").substring(1,
				resource.length() - 1);
		String[] strArray = null;
		if (resource.contains(",")) {
			strArray = resource.split(",");
		} else if (resource.contains(";")) {
			strArray = resource.split(";");
		}
		StringBuffer sb = new StringBuffer();
		for (String s : strArray) {
			String[] values = s.split(":");
			sb.append("【" + values[0].substring(1, values[0].length() - 1)
					+ "】：" + values[1].substring(1, values[1].length() - 1)
					+ "\n");
		}
		return sb.toString();
	}

	/**
	 * String 字符串切换
	 * 
	 * @param resource
	 * @return
	 */
	private static String strngFormat(String resource) {
		resource = resource.replace("\n", "").trim();
		StringBuffer sb = new StringBuffer();
		int rowNumber = 0;
		while (true) {
			int start = rowNumber * ROW_LENGTH;
			int end = (rowNumber + 1) * ROW_LENGTH;
			if (end >= resource.length()) {
				sb.append(resource.substring(start, resource.length()));
				break;
			} else {
				sb.append(resource.substring(start, end) + "\n");
			}
			rowNumber++;
		}
		return sb.toString();
	}

}
