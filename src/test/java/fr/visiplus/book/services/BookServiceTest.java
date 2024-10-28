package fr.visiplus.book.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.visiplus.book.entities.Book;
import fr.visiplus.book.entities.BookStatus;
import fr.visiplus.book.entities.Reservation;

@SpringBootTest
public class BookServiceTest {
	
	@MockBean
	private ReservationService reservationService;
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void testIsNotGetLast21Days() {
		//Arrange
		Book book = new Book();
		book.setId(Long.valueOf(1));
		book.setName("TestBook");
		book.setDescription("Not relevant");
		book.setStatus(BookStatus.BOOKED);
		
		Reservation reservation = new Reservation();
		reservation.setId(Long.valueOf(1));
		reservation.setBook(book);
		reservation.setDateResa(LocalDate.now());
		
		book.setReservation(reservation);
		
		Mockito.when(reservationService.isReservationMoreThan(reservation, 21)).thenReturn(true);
		
		//Act
		boolean result = bookService.isNotGetLast21Days(book);		
		
		//Assert
		assertTrue(result);		
        Mockito.verify(reservationService).isReservationMoreThan(reservation, 21);

	}
	
	@Test
	public void testIsNotGetLast21DaysBookNotBooked() {
		//Arrange
		Book book = new Book();
		book.setId(Long.valueOf(1));
		book.setName("TestBook");
		book.setDescription("Not relevant");
		book.setStatus(BookStatus.AVAILABLE);
		
		Reservation reservation = new Reservation();
		reservation.setId(Long.valueOf(1));
		reservation.setBook(book);
		reservation.setDateResa(LocalDate.now());
		
		book.setReservation(reservation);
				
		//Act
		boolean result = bookService.isNotGetLast21Days(book);		
		
		//Assert
		assertFalse(result);
  	}
	
	

}
