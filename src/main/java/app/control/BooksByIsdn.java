package app.control;

import app.entity.BookRecord;
import app.entity.BooksList;

import java.util.Optional;

public class BooksByIsdn {

    private static final String ISBN_13 = "ISBN_13";

    public static BookRecord getBookByIsdn(BooksList booksList, String isdn) {
        Optional<BookRecord> searchedBook = getBookByIdentifier(booksList, isdn);
        if (!searchedBook.isPresent()) {
            searchedBook = getBookById(booksList, isdn);
        }
        return searchedBook
                .orElseGet(() -> new BookRecord("404 - No results found"));
    }

    private static Optional<BookRecord> getBookByIdentifier(BooksList booksList, String isdn) {
        return booksList.getBookRecords().stream()
                .filter(bookRecord -> hasCorrectIdentifier(bookRecord, isdn))
                .findAny();
    }

    private static boolean hasCorrectIdentifier(BookRecord bookRecord, String isdn) {
        return bookRecord.getVolumeInfo().getIndustryIdentifiers().stream()
                .filter(industryIdentifiers -> industryIdentifiers.getType().equals(ISBN_13))
                .anyMatch(industryIdentifiers -> industryIdentifiers.getIdentifier().equals(isdn));
    }

    private static Optional<BookRecord> getBookById(BooksList booksList, String isdn) {
        return booksList.getBookRecords().stream()
                .filter(bookRecord -> bookRecord.getId().equals(isdn))
                .findAny();
    }
}