package com.cv.sc.storage;

import com.cv.sc.model.SCEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface StorageService<T extends SCEntity> {

    Object save(Object o) throws UnsupportedEncodingException;

    Object fetch(Class entityClass, Object pk) throws IOException;

    Object delete(Class entityClass, Object pk) throws IOException;

    Object update(Object value) throws IOException;

    List<T> findAll(Class entityClass) throws IOException;

    List<T> fetchWithPredicate(Class entityClass, String paramName, String value);
}
