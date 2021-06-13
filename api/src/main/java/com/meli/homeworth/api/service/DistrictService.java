package com.meli.homeworth.api.service;

import com.meli.homeworth.api.model.DistrictModel;
import com.meli.homeworth.api.model.DistrictPartialRequestModel;
import com.meli.homeworth.api.model.DistrictRequestModel;
import com.meli.homeworth.api.repository.DistrictRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DistrictService {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private DistrictRepository repository;

  public Mono<DistrictModel> create(Mono<DistrictRequestModel> districtRequest) {
    return districtRequest
      .map(district -> this.modelMapper.map(district, DistrictModel.class))
      .flatMap(this.repository::save)
      .onErrorMap(
        DataIntegrityViolationException.class,
        (exception) -> new IllegalStateException("District name already exists."));
  }

  public Mono<DistrictModel> findById(UUID id) {
    return this.repository
      .findById(id)
      .switchIfEmpty(Mono.error(new NoSuchElementException("District resource not found.")));
  }

  public Mono<DistrictModel> findByName(String name) {
    return this.repository
      .findByName(name)
      .switchIfEmpty(Mono.error(new NoSuchElementException("District resource not found.")));
  }

  public Flux<DistrictModel> list(Pageable pageable) {
    return this.repository
      .findAll(pageable.getSort())
      .onErrorMap(
        BadSqlGrammarException.class,
        (exception) -> new IllegalArgumentException("Invalid sort parameter."))
      .skip(pageable.getOffset())
      .take(pageable.getPageSize());
  }

  public Mono<DistrictModel> update(UUID id, Mono<DistrictRequestModel> districtRequest) {
    return this.repository
      .findById(id)
      .switchIfEmpty(Mono.error(new NoSuchElementException("District resource not found.")))
      .flatMap(district -> districtRequest
        .map(districtUpdate -> this.modelMapper.map(districtUpdate, DistrictModel.class))
        .map(districtUpdate -> districtUpdate
          .toBuilder()
          .id(district.getId())
          .build()))
      .flatMap(this.repository::save)
      .onErrorMap(
        DataIntegrityViolationException.class,
        (exception) -> new IllegalStateException("District name already exists."));
  }

  public Mono<DistrictModel> partialUpdate(UUID id, Mono<DistrictPartialRequestModel> districtRequest) {
    return this.repository
      .findById(id)
      .switchIfEmpty(Mono.error(new NoSuchElementException("District resource not found.")))
      .flatMap(district -> districtRequest
        .doOnNext(districtUpdate -> this.modelMapper.map(districtUpdate, district))
        .thenReturn(district))
      .flatMap(this.repository::save)
      .onErrorMap(
        DataIntegrityViolationException.class,
        (exception) -> new IllegalStateException("District name already exists."));
  }

  public Mono<DistrictModel> delete(UUID id) {
    return this.repository
      .findById(id)
      .flatMap(district -> this.repository
        .deleteById(district.getId())
        .thenReturn(district));
  }
}
