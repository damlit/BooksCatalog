package app.boundary;

import app.entity.BooksList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class JsonMapper {
    private BooksList booksList;

    public JsonMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/jsonFiles/books.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            booksList = objectMapper.readValue(reader, BooksList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BooksList getBooksList() {
        return booksList;
    }
}
