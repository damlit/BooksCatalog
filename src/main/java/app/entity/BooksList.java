package app.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksList {

    private String requestedUrl;
    private List<BookRecord> bookRecordList;

    @JsonGetter("requestedUrl")
    public String getRequestedUrl() {
        return requestedUrl;
    }

    @JsonSetter("requestedUrl")
    public void setRequestedUrl(String requestedUrl) {
        this.requestedUrl = requestedUrl;
    }

    @JsonGetter("items")
    public List<BookRecord> getBookRecords() {
        return bookRecordList;
    }

    @JsonSetter("items")
    public void setBookRecords(List<BookRecord> bookRecordList) {
        this.bookRecordList = bookRecordList;
    }
}