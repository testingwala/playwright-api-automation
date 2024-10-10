package org.playwright;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostApiCall {
    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;

    @BeforeTest
    public void setup(){
        playwright = Playwright.create();
        request =  playwright.request();
        requestContext = request.newContext();
    }

    @Test
    public void createBooking() throws IOException {

        Map<String, Object> data = new HashMap<>();
        data.put("firstname", "Shriniwas");
        data.put("lastname", "Alle");
        data.put("totalprice", 100);
        data.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2024-10-02");
        bookingDates.put("checkout", "2024-11-02");

        data.put("bookingdates", bookingDates);
        data.put("additionalneeds", "Lunch");


        APIResponse apiResponse = requestContext.post("https://restful-booker.herokuapp.com/booking",
                RequestOptions.create()
                              .setData(data));
        int statusCode = apiResponse.status();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode postJsonResponse = objectMapper.readTree(apiResponse.body());
        System.out.println(postJsonResponse.toPrettyString());

        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(apiResponse.ok());
        Assert.assertEquals(postJsonResponse.get("booking").get("firstname").asText(), "Shriniwas");
        Assert.assertEquals(postJsonResponse.get("booking").get("lastname").asText(), "Alle");
    }

    @AfterTest
    public void tearDown(){
        playwright.close();
    }
}
