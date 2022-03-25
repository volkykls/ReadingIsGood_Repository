package com.readingIsGood.backEnd.implementations;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.readingIsGood.backEnd.dtos.DtoCustomer;
import com.readingIsGood.backEnd.services.SrvCustomer;
import com.readingIsGood.dao.entities.EntCustomer;
import com.readingIsGood.dao.repositories.RepCustomer;
import com.readingIsGood.utils.Messages;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ImpCustomer implements SrvCustomer {

	Logger log = LoggerFactory.getLogger(ImpCustomer.class);

	@Autowired
	RepCustomer repoCustomer;

	public DtoCustomer saveCustomer(DtoCustomer dto){
		EntCustomer ent = new EntCustomer();
		try {
			if (StringUtils.isNoneBlank(dto.getName())) {
				if (!repoCustomer.findByEmail(dto.getEmail())) {
					BeanUtils.copyProperties(dto, ent);
					repoCustomer.save(ent);
					log.info(Messages.success+"(saveCustomer) User:"+dto.getName()+" "+dto.getSurName().toUpperCase()+
						" eMail:"+dto.getEmail()+
						" Process Time:"+new Date());
				}else {
					dto.setErrorMessage(Messages.fail+" Already Exist !(saveCustomer)");
					return dto;
				}
			}else {
				dto.setErrorMessage(Messages.fail+"(saveCustomer)");
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}