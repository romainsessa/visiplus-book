package fr.visiplus.book.services;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.visiplus.book.entities.Book;
import fr.visiplus.book.entities.BookStatus;
import fr.visiplus.book.entities.Reservation;

@SpringBootTest
public class ReservationServiceTest {
	
	@Autowired
	ReservationService reservationService;	
	
	@Test
	public void testisReservationMoreThan() {
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
		
		//Act
		boolean result = reservationService.isReservationMoreThan(reservation, 1);
		
		//Assert
		assertFalse(result);		
	}

}
