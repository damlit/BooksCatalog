package app.boundary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeworkApplication {

    public static JsonMapper jsonMapper;

    public static void main(String[] args) {
        jsonMapper = new JsonMapper();
        SpringApplication.run(HomeworkApplication.class, args);
    }
}