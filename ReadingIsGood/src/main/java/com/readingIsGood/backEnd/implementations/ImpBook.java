package com.readingIsGood.backEnd.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.readingIsGood.backEnd.dtos.DtoBook;
import com.readingIsGood.backEnd.services.SrvBook;
import com.readingIsGood.dao.entities.EntBook;
import com.readingIsGood.dao.repositories.RepBook;
import com.readingIsGood.utils.Messages;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ImpBook implements SrvBook {

	Logger log = LoggerFactory.getLogger(ImpBook.class);

	@Autowired
	RepBook repoBook;

	public DtoBook saveBook(DtoBook dto){
		EntBook ent = new EntBook();
		try {
			if (StringUtils.isNoneBlank(dto.getName())) {
//				dto.setName("Management to Success"); //Name of the book
//				dto.setCategory("Book"); //Book,NewsPaper,Magezine etc.
//				dto.setSubCategory("Governance");//Governance,Cooking,Career,Engineering,Politics etc.
//				dto.setAmount(5L);
//				dto.setDate(new Date());//Action time
//				dto.setPrice(1L);
//				dto.setType(Enums.ADD.getConstantCode()); //Add to stock or reduce(Sold)
				BeanUtils.copyProperties(dto, ent);
				repoBook.save(ent);
				log.info(Messages.success+"(saveBook) User:"+dto.getName()+" Process Time:"+new Date());
			}else {
				dto.setErrorMessage(Messages.fail+"(saveBook)");
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public DtoBook updateBook(Long id, Long amount){
		DtoBook dto = new DtoBook();
		EntBook ent = new EntBook();
		try {
			if (Objects.nonNull(id) && Objects.nonNull(amount)) {
				ent = repoBook.findById(id).orElse(null);
				if (Objects.nonNull(ent)) {
					ent.setAmount(amount);
					repoBook.save(ent);
					log.info(Messages.success+"(updateBook) Amount:"+dto.getAmount()+" Process Time:"+new Date());
				}
			}else {
				dto.setErrorMessage(Messages.fail+"(updateBook)");
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public List<DtoBook> booksInStock(DtoBook dto){
		List<DtoBook> dtos = new ArrayList<DtoBook>();
		try {
			if (Objects.nonNull(dto.getId())) {
				BeanUtils.copyProperties(repoBook.findAllByIdOrderByDateDesc(dto.getId()), dtos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
}
