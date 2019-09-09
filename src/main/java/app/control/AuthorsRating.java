package app.control;

import app.entity.Author;
import app.entity.BooksList;
import app.entity.PairAuthorAndAverage;
import app.entity.VolumeInfo;

import java.util.*;
import java.util.stream.Collectors;

public class AuthorsRating {

    private static Set<String> UNIQUE_AUTHOR_NAMES = new HashSet<>();
    private static List<PairAuthorAndAverage> AUTHORS_WITH_AVERAGE = new ArrayList<>();

    public static List<Author> getAuthorsSortedByRating(BooksList booksList) {
        getAuthorNames(booksList);
        setAverageForAuthors(booksList);
        List<Author> authors = fillAuthorsAverage(createAuthorsEntity());
        authors.forEach(Author::calculateAverageRating);
        return rejectAuthorsWithoutAverage(authors);

    }

    private static void getAuthorNames(BooksList booksList) {
        booksList.getBookRecords().stream()
                .map(bookRecord -> bookRecord.getVolumeInfo().getAuthorsInList())
                .forEach(authorNames -> UNIQUE_AUTHOR_NAMES.addAll(authorNames));
    }

    private static List<Author> createAuthorsEntity() {
        return UNIQUE_AUTHOR_NAMES.stream()
                .map(Author::new)
                .collect(Collectors.toList());
    }

    private static void setAverageForAuthors(BooksList booksList) {
        booksList.getBookRecords().stream()
                .filter(bookRecord -> bookRecord.getVolumeInfo().getAverageRating() != null)
                .forEach(bookRecord -> getAverageFromVolumeInfo(bookRecord.getVolumeInfo()));
    }

    private static void getAverageFromVolumeInfo(VolumeInfo volumeInfo) {
        volumeInfo.getAuthorsInList()
                .forEach(author -> AUTHORS_WITH_AVERAGE.add(new PairAuthorAndAverage(author, volumeInfo.getAverageRating())));
    }

    private static List<Author> fillAuthorsAverage(List<Author> authors) {
        AUTHORS_WITH_AVERAGE.forEach(authorsWithAverage ->
                fillAverage(authors, authorsWithAverage.getAuthorName(), authorsWithAverage.getAverage())
        );
        return authors;
    }

    private static void fillAverage(List<Author> authors, String authorName, Double average) {
        authors.stream()
                .filter(author -> authorName.equals(author.getName()))
                .forEach(author -> author.addToRating(average));
    }

    private static List<Author> rejectAuthorsWithoutAverage(List<Author> authors) {
        return authors.stream()
                .filter(author -> author.getAverageRating() != 0)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }
}