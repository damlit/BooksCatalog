package app.control;

import app.boundary.JsonMapper;
import app.entity.BookRecord;
import app.entity.BooksList;
import app.entity.IndustryIdentifiers;
import app.entity.VolumeInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BooksByIsdnTest {

    @Spy
    private JsonMapper jsonMapper = new JsonMapper();

    private static BooksList BOOKS_LIST;

    private static final String ID = "1";
    private static final String DESCRIPTION_1 = "D1";

    private static final String ISBN_13 = "ISBN_13";
    private static final String IDENTIFIER = "3";
    private static final String DESCRIPTION_2 = "D2";

    private static final String BAD_ID = "2";

    private static final String BAD_ISBN = "ISBN_12";

    private static final String ERROR = "404 - No results found";

    @Before
    public void createBookRecord() {
        BOOKS_LIST = new BooksList();
        BookRecord bookRecord = new BookRecord();
        List<BookRecord> bookRecordList = Collections.singletonList(bookRecord);
        BOOKS_LIST.setBookRecords(bookRecordList);
        BOOKS_LIST.getBookRecords().get(0).setVolumeInfo(new VolumeInfo());
        List<IndustryIdentifiers> industryIdentifiers = Collections.singletonList(new IndustryIdentifiers());
        BOOKS_LIST.getBookRecords().get(0).getVolumeInfo().setIndustryIdentifiers(industryIdentifiers);
    }

    @Test
    public void shouldFindBookWithoutIsbnType13() {
        BookRecord bookRecord = BOOKS_LIST.getBookRecords().get(0);
        bookRecord.setId(ID);
        bookRecord.getVolumeInfo().setDescription(DESCRIPTION_1);

        BookRecord bookByIsdn = BooksByIsdn.getBookByIsdn(BOOKS_LIST, ID);

        assertEquals(DESCRIPTION_1, bookByIsdn.getVolumeInfo().getDescription());
    }

    @Test
    public void shouldFindBookWithIsbnType13() {
        BookRecord bookRecord = BOOKS_LIST.getBookRecords().get(0);
        bookRecord.setId(ID);
        bookRecord.getVolumeInfo().setDescription(DESCRIPTION_2);
        bookRecord.getVolumeInfo().getIndustryIdentifiers().get(0).setType(ISBN_13);
        bookRecord.getVolumeInfo().getIndustryIdentifiers().get(0).setIdentifier(IDENTIFIER);

        BookRecord bookByIsdn = BooksByIsdn.getBookByIsdn(BOOKS_LIST, IDENTIFIER);

        assertEquals(DESCRIPTION_2, bookByIsdn.getVolumeInfo().getDescription());
    }

    @Test
    public void shouldNotFindBookWithoutIsbnWithBadId() {
        BookRecord bookRecord = BOOKS_LIST.getBookRecords().get(0);
        bookRecord.setId(BAD_ID);
        bookRecord.getVolumeInfo().setDescription(DESCRIPTION_1);

        BookRecord bookByIsdn = BooksByIsdn.getBookByIsdn(BOOKS_LIST, ID);

        assertEquals(ERROR, bookByIsdn.getError());
    }

    @Test
    public void shouldNotFindBookWithOtherIsbnIdentifier() {
        BookRecord bookRecord = BOOKS_LIST.getBookRecords().get(0);
        bookRecord.setId(ID);
        bookRecord.getVolumeInfo().setDescription(DESCRIPTION_2);
        bookRecord.getVolumeInfo().getIndustryIdentifiers().get(0).setType(ISBN_13);
        bookRecord.getVolumeInfo().getIndustryIdentifiers().get(0).setIdentifier(BAD_ID);

        BookRecord bookByIsdn = BooksByIsdn.getBookByIsdn(BOOKS_LIST, IDENTIFIER);

        assertEquals(ERROR, bookByIsdn.getError());
    }

    @Test
    public void shouldNotFindBookWithOtherIsbnType() {
        BookRecord bookRecord = BOOKS_LIST.getBookRecords().get(0);
        bookRecord.setId(ID);
        bookRecord.getVolumeInfo().setDescription(DESCRIPTION_2);
        bookRecord.getVolumeInfo().getIndustryIdentifiers().get(0).setType(BAD_ISBN);
        bookRecord.getVolumeInfo().getIndustryIdentifiers().get(0).setIdentifier(IDENTIFIER);

        BookRecord bookByIsdn = BooksByIsdn.getBookByIsdn(BOOKS_LIST, IDENTIFIER);

        assertEquals(ERROR, bookByIsdn.getError());
    }
}