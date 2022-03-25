package com.readingIsGood.backEnd.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.readingIsGood.backEnd.dtos.DtoBook;

@Transactional
public interface SrvBook {

	/** Will persist new book */
	DtoBook saveBook(DtoBook dto);

	/** Will update book’s stock */
	DtoBook updateBook(Long id, Long amount);

	/** Tracking the stock of books */
	List<DtoBook> booksInStock(DtoBook dto);

}