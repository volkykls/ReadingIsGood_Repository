package com.readingIsGood.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
//@Table(name = "COLL_ORDER")
@Document(collection = "COLL_ORDER")
public class EntOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "STATU")
	private Long statu;

	@Column(name = "AMOUNT")
	private Long amount;
	
	@OneToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private EntCustomer customerId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "BOOK_ID")
	List<EntBook> bookId = new ArrayList<>();

	@Column(name = "DATE")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
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

}