package com.cv.sc.model;

import org.json.simple.JSONObject;

import java.util.Map;

public class SearchResponse {

    public JSONObject getResponse() {
        return response;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    JSONObject response;
}
