package com.readingIsGood.utils;

public enum Types {

	ADD("0"), SOLD("1");

	private final String constant;

	Types(String value) {
		this.constant = value;
	}

	public String getConstantCode() {
		return this.constant;
	}
}
