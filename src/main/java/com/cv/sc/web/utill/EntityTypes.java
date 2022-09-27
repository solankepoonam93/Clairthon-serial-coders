package com.cv.sc.web.utill;

import com.cv.sc.model.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
public class EntityTypes {
    private EntityTypes() {
        // all static
    }
    private static final Map<String, Class> entityClassMap = new HashMap<>();

    static { // miserable, but no time to think
        entityClassMap.put(APIResponse.class.getSimpleName(), APIResponse.class);
        entityClassMap.put(Config.class.getSimpleName(), Config.class);
        entityClassMap.put(Report.class.getSimpleName(), Report.class);
        entityClassMap.put(SearchParent.class.getSimpleName(), SearchParent.class);
        entityClassMap.put(SearchResult.class.getSimpleName(), SearchResult.class);
        entityClassMap.put(SearchResponse.class.getSimpleName(), SearchResponse.class);
    }

    public static void addEntity(String entityName, Class entityClass) {
        entityClassMap.put(entityName, entityClass);
    }

    public static Class getEntityClass(String entityName) {
        return entityClassMap.get(entityName);
    }
}
