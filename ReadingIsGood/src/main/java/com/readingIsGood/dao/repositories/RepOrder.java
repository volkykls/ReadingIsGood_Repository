package com.readingIsGood.dao.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.readingIsGood.dao.entities.EntOrder;

//@Transactional
//@Repository
@EnableMongoRepositories
public interface RepOrder extends MongoRepository<EntOrder, Long> {

	Optional<EntOrder> findById(Long id);// getOne also used but both of them not necessary !

	List<EntOrder> findAllGroupByDate();
	
	List<EntOrder> findAllByIdOrderByDateDesc(Long id);

	List<EntOrder> findAllByDateBetween(Date startDate, Date endDate);
	
	List<EntOrder> findAllByDateLessThanEqual(Date endDate);
	
	List<EntOrder> findAllByDateGreaterThanEqual(Date startDate);
	
	EntOrder findByCustomerId(Long orderCustomerId);

	EntOrder findByStockId(Long orderStockId);

	List<EntOrder> findByDate(Date orderDate);
}
