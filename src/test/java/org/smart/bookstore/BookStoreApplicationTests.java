package org.smart.bookstore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.smart.bookstore.data.entities.Book;
import org.smart.bookstore.data.entities.BookType;
import org.smart.bookstore.data.entities.Discount;
import org.smart.bookstore.data.entities.PromoCode;
import org.smart.bookstore.model.Cart;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BookStoreApplicationTests {

    String validPromoCode = "flat200";
    String invalidPromoCode = "flat300";

    @LocalServerPort
    int randomServerPort;

    @Test
    public void getAllBooks() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books";
        URI uri = new URI(baseUrl);
        ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
        Assert.assertEquals(Objects.requireNonNull(result.getBody()).size(), 9);
    }

    @Test
    public void getBooksByPagination() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books?size=2&page=2";
        URI uri = new URI(baseUrl);
        ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
        Assert.assertEquals(Objects.requireNonNull(result.getBody()).size(), 2);
    }

    @Test
    public void getBookByPathVariable() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/100";
        URI uri = new URI(baseUrl);
        ResponseEntity<Book> result = restTemplate.getForEntity(uri, Book.class);
        Assert.assertEquals(Objects.requireNonNull(result.getBody()).getISBN(), 100);
    }

    @Test
    public void getBookByRequestParam() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books?id=100";
        URI uri = new URI(baseUrl);
        ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
        Assert.assertEquals(Objects.requireNonNull(result.getBody()).size(), 1);
    }

    @Test
    public void checkout() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/checkout";
        URI uri = new URI(baseUrl);

        int[] arr = {100, 101, 102, 103, 104, 105, 106, 107};

        Cart cart = restTemplate.postForObject(uri, arr, Cart.class);
        assert cart != null;
        Assert.assertEquals(cart.getPayableAmount(), Optional.of(1377.5));
    }

    @Test
    public void checkoutWithValidPromoCode() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/checkout?promoCode=" + validPromoCode;
        URI uri = new URI(baseUrl);

        int[] arr = {100, 101, 102, 103, 104, 105, 106, 107};

        Cart cart = restTemplate.postForObject(uri, arr, Cart.class);
        assert cart != null;
        Assert.assertEquals(cart.getMessage().get().stream().filter(x -> x.contains("Flat")).findAny(),
                Optional.of("Flat Rs.200 discount on amount Rs.1377.5 is Rs.1177.5"));
    }

    @Test
    public void checkoutWithInvalidPromoCode() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/checkout?promoCode=" + invalidPromoCode;
        URI uri = new URI(baseUrl);

        int[] arr = {100, 101, 102, 103, 104, 105, 106, 107};

        Cart cart = restTemplate.postForObject(uri, arr, Cart.class);
        assert cart != null;
        Assert.assertEquals(cart.getMessage().get().stream().filter(x -> x.contains(String.format("Promo code %s is invalid or inactive", "flat300"))).findAny(),
                Optional.of("Promo code flat300 is invalid or inactive"));
    }

    @Test
    public void saveDiscount() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/discount";
        URI uri = new URI(baseUrl);

        Discount discount = new Discount(BookType.FANTASY, 100, true);

        Discount discountPersist = restTemplate.postForObject(uri, discount, Discount.class);
        assert discountPersist != null;
    }

    @Test
    public void savePromoCode() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books/promocode";
        URI uri = new URI(baseUrl);

        PromoCode promoCode = new PromoCode("flat400", 100, 1000, true);

        PromoCode promoCodePersist = restTemplate.postForObject(uri, promoCode, PromoCode.class);
        assert promoCodePersist != null;
    }

    @Test
    public void saveBook() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/books";
        URI uri = new URI(baseUrl);

        Book book = new Book("name", "desc", "author", BookType.FANTASY, 100);

        Book bookPersist = restTemplate.postForObject(uri, book, Book.class);
        assert bookPersist != null;
    }
}