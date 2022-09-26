package com.cv.sc.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

/**
 * <br>
 * Created By: bhushan.karmarkar12@gmail.com
 * <br>
 * Date: 26/09/22
 */
public class ObjectMapperProvider {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private ObjectMapperProvider() {
        // all static
    }

    public static ObjectMapper getObjectMapperForParsingResult() {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return objectMapper;
    }
}
