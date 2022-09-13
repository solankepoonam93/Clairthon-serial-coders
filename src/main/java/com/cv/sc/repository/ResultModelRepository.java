package com.cv.sc.repository;

import com.cv.sc.models.ResultModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultModelRepository extends CrudRepository<ResultModel, Long> {

    ResultModel save(ResultModel resultModel);
}
