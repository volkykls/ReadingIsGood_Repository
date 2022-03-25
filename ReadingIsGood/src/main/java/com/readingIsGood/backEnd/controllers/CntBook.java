package com.readingIsGood.backEnd.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.readingIsGood.backEnd.dtos.DtoBook;
import com.readingIsGood.backEnd.services.SrvBook;
import com.readingIsGood.utils.Messages;

@RestController
public class CntBook {

	Logger log = LoggerFactory.getLogger(CntBook.class);

	@Autowired
	SrvBook srvBook;

	@PostMapping(value = "/saveBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DtoBook saveBookInfo(@RequestBody DtoBook dto) {
		log.info("saveBook"+Messages.inProcess);
		return srvBook.saveBook(dto);
	}
	
	@PostMapping(value = "/updateBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DtoBook updateBookInfo(@RequestBody Long id, Long amount) {
		log.info("updateBook"+Messages.inProcess);
		return srvBook.updateBook(id, amount);
	}
	
	@PostMapping(value = "/booksInStock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DtoBook> booksInStockInfo(@RequestBody DtoBook dto) {
		log.info("booksInStock"+Messages.inProcess);
			return srvBook.booksInStock(dto);
	}
	
}
