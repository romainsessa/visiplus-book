package fr.visiplus.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.visiplus.book.entities.Reservation;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
