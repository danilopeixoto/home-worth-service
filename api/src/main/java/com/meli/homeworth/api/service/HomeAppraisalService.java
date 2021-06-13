package com.meli.homeworth.api.service;

import com.meli.homeworth.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
public class HomeAppraisalService {
  @Autowired
  private DistrictService districtService;

  public Mono<HouseAreaResponse> calculateArea(Mono<HouseModel> houseRequest) {
    return houseRequest.map(house -> new HouseAreaResponse(house, house.calculateArea()));
  }

  public HouseValuationResponse calculateValuation(HouseModel house, DistrictModel district) {
    return new HouseValuationResponse(house, house.calculateArea() * district.getSquareMeterPrice());
  }

  public Mono<RoomAreaResponse> getLargestRoom(Mono<HouseModel> houseRequest) {
    return houseRequest
      .flatMapMany(house -> Flux.fromIterable(house.getRooms()))
      .sort(Comparator.comparing(RoomModel::calculateArea))
      .last()
      .map(room -> new RoomAreaResponse(room, room.calculateArea()));
  }

  public Flux<RoomAreaResponse> calculateRoomAreas(Mono<HouseModel> houseRequest) {
    return houseRequest
      .flatMapMany(house -> Flux.fromIterable(house.getRooms()))
      .map(room -> new RoomAreaResponse(room, room.calculateArea()));
  }
}
