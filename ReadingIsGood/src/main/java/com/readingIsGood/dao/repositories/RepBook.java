package com.readingIsGood.dao.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.readingIsGood.dao.entities.EntBook;

//MongoRepository
//@Transactional
//@Repository
@SpringBootApplication
public interface RepBook extends MongoRepository<EntBook, Long> {

	EntBook findByName(String name);

	EntBook findByAmount(Long amount);

	List<EntBook> findAllByIdOrderByDateDesc(Long id);
	
	List<EntBook> findByCategory(String category);

	List<EntBook> findBySubCategory(String subCategory);

	List<EntBook> findByDate(Date orderDate);
}
