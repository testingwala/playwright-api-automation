package org.playwright;

import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.playwright.requestdata.ObjectMapperClass;
import org.playwright.requestdata.RequestData;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PutApiCall {
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
        // AUTH
        APIResponse apiAuthResponse = requestContext.post("https://restful-booker.herokuapp.com/auth",
                RequestOptions.create()
                              .setData(RequestData.requestDataAuth()));
        int statusCodeAuth = apiAuthResponse.status();

        JsonNode authJsonResponse = ObjectMapperClass.getJsonResponse(apiAuthResponse);
        Assert.assertEquals(statusCodeAuth, 200);
        String token = authJsonResponse.get("token").asText();

        // POST
        APIResponse apiPostResponse = requestContext.post("https://restful-booker.herokuapp.com/booking",
                RequestOptions.create()
                              .setData(RequestData.requestDataPost()));
        int statusCode = apiPostResponse.status();

        JsonNode postJsonResponse = ObjectMapperClass.getJsonResponse(apiPostResponse);
        Assert.assertEquals(statusCode, 200);
        int bookingId = postJsonResponse.get("bookingid").asInt();

        // PUT
        APIResponse apiPutResponse = requestContext.put("https://restful-booker.herokuapp.com/booking/"+bookingId,
                RequestOptions.create()
                              .setHeader("Content-Type", "application/json")
                              .setHeader("Cookie", "token="+token)
                              .setData(RequestData.requestDataPut()));

        int statusCodePut = apiPutResponse.status();

        JsonNode putJsonResponse = ObjectMapperClass.getJsonResponse(apiPutResponse);
        System.out.println(putJsonResponse.toPrettyString());

        Assert.assertEquals(statusCodePut, 200);
    }

    @AfterTest
    public void tearDown(){
        playwright.close();
    }
}
