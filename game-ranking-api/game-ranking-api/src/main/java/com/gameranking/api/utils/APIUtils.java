package com.gameranking.api.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.Timestamp;

public class APIUtils {

    private static final Gson gson = new Gson();

    public static JsonObject buildResponseBody(Object obj) {
        JsonObject responseBody = new JsonObject();
        responseBody.addProperty("timestamp", new Timestamp(System.currentTimeMillis()).toString());
        responseBody.add("responseBody", gson.toJsonTree(obj));

        return responseBody;
    }

    public static JsonObject buildErrorResponse(String erro) {
        JsonObject responseBody = new JsonObject();
        responseBody.addProperty("timestamp", new Timestamp(System.currentTimeMillis()).toString());
        responseBody.addProperty("errorMessage", erro);

        return responseBody;
    }
}
