package org.example.books.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.books.configuration.properties.AppCacheProperties;
import org.example.books.dto.BookRequest;
import org.example.books.model.Book;
import org.example.books.model.Category;
import org.example.books.repositories.BooksRepository;
import org.example.books.repositories.CategoriesRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheManager = "redisCacheManager")
public class BooksService {
    private final BooksRepository booksRepository;
    private final CategoriesRepository categoriesRepository;

    @Cacheable(cacheNames = AppCacheProperties.CacheNames.BOOK_BY_NAME_AND_AUTHOR, key = "#bookName + #authorName")
    public Book getBookByNameAndAuthor(String bookName, String authorName) {
        if(bookName != null && authorName != null){
            return booksRepository.findBookByNameAndAuthor(bookName,authorName);
        }
        return null;
    }

    @Cacheable(cacheNames = AppCacheProperties.CacheNames.BOOKS_BY_CATEGORIES, key = "#categoryName")
    public List<Book> getBooksByCategory(String categoryName){
        return booksRepository.findBooksByCategory_Name(categoryName);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = AppCacheProperties.CacheNames.BOOK_BY_NAME_AND_AUTHOR, allEntries = true),
            @CacheEvict(cacheNames = AppCacheProperties.CacheNames.BOOKS_BY_CATEGORIES, allEntries = true)
    })
    public Book create(BookRequest bookRequest){
        Book newBook = new Book();
        newBook.setAuthor(bookRequest.getAuthorName());
        newBook.setName(bookRequest.getBookName());
        Category category = categoriesRepository.findCategoryByName(bookRequest.getCategoryName());
        if(category == null){
            Category newCategory = new Category();
            newCategory.setName(bookRequest.getCategoryName());
            category = categoriesRepository.save(newCategory);
        }
        newBook.setCategory(category);
        return booksRepository.save(newBook);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = AppCacheProperties.CacheNames.BOOK_BY_NAME_AND_AUTHOR, allEntries = true),
            @CacheEvict(cacheNames = AppCacheProperties.CacheNames.BOOKS_BY_CATEGORIES, allEntries = true)
    })
    public Book update(Integer id, BookRequest bookRequest) {
        Book book = booksRepository.findBookById(id);
        if(book != null){
            if(bookRequest.getBookName() != null) book.setName(bookRequest.getBookName());
            if(bookRequest.getAuthorName()!= null) book.setAuthor(bookRequest.getAuthorName());
            Category category = categoriesRepository.findCategoryByName(bookRequest.getCategoryName());
            if(category == null){
                Category newCategory = new Category();
                newCategory.setName(bookRequest.getCategoryName());
                category = categoriesRepository.save(newCategory);
            }
            book.setCategory(category);
        }
        return book;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = AppCacheProperties.CacheNames.BOOK_BY_NAME_AND_AUTHOR, allEntries = true),
            @CacheEvict(cacheNames = AppCacheProperties.CacheNames.BOOKS_BY_CATEGORIES, allEntries = true)
    })
    public void delete(Long id) {
        booksRepository.deleteById(id);
    }
}
