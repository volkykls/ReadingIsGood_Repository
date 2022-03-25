package com.readingIsGood.backEnd.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.readingIsGood.backEnd.dtos.DtoCustomer;
import com.readingIsGood.backEnd.dtos.DtoOrder;
import com.readingIsGood.backEnd.services.SrvOrder;
import com.readingIsGood.dao.entities.EntOrder;
import com.readingIsGood.dao.repositories.RepBook;
import com.readingIsGood.dao.repositories.RepCustomer;
import com.readingIsGood.dao.repositories.RepOrder;
import com.readingIsGood.utils.Messages;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ImpOrder implements SrvOrder {

	Logger log = LoggerFactory.getLogger(ImpOrder.class);

	@Autowired
	RepOrder repoOrder;
	
	@Autowired
	RepCustomer repoCustomer;
	
	@Autowired
	RepBook repoBook;

	public DtoOrder saveOrder(DtoOrder dto){
		EntOrder ent = new EntOrder();
		try {
			if (Objects.nonNull(dto.getBookId()) && Objects.nonNull(dto.getCustomerId())) {
				if (dto.getAmount()>0 && Objects.nonNull(dto.getAmount())) {
					BeanUtils.copyProperties(dto, ent);
					repoOrder.save(ent);
					log.info(Messages.success+"(saveOrder) User:"+dto.getBookId()+" - "+dto.getCustomerId()+" Process Time:"+new Date());
				}
			}else {
				dto.setErrorMessage(Messages.fail+"(saveOrder)");
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public DtoOrder updateOrder(Long id, Long amount){
		DtoOrder dto = new DtoOrder();
		EntOrder ent = new EntOrder();
		try {
			if (amount<1) {
//				cancel the order !
				return dto;
			}
			if (Objects.nonNull(id) && Objects.nonNull(amount)) {
				ent = repoOrder.findById(id).orElse(null);
				if (Objects.nonNull(ent)) {
					ent.setAmount(amount);
					repoOrder.save(ent);
					log.info(Messages.success+"(updateOrder) OrderId:"+dto.getId()+" - "+"Amount:"+dto.getAmount()+" Process Time:"+new Date());
				}
			}else {
				dto.setErrorMessage(Messages.fail+"(updateOrder)");
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public DtoOrder getOrder(Long id){
		DtoOrder dto = new DtoOrder();
		try {
			if (Objects.nonNull(id)) {
				BeanUtils.copyProperties(repoOrder.findById(id), dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public List<DtoOrder> ordersOfCustomer(Long customerId){
		List<DtoOrder> dtos = new ArrayList<DtoOrder>();
		try {
			if (Objects.nonNull(customerId)) {
				BeanUtils.copyProperties(repoOrder.findAllByIdOrderByDateDesc(customerId), dtos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	public List<DtoOrder> allSavedOrder(){
		List<DtoOrder> dtos = new ArrayList<DtoOrder>();
		try {
			BeanUtils.copyProperties(repoOrder.findAll(), dtos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	public List<DtoOrder> rangeOrder(Date startDate, Date endDate){
		List<DtoOrder> dtos = new ArrayList<DtoOrder>();
		try {
			if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
				BeanUtils.copyProperties(repoOrder.findAllByDateBetween(startDate, endDate), dtos);
			}else if(Objects.nonNull(endDate)){
				BeanUtils.copyProperties(repoOrder.findAllByDateLessThanEqual(endDate), dtos);
			}else if(Objects.nonNull(startDate)){
				BeanUtils.copyProperties(repoOrder.findAllByDateGreaterThanEqual(startDate), dtos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	public DtoOrder viewOrderDetail(DtoOrder dto){
		DtoCustomer dtoCust = new DtoCustomer();
		try {
			if (Objects.nonNull(dto.getId())) {
				dto = getOrder(dto.getId());

				BeanUtils.copyProperties(repoCustomer.findById(dto.getCustomerId().getId()), dtoCust);
				
				String bookList = "";
				for (int i=0; dto.getBookId().size()>0; i++) {
					bookList += dto.getBookId().get(i);
				}
				
				log.info(Messages.success+"(viewOrderDetail) OrderId:"+dto.getId()+" -"
					+" Customer Name:"+dtoCust.getName()+" - "
					+" Book Name:"+bookList+" - "
					+" Process Time:"+new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public List<DtoOrder> monthlyOrder(){
		List<DtoOrder> dtos = new ArrayList<DtoOrder>();
		try {
			BeanUtils.copyProperties(repoOrder.findAllByDateBetween(
					new GregorianCalendar(Calendar.YEAR, Calendar.JANUARY, 1).getTime(), 
					new GregorianCalendar(Calendar.YEAR, Calendar.DECEMBER, 31).getTime() ), 
					dtos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	/**
	 * Result shown on the Console !
	 */
	public List<DtoOrder> monthlyReportTable() {
		log.info("monthlyOrder"+Messages.inProcess);
		List<DtoOrder> dtoOrds = null;
		List<DtoOrder> dtoReport = null;
		
		try {
			List<DtoOrder> dtoAllOrd = monthlyOrder();
			dtoOrds = distinctAndSort(dtoAllOrd);
			
			for (DtoOrder dtoOrder : dtoOrds) {
				float countOfOrder = 0;
				float countOfBook = 0;
				float countOfPurchased = 0;
				for(int i=0; dtoAllOrd.size()>0; i++) {
					if (dtoAllOrd.get(i).getDateCriteria().equals(dtoOrder.getDateCriteria())) {
						countOfOrder++;
						countOfBook+=dtoAllOrd.get(i).getBookId().size();
						countOfPurchased+=dtoAllOrd.get(i).getAmount();
					}
				}
				
				System.out.println("Month:"+dtoOrder.getDateCriteria()
					+"Total Order Count:"+countOfOrder
					+"Total Book Count:"+countOfBook
					+"Total Purchased Amount:"+countOfPurchased
					);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return dtoReport;
	}
	
	public List<DtoOrder> distinctAndSort(List<DtoOrder> dtoOrds){
		List<DtoOrder> resultEnts = new ArrayList<DtoOrder>();
		
		List<DtoOrder> entsDistinct = dtoOrds.stream().filter(
				distinctByKey(p-> p.getDateCriteria())).collect(Collectors.toList());
		
		resultEnts = entsDistinct.stream().
				sorted((o1, o2)->(o1.getDateCriteria()).
                    compareTo(o2.getDateCriteria())).collect(Collectors.toList());

		return resultEnts;
	}

	public static <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		  
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
	}
	
}