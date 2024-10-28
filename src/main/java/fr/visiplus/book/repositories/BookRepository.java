package fr.visiplus.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.visiplus.book.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
