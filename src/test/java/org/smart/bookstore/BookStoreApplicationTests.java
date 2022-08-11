package org.smart.bookstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.smart.bookstore.data.entities.Book;
import org.smart.bookstore.model.Cart;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BookStoreApplicationTests {
    @LocalServerPort
    int randomServerPort;

    @Test
    public void getAllBooks() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books";
        URI uri = new URI(baseUrl);
        ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
        System.out.println(result.getBody());
    }

    @Test
    public void getBooksByPagination() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books?size=2&page=2";
        URI uri = new URI(baseUrl);
        ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
        System.out.println(result.getBody());
    }

    @Test
    public void getBookByPathVariable() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/100";
        URI uri = new URI(baseUrl);
        ResponseEntity<Book> result = restTemplate.getForEntity(uri, Book.class);
        System.out.println(result.getBody());
    }

    @Test
    public void getBookByRequestParam() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books?id=100";
        URI uri = new URI(baseUrl);
        ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
        System.out.println(result.getBody());
    }

    @Test
    public void checkout() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/checkout";
        URI uri = new URI(baseUrl);

        int[] arr = {100, 101, 102, 103, 104, 105, 106, 107};

        Cart cart = restTemplate.postForObject(uri, arr, Cart.class);
        System.out.println(cart);
    }

    @Test
    public void checkoutWithValidPromoCode() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/checkout?promoCode=flat200";
        URI uri = new URI(baseUrl);

        int[] arr = {100, 101, 102, 103, 104, 105, 106, 107};

        Cart cart = restTemplate.postForObject(uri, arr, Cart.class);
        System.out.println(cart);
    }

    @Test
    public void checkoutWithInvalidPromoCode() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/checkout?promoCode=flat300";
        URI uri = new URI(baseUrl);

        int[] arr = {100, 101, 102, 103, 104, 105, 106, 107};

        Cart cart = restTemplate.postForObject(uri, arr, Cart.class);
        System.out.println(cart);
    }
}