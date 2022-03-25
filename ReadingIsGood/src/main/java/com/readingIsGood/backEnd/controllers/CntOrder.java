package com.readingIsGood.backEnd.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.readingIsGood.backEnd.dtos.DtoOrder;
import com.readingIsGood.backEnd.services.SrvOrder;
import com.readingIsGood.utils.Messages;

@RestController
public class CntOrder {

	Logger log = LoggerFactory.getLogger(CntOrder.class);

	@Autowired
	SrvOrder srvOrder;
	
	@PostMapping(value = "/saveOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DtoOrder saveOrderInfo(@RequestBody DtoOrder dto) {
		log.info("saveOrder"+Messages.inProcess);
		if (dto.getAmount()>0) {
			dto.setErrorMessage("You can not buy -1 items");
			return null;
		}
		
		return srvOrder.saveOrder(dto);
	}
	
	@PostMapping(value = "/updateOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DtoOrder updateOrderInfo(@RequestBody Long id, Long amount) {
		log.info("updateOrder"+Messages.inProcess);
		return srvOrder.updateOrder(id, amount);
	}

	@PostMapping(value = "/getOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DtoOrder getOrderInfo(@RequestBody Long id) {
		log.info("getOrder"+Messages.inProcess);
		return srvOrder.getOrder(id);
	}
	
	@GetMapping(value = "/ordersOfCustomer/{customerId}")
	public List<DtoOrder> ordersOfCustomerInfo(@PathVariable Long customerId) {
		log.info("ordersOfCustomer"+Messages.inProcess);
		return srvOrder.ordersOfCustomer(customerId);
	}
	
	@GetMapping(value = "/allOrder")
	public List<DtoOrder> allOrderInfo() {
		log.info("allOrder"+Messages.inProcess);
		return srvOrder.allSavedOrder();
	}
	
	@PostMapping(value = "/rangeOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DtoOrder> rangeOrderInfo(@RequestBody Date startDate, Date endDate ) {
		log.info("rangeOrder"+Messages.inProcess);
		return srvOrder.rangeOrder(startDate, endDate);
	}
	
	@PostMapping(value = "/viewOrderDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DtoOrder viewOrderDetailInfo(@RequestBody DtoOrder dto) {
		log.info("viewOrderDetail"+Messages.inProcess);
		try {
			return srvOrder.viewOrderDetail(dto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}

}
