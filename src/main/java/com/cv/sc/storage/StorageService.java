package com.cv.sc.storage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface StorageService {

    Object save(Object o) throws UnsupportedEncodingException;

    Object fetch(Class entityClass, Object pk) throws IOException;

    Object delete(Class entityClass, Object pk) throws IOException;

    Object update(Object value) throws IOException;

    List findAll(Class entityClass) throws IOException;
}
