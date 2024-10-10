package org.playwright.requestdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;

import java.io.IOException;

public class ObjectMapperClass {

    public static JsonNode getJsonResponse(APIResponse response) throws IOException {
        ObjectMapper objectMapperAuth = new ObjectMapper();
        return objectMapperAuth.readTree(response.body());
    }
}
