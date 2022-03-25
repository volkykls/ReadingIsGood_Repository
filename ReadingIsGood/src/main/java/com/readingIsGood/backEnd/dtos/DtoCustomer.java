package com.readingIsGood.backEnd.dtos;

import java.io.Serializable;

public class DtoCustomer implements Serializable {

	private static final long serialVersionUID = 6322965760199498997L;

	private Long id;
	private Long statu;
	private String name;
	private String surName;
	private String email;
	private String errorMessage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getStatu() {
		return statu;
	}

	public void setStatu(Long statu) {
		this.statu = statu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}