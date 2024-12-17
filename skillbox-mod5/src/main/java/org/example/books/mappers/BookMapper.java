package org.example.books.mappers;

import org.example.books.dto.BookResponse;
import org.example.books.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {
    public List<BookResponse> booksToResponse(List<Book> books) {
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book : books) {
//            BookResponse bookResponse = new BookResponse();
//            bookResponse.setId(book.getId());
//            bookResponse.setBookName(book.getName());
//            bookResponse.setAuthorName(book.getAuthor());
//            bookResponse.setCategoryName(book.getCategory().getName());
            BookResponse bookResponse = bookToResponse(book);
            bookResponses.add(bookResponse);
        }
        return bookResponses;
    }

    public BookResponse bookToResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        if(book != null) {
            bookResponse.setId(book.getId());
            bookResponse.setBookName(book.getName());
            bookResponse.setAuthorName(book.getAuthor());
            bookResponse.setCategoryName(book.getCategory().getName());
            return bookResponse;
        }
        return null;
    }


}
