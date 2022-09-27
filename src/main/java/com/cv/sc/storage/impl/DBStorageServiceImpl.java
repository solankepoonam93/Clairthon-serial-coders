package com.cv.sc.storage.impl;

import com.cv.sc.storage.EntityManagerProvider;
import com.cv.sc.storage.StorageService;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

public class DBStorageServiceImpl implements StorageService {

    private EntityManager entityManager;

    public DBStorageServiceImpl() {
         entityManager = EntityManagerProvider.getInstance().getEntityManager();
    }

    public Object save(Object searchParent) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(searchParent);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }

        return searchParent;
    }

    @Override
    public Object fetch(Class entityClass, Object id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public Object delete(Class entityClass, Object pk) throws IOException {
        return null;
    }

    @Override
    public Object update(Object value) throws IOException {
        return null;
    }

    public List findAll(Class entityClass) {
        return entityManager.createQuery("Select t from " + entityClass.getSimpleName() + " t").getResultList();
    }

}
