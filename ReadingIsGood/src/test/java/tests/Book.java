package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.readingIsGood.backEnd.dtos.DtoBook;
import com.readingIsGood.dao.entities.EntBook;
import com.readingIsGood.dao.repositories.RepBook;

/**
 * @author v.keles
 *
 */
@SpringBootTest
public class Book {

	@MockBean
	RepBook repoBook;
	
	@Test
	public void track() {
		System.out.println("TEST Tracking the stock of books.");
		DtoBook dto = new DtoBook();
		EntBook ent = new EntBook();
		dto.setName("Management to Success");
		
		Mockito.when(repoBook.findByName(dto.getName())).thenReturn(ent);
		assertEquals(ent.getName(), dto.getName());
		verify(repoBook).findByName(dto.getName());
		
	}
}
