package app.boundary;

import app.control.AuthorsRating;
import app.control.BooksByCategory;
import app.control.BooksByIsdn;
import app.entity.Author;
import app.entity.BookRecord;
import app.entity.BooksList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndpointsList {

    private static final BooksList BOOKS_LIST = HomeworkApplication.jsonMapper.getBooksList();

    @RequestMapping("/api/book/{isdn}")
    public BookRecord getBookWithIsdn(
            @PathVariable String isdn) {
        return BooksByIsdn.getBookByIsdn(BOOKS_LIST, isdn);
    }

    @RequestMapping("/api/category/{categoryName}/books")
    public List<BookRecord> getBooksWithCategirues(
            @PathVariable String categoryName) {
        return BooksByCategory.getBooksByCategory(BOOKS_LIST, categoryName);
    }

    @RequestMapping("/api/rating")
    public List<Author> getBooksWithIsdn() {
        return AuthorsRating.getAuthorsSortedByRating(BOOKS_LIST);
    }
}