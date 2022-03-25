package com.readingIsGood.backEnd.services;

import org.springframework.transaction.annotation.Transactional;

import com.readingIsGood.backEnd.dtos.DtoCustomer;

@Transactional
public interface SrvCustomer {
	/** Will persist new customers */
	DtoCustomer saveCustomer(DtoCustomer dto);

}