package com.cv.sc.repository;

import com.cv.sc.models.SearchParent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchParentRepository extends CrudRepository<SearchParent, Long> {

    SearchParent findById(long id);
}
