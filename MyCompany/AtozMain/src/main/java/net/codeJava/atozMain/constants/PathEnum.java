package net.codeJava.atozMain.constants;

public enum PathEnum {

	
	MAIN("main",1L),
	BANK_OPTION_LIST("bank/bank_select",2L)
	
	;
	
	private String value;
	private Long pageId;

	private PathEnum(String value, Long pageId) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.pageId = pageId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
}
