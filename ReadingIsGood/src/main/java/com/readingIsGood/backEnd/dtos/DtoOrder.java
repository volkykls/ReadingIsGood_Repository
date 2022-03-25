package com.readingIsGood.backEnd.dtos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.readingIsGood.dao.entities.EntBook;
import com.readingIsGood.dao.entities.EntCustomer;

public class DtoOrder implements Serializable {

	private static final long serialVersionUID = 6322965760199498997L;

	private Long id;
	private Long statu;
	private Long amount;
	private EntCustomer customerId;
	List<EntBook> bookId = new ArrayList<>();
	private Date date;
	private String dateCriteria;
	private String errorMessage;

	public static String dateMontly(Date fullDate) {
		Locale locale = new Locale("tr", "TR");
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMM", locale);
		String monthText = sdfDay.format(fullDate);
		return monthText;
	}
	
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getStatu() {
		return statu;
	}

	public void setStatu(Long statu) {
		this.statu = statu;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getDateCriteria() {
		return dateCriteria;
	}

	public void setDateCriteria(String dateCriteria) {
		this.dateCriteria = dateMontly(getDate());
	}

	public EntCustomer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(EntCustomer customerId) {
		this.customerId = customerId;
	}

	public List<EntBook> getBookId() {
		return bookId;
	}

	public void setBookId(List<EntBook> bookId) {
		this.bookId = bookId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}