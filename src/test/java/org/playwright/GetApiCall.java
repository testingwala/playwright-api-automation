package org.playwright;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetApiCall {
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
    public void getBookingIds() {
        APIResponse apiResponse = requestContext.get("https://restful-booker.herokuapp.com");
        int statusCode = apiResponse.status();

        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(apiResponse.ok());
    }

    @AfterTest
    public void tearDown(){
        playwright.close();
    }
}
