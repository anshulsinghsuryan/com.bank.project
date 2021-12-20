package com.bank.application.utility;

public class CommonUtils {

	public static String getLogMessage(Exception ex) {
		StringBuilder str = new StringBuilder();
		StackTraceElement[] stack = ex.getStackTrace();
		str.append("\n");
		str.append(stack[0]);
		return str.toString();
	}
}
