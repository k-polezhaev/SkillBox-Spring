package org.example.books.controllers;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.example.books.dto.BookRequest;
import org.example.books.dto.BookResponse;
import org.example.books.mappers.BookMapper;
import org.example.books.model.Book;
import org.example.books.services.BooksService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BooksController {

    private final BooksService booksService;
    private final BookMapper bookMapper;
    @GetMapping()
    public BookResponse getBookByNameAndAuthor(@PathParam("bookName") String bookName, @PathParam("authorName") String authorName){
        return bookMapper.bookToResponse(
                booksService.getBookByNameAndAuthor(bookName, authorName)
        );
    }

    @GetMapping("/byCategoryName")
    public List<BookResponse> getBooksByCategory(@PathParam("categoryName") String categoryName){
        return bookMapper.booksToResponse(
                booksService.getBooksByCategory(categoryName)
        );
    }

    @PostMapping()
    public BookResponse createBook(@RequestBody BookRequest request){
        return bookMapper.bookToResponse(
                booksService.create(request));
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Integer id, @RequestBody BookRequest request){
        return bookMapper.bookToResponse(
                booksService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        booksService.delete(id);
    }




}
