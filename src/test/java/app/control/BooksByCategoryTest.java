package app.control;

import app.boundary.JsonMapper;
import app.entity.BookRecord;
import app.entity.BooksList;
import app.entity.VolumeInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BooksByCategoryTest {

    @Spy
    private JsonMapper jsonMapper = new JsonMapper();

    private static BooksList BOOKS_LIST;

    private static final String DESCRIPTION = "Good";
    private static final String HISTORY = "History";

    private static final String OTHER = "Other";

    private static final String ERROR = "404 - No results found";

    @Before
    public void createBookRecord() {
        BOOKS_LIST = new BooksList();
        BookRecord bookRecord = new BookRecord();
        List<BookRecord> bookRecordList = Collections.singletonList(bookRecord);
        BOOKS_LIST.setBookRecords(bookRecordList);
        BOOKS_LIST.getBookRecords().get(0).setVolumeInfo(new VolumeInfo());
    }

    @Test
    public void shouldFindBookWithCategory() {
        BookRecord bookRecord = BOOKS_LIST.getBookRecords().get(0);
        bookRecord.getVolumeInfo().setCategories(Collections.singletonList(HISTORY));
        bookRecord.getVolumeInfo().setDescription(DESCRIPTION);

        List<BookRecord> bookByCategory = BooksByCategory.getBooksByCategory(BOOKS_LIST, HISTORY);

        assertTrue(!bookByCategory.isEmpty());
        assertEquals(1, bookByCategory.size());
        assertEquals(DESCRIPTION, bookByCategory.get(0).getVolumeInfo().getDescription());
    }

    @Test
    public void shouldNotFindBookWithOtherCategory() {
        BookRecord bookRecord = BOOKS_LIST.getBookRecords().get(0);
        bookRecord.getVolumeInfo().setCategories(Collections.singletonList(OTHER));

        List<BookRecord> bookByCategory = BooksByCategory.getBooksByCategory(BOOKS_LIST, HISTORY);

        assertTrue(!bookByCategory.isEmpty());
        assertEquals(1, bookByCategory.size());
        assertEquals(ERROR, bookByCategory.get(0).getError());
    }
}