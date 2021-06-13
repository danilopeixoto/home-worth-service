package com.meli.homeworth.api.service;

import com.meli.homeworth.api.model.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active:development")
public class HomeAppraisalServiceTest {
  @Autowired
  private HomeAppraisalService service;

  @ParameterizedTest
  @MethodSource("com.meli.homeworth.api.util.FixtureParameterProvider#buildHouse")
  public void shouldCalculateAreaEqualsToOneHundred(HouseModel house) {
    StepVerifier
      .create(this.service
        .calculateArea(Mono.just(house))
        .map(HouseAreaResponse::getArea))
      .expectNext(100.0)
      .verifyComplete();
  }

  @ParameterizedTest
  @MethodSource("com.meli.homeworth.api.util.FixtureParameterProvider#buildHouseAndDistrict")
  public void shouldCalculateValuationEqualsToOneThousand(HouseModel house, DistrictModel district) {
    StepVerifier
      .create(Mono
        .just(this.service.calculateValuation(house, district))
        .map(HouseValuationResponse::getValuation))
      .expectNext(1000.0)
      .verifyComplete();
  }

  @ParameterizedTest
  @MethodSource("com.meli.homeworth.api.util.FixtureParameterProvider#buildHouse")
  public void shouldGetLargestRoomWithAreaEqualsToEighty(HouseModel house) {
    StepVerifier
      .create(this.service
        .getLargestRoom(Mono.just(house))
        .map(RoomAreaResponse::getArea))
      .expectNext(80.0)
      .verifyComplete();
  }

  @ParameterizedTest
  @MethodSource("com.meli.homeworth.api.util.FixtureParameterProvider#buildHouse")
  public void shouldCalculateRoomAreasEqualToEightyAndTwenty(HouseModel house) {
    StepVerifier
      .create(this.service
        .calculateRoomAreas(Mono.just(house))
        .map(RoomAreaResponse::getArea))
      .expectNext(80.0)
      .expectNext(20.0)
      .verifyComplete();
  }
}
