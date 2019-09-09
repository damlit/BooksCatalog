package app.control;

import app.boundary.JsonMapper;
import app.entity.Author;
import app.entity.BookRecord;
import app.entity.BooksList;
import app.entity.VolumeInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AuthorsRatingTest {

    @Spy
    private JsonMapper jsonMapper = new JsonMapper();

    private static BooksList BOOKS_LIST;

    private static final String AUTHOR_1 = "Author1";
    private static final Double AVERAGE_11 = 5.0;
    private static final Double AVERAGE_12 = 3.0;

    private static final String AUTHOR_2 = "Author2";
    private static final Double AVERAGE_21 = 1.0;

    private static final Double AUTHOR_1_AVERAGE = 4.0;

    @Before
    public void createBookRecord() {
        BOOKS_LIST = new BooksList();
        BookRecord bookRecord = new BookRecord();
        BookRecord secondBookRecord = new BookRecord();
        BookRecord thirdBookRecord = new BookRecord();
        List<BookRecord> bookRecordList = Arrays.asList(bookRecord, secondBookRecord, thirdBookRecord);
        BOOKS_LIST.setBookRecords(bookRecordList);
        BOOKS_LIST.getBookRecords().get(0).setVolumeInfo(new VolumeInfo());
        BOOKS_LIST.getBookRecords().get(1).setVolumeInfo(new VolumeInfo());
        BOOKS_LIST.getBookRecords().get(2).setVolumeInfo(new VolumeInfo());
    }

    @Test
    public void shouldCalculateFirstAuthorAverage() {
        BookRecord bookRecord = BOOKS_LIST.getBookRecords().get(0);
        BookRecord secondBookRecord = BOOKS_LIST.getBookRecords().get(1);
        BookRecord thirdBookRecord = BOOKS_LIST.getBookRecords().get(2);

        String[] authorArray = new String[] {AUTHOR_1};
        String[] secondAuthorArray = new String[] {AUTHOR_2};

        bookRecord.getVolumeInfo().setAuthors(authorArray);
        bookRecord.getVolumeInfo().setAverageRating(AVERAGE_11);
        secondBookRecord.getVolumeInfo().setAuthors(secondAuthorArray);
        secondBookRecord.getVolumeInfo().setAverageRating(AVERAGE_21);
        thirdBookRecord.getVolumeInfo().setAuthors(authorArray);
        thirdBookRecord.getVolumeInfo().setAverageRating(AVERAGE_12);

        List<Author> calculateAverage = AuthorsRating.getAuthorsSortedByRating(BOOKS_LIST);

        assertTrue(!calculateAverage.isEmpty());
        assertEquals(2, calculateAverage.size());
        assertEquals(AUTHOR_1_AVERAGE, calculateAverage.get(0).getAverageRating());
    }
}