package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.readingIsGood.backEnd.dtos.DtoOrder;
import com.readingIsGood.dao.entities.EntOrder;
import com.readingIsGood.dao.repositories.RepOrder;

/**
 * @author v.keles
 *
 */
@SpringBootTest
public class Order {

	@MockBean
	RepOrder repoOrder;
	
	@Test
	public void customerOrders() {
		System.out.println("TEST List all orders of the customer");
		DtoOrder dto = new DtoOrder();
		EntOrder ent = new EntOrder();
		Long testUser = 3L;
		dto.getCustomerId().setId(testUser);
		
		Mockito.when(repoOrder.findByCustomerId(dto.getCustomerId().getId())).thenReturn(ent);
		assertEquals(ent.getCustomerId(), testUser);
		verify(repoOrder).findByCustomerId(testUser);
	}
	
}
