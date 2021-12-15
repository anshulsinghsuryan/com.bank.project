package com.bank.application.utility;

import com.bank.application.enums.FileConstants;

public class ValidateFile {

	public static boolean isFileNameValid(String fileName) {
		if(fileName.endsWith(FileConstants.JPG_FILE.getStrValue()) 
				|| fileName.endsWith(FileConstants.PNG_FILE.getStrValue())) {
			return true;
		} else if(fileName.endsWith(FileConstants.PDF_FILE.getStrValue())) {
			return true;
		}
		return false;
	}
	public static boolean isSizeValid(long size) {
		if(size <= FileConstants.MAX_FILE_SIZE.getIntValue()) {
			return true;
		}
		return false;
	}
}
