package net.codeJava.atozMain.constants;

public enum SwalAlertIconTypeEnum {

	SUCCESS("success"),
	ERROR("error"),
	WARNING("warning"),
	INFO("info"),
	QUESTION("question"),
	
	;
	
	private String typeName;
	
	private  SwalAlertIconTypeEnum(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
