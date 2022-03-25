package com.readingIsGood.backEnd.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.readingIsGood.backEnd.dtos.DtoCustomer;
import com.readingIsGood.backEnd.services.SrvCustomer;
import com.readingIsGood.utils.Messages;

@RestController
public class CntCustomer {

	Logger log = LoggerFactory.getLogger(CntCustomer.class);

	@Autowired
	SrvCustomer srvCustomer;
	
	@PostMapping(value = "/saveCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DtoCustomer saveCustomerInfo(@RequestBody DtoCustomer dto) {
		log.info("saveCustomer"+Messages.inProcess);
		return srvCustomer.saveCustomer(dto);
	}
}
