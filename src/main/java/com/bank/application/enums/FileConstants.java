package com.bank.application.enums;

public enum FileConstants {

	PROFILE_PATH("profile"),
	ADHAR_PATH("adhar"),
	PAN_PATH("pan"),
	SIGNATURE_PATH("signature"),
	VOTERIDCARD_PATH("voteridcard"),
	DATA_FOLDER("data"),
	PDF_FILE("pdf"),
	JPG_FILE("jpg"),
	PNG_FILE("png"),
	MAX_FILE_SIZE(5242880),
	;

	private String str;
	private long value;
	
	FileConstants(String string) {
		this.str = string;
	}
	
	FileConstants(long value) {
		this.value = value;
	}

	public long getIntValue() {
		return value;
	}
	
	public String getStrValue() {
		return str;
	}
}
