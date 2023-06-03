package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookService bookService;
    @InjectMocks
    private BookService bookServiceUnderTest;
    private User user;
    private Book book;

    @BeforeEach
    public void setUp() {
        bookService = new BookService();
        user = new User("Jane", "fakePassword123", "JaneDoe@gmail.com");
        book = new Book("Fake Title", "Fake Author", "Genre", 2.50);

        bookServiceUnderTest = new BookService();
        bookServiceUnderTest.addBook(book);
    }

    @AfterEach
    public void tearDown() {
        bookServiceUnderTest = null;
        user = null;
    }

    @Test
    public void searchBookTest_searchTitle() {
        String search = "Title";
        List<Book> result = bookServiceUnderTest.searchBook(search);
        assertEquals(Arrays.asList(book), result);
    }

    @Test
    public void searchBookTest_searchAuthor() {
        String search = "Author";
        List<Book> result = bookServiceUnderTest.searchBook(search);
        assertEquals(Arrays.asList(book), result);
    }

    @Test
    public void searchBookTest_searchGenre() {
        String search = "Genre";
        List<Book> result = bookServiceUnderTest.searchBook(search);
        assertEquals(Arrays.asList(book), result);
    }

    @Test
    public void searchBookTest_noExistingBook() {
        String search = "Unknown";
        List<Book> result = bookServiceUnderTest.searchBook(search);
        assertTrue(result.isEmpty());
    }

    @Test
    public void purchaseBookTest_existingBook() {
        boolean success = bookServiceUnderTest.purchaseBook(user, book);
        assertTrue(success);
    }
    @Test
    public void purchaseBookTest_bookNotAvailable() {
        boolean result = bookService.purchaseBook(user, book);
        assertFalse(result);
    }

    @Test
    public void purchaseBookTest_bookDoesNotExist() {
        Book unknownBook = new Book("Mystery Book!", "Unknown Author", "Unknown Genre", 0.00);
        boolean success = bookServiceUnderTest.purchaseBook(user, unknownBook);
        assertFalse(success);
    }
}