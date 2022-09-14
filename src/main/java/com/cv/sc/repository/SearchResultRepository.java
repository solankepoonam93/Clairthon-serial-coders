package com.cv.sc.repository;

import com.cv.sc.models.SearchResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchResultRepository extends CrudRepository<SearchResult, Long> {

    SearchResult save(SearchResult resultModel);
}
