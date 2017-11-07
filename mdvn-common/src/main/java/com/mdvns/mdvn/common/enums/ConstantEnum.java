package com.mdvns.mdvn.common.enums;

public enum ConstantEnum {
	
	ZEROR("0"),ONE("1"),TWO("2"),THREE("3"), FOUR("4"),FIVE("5"),SIX("6"),SEVEEN("7"),EIGHT("8"),NINE("9"),TEN("10"),SPRIT("/");
	
	String value;
	
	private ConstantEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
