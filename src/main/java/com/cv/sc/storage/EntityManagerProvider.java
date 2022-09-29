package com.cv.sc.storage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 15/09/22
 */
public class EntityManagerProvider {
    private static EntityManagerProvider instance;

    private final EntityManager entityManager;

    private EntityManagerProvider() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myDB");
        entityManager = factory.createEntityManager();
    }

    public static EntityManagerProvider getInstance() {
        if (instance == null) {
            instance = new EntityManagerProvider();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
