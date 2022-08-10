package org.smart.bookstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.smart.bookstore.data.repositories.entities.Book;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BookStoreApplicationTests {
    @LocalServerPort
    int randomServerPort;

    @Test
    public void testGetEmployeeListSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/";
        URI uri = new URI(baseUrl);
        ResponseEntity<Book> result = restTemplate.getForEntity(uri, Book.class);
        System.out.println(result);
    }
}