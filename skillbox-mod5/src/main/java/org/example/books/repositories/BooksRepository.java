package org.example.books.repositories;


import org.example.books.model.Book;
import org.example.books.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {

    Book findBookById(Integer id);
    Book findBookByNameAndAuthor(String bookName, String authorName);
    List<Book> findBooksByCategory_Name(String categoryName);
//    void deleteBookById(Integer id);
}
