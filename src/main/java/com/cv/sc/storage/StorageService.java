package com.cv.sc.storage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface StorageService {

    public Object save(Object o) throws UnsupportedEncodingException;

    public Object fetch(Class entityClass, Object pk) throws IOException;
}
