package com.readingIsGood.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.readingIsGood.dao.entities.EntCustomer;

//@Transactional
//@Repository
@EnableMongoRepositories
public interface RepCustomer extends MongoRepository<EntCustomer, Long> {

	EntCustomer findByName(String name);
	boolean findByEmail(String eMail); 
	
}
