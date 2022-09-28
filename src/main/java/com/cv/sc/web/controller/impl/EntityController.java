package com.cv.sc.web.controller.impl;

import com.cv.sc.model.SCEntity;
import com.cv.sc.storage.StorageService;
import com.cv.sc.storage.impl.DBStorageServiceImpl;
import com.cv.sc.web.controller.SCController;
import com.cv.sc.web.utill.EntityTypes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
@RestController
@RequestMapping("/dao")
public class EntityController<T extends SCEntity> implements SCController {

    private StorageService dbStorageService;

    private ObjectMapper objectMapper;

    public EntityController() {
        this.dbStorageService = DBStorageServiceImpl.getInstance();
        objectMapper = new ObjectMapper();
    }

    @PostMapping(path = "/persist/{entityName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public T persist(@PathVariable String entityName, @RequestBody String entityJson) throws UnsupportedEncodingException, JsonProcessingException {

        Class<T> clazz = EntityTypes.getEntityClass(entityName);
        T t = objectMapper.readValue(entityJson, clazz);
        t = (T) dbStorageService.save(t);
        return t;
    }

    @GetMapping(path = "/fetch/{entityName}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public T fetch(@PathVariable Long id, @PathVariable String entityName) throws IOException {
        Class entityClass = EntityTypes.getEntityClass(entityName);
        return (T) dbStorageService.fetch(entityClass, id);
    }

    @GetMapping(path = "/delete/{entityName}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public T delete(@PathVariable Long id, @PathVariable String entityName) throws IOException {
        Class entityClass = EntityTypes.getEntityClass(entityName);
        return (T) dbStorageService.delete(entityClass, id);
    }

    @PostMapping(path = "/update/{entityName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public T update(@PathVariable String entityName, @RequestBody String entityJson) throws IOException {
        Class<T> entityClass = EntityTypes.getEntityClass(entityName);
        T t = objectMapper.readValue(entityJson, entityClass);
        return (T) dbStorageService.update(t);
    }

    @GetMapping(path = "/fetchAll/{entityName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<T> fetchAll(@PathVariable String entityName) throws IOException {
        Class entityClass = EntityTypes.getEntityClass(entityName);
        List<T> t= dbStorageService.findAll(entityClass);
        return t;
    }
}
