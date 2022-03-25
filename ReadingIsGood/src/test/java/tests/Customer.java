package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.readingIsGood.backEnd.dtos.DtoCustomer;
import com.readingIsGood.dao.entities.EntCustomer;
import com.readingIsGood.dao.repositories.RepCustomer;

/**
 * @author v.keles
 *
 */
@SpringBootTest
public class Customer{

	@MockBean
	RepCustomer repoCustomer;
	
	@Test
	public void register() {
		System.out.println("TEST Registering New Customer");
		DtoCustomer dto = new DtoCustomer();
		EntCustomer ent = new EntCustomer();
		dto.setName("Volkan");
		dto.setSurName("Keles");
		
		Mockito.when(repoCustomer.findByName(dto.getName())).thenReturn(ent);
		assertEquals(ent.getName(), dto.getName());
		verify(repoCustomer).findByName(dto.getName());
	}

}
