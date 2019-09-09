package app.control;

import app.entity.BookRecord;
import app.entity.BooksList;

import java.util.List;
import java.util.stream.Collectors;

public class BooksByCategory {

    public static List<BookRecord> getBooksByCategory(BooksList booksList, String searchedCategory) {
        List<BookRecord> booksWithTheCorrectCategory = getBooksWithTheCorrectCategory(booksList, searchedCategory);
        if (booksWithTheCorrectCategory.isEmpty()) {
            booksWithTheCorrectCategory.add(new BookRecord("404 - No results found"));
        }
        return booksWithTheCorrectCategory;
    }

    private static List<BookRecord> getBooksWithTheCorrectCategory(BooksList booksList, String searchedCategory) {
        return booksList.getBookRecords().stream()
                .filter(book -> book.getVolumeInfo().getCategories() != null)
                .filter(book -> isBookWithTheCorrectCategory(book, searchedCategory))
                .collect(Collectors.toList());
    }

    private static boolean isBookWithTheCorrectCategory(BookRecord bookRecord, String searchedCategory) {
        return bookRecord.getVolumeInfo().getCategories().stream()
                .anyMatch(category -> category.equals(searchedCategory));
    }
}