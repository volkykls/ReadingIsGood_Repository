package com.readingIsGood.utils;

public enum Status {

	ACTIVE(0L), PASIVE(1L);

	private final Long statu;

	Status(Long value) {
		this.statu = value;
	}

	public Long getStatuCode() {
		return this.statu;
	}
}
