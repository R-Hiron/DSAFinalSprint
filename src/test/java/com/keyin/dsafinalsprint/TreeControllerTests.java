package com.keyin.dsafinalsprint;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TreeControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testProcessNumbersEndpoint() {
        String[] numbers = { "10", "5", "15" };
        ResponseEntity<String> response = restTemplate.postForEntity("/process-numbers", numbers, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("\"value\":10"));
    }

    @Test
    public void testPreviousTreesEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity("/previous-trees", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
