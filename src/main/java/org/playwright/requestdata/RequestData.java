package org.playwright.requestdata;

import java.util.HashMap;
import java.util.Map;

public class RequestData {

    public static Map<String, Object> requestDataPost() {
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
        return data;
    }

    public static Map<String, Object> requestDataAuth() {
        Map<String, Object> data = new HashMap<>();
        data.put("username", "admin");
        data.put("password", "password123");

        return data;
    }

    public static Map<String, Object> requestDataPut() {
        Map<String, Object> data = new HashMap<>();
        data.put("firstname", "Shriniwas");
        data.put("lastname", "Alle");
        data.put("totalprice", 100);
        data.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2024-10-02");
        bookingDates.put("checkout", "2024-11-02");

        data.put("bookingdates", bookingDates);
        data.put("additionalneeds", "Dinner");
        return data;
    }
}
