package com.example.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetuUtils {

	
	public static Double getDoubleValue(Object s) {
		try {
			return s != null ? Double.parseDouble(s.toString()) : new Double(0);
		} catch (NumberFormatException e) {
			return new Double(0);
		}
	}
	
	
	public static String getStringValue(Object s) {
		try {
			return s != null ? s.toString() : "";
		} catch (NumberFormatException e) {
			return "";
		}
	}
	
	public static final String generateUnique(String prefix, String format) {
		return prefix+getCurrentDateInFormat(format);
	}
	
	
	public static final String getCurrentDateInFormat(String format) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String strDate = formatter.format(date);
		return strDate+System.currentTimeMillis();
	}
	
	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}
	
	
	public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {

		Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		String parsedDate = formatter.format(initDate);

		return parsedDate;
	}
}
