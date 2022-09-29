package com.cv.sc.cache;

import java.util.HashSet;
import java.util.Set;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
public class TokenCache {
    // pathetic implementation- but let's keep it for now
    private static final Set<String> tokenSet = new HashSet<>();

    private TokenCache() {
        // all static methods
    }

    public static Boolean isTokenPresent(String token) {
        return tokenSet.contains(token);
    }

    public static void addToken(String token) {
        tokenSet.add(token);
    }

    public static void removeToken(String token) {
        tokenSet.remove(token);
    }
}
