package com.readingIsGood.backEnd.services;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.readingIsGood.backEnd.dtos.DtoOrder;

@Transactional
public interface SrvOrder {

	/** Will persist new order */
	DtoOrder saveOrder(DtoOrder dto);

	/** Will update stock records */
	DtoOrder updateOrder(Long id, Long amount);
	
	/** Will query order by Id */
	DtoOrder getOrder(Long id);

	/** Will query all orders of the customer */
	List<DtoOrder> ordersOfCustomer(Long customerId);
	
	List<DtoOrder> allSavedOrder();

	/** List orders by date interval ( startDate - endDate )*/
	List<DtoOrder> rangeOrder(Date startDate, Date endDate);

	/** Viewing the order details */
	DtoOrder viewOrderDetail(DtoOrder dto);

	/** Query Monthly Statistics */
	List<DtoOrder> monthlyOrder();
	
	List<DtoOrder> monthlyReportTable();
	
	
}