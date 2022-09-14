package com.cv.sc.repository;

import com.cv.sc.models.Config;
import com.cv.sc.models.SearchResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends CrudRepository<Config, Long>{

    Config findById(long id);
}
