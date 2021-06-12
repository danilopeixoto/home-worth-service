package com.meli.homeworth.api.repository;

import com.meli.homeworth.api.model.DistrictModel;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface DistrictRepository extends ReactiveSortingRepository<DistrictModel, UUID> {
  @Query("select * from district where name = :name")
  Mono<DistrictModel> findByName(@Param("name") String name);
}
