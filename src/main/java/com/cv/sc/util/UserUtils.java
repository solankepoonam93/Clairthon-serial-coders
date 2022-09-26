package com.cv.sc.util;

import java.util.HashMap;
import java.util.Map;

public class UserUtils {

    public static final String AUTH_TOKEN = "AUTH_TOKEN";

    public String getRequestUrlQuery(String url, Map<String, String> params){
        StringBuilder requestQueryUrl = new StringBuilder();
        requestQueryUrl.append(url);
        for(String key: params.keySet()) {
            if(key.equals("q")) {
                requestQueryUrl.append("?");
                requestQueryUrl.append(key);
                requestQueryUrl.append("=");
                requestQueryUrl.append(params.get(key));
            } else {
                requestQueryUrl.append("&");
                requestQueryUrl.append(key);
                requestQueryUrl.append(":");
                requestQueryUrl.append(params.get(key));
            }
        }
        return requestQueryUrl.toString();

    }

    public Map<String, String> getHeaders() {
        String authToken =System.getenv(AUTH_TOKEN);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.AUTH_HEADER, authToken);
        headers.put(Constants.ACCEPT_HEADER, Constants.ACCEPT_HEADER_VALUE);
        return headers;
    }
}
