package com.readingIsGood.backEnd.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingIsGood.backEnd.dtos.DtoOrder;
import com.readingIsGood.backEnd.services.SrvOrder;
import com.readingIsGood.utils.Messages;

@RestController
public class CntStatistic {

	Logger log = LoggerFactory.getLogger(CntStatistic.class);

	@Autowired
	SrvOrder srvOrder;
	
	@GetMapping(value = "/monthlyOrder")
	public List<DtoOrder> monthlyOrderInfo() {
		log.info("monthlyOrder"+Messages.inProcess);

		return srvOrder.monthlyReportTable();
	}
	
}
