package com.meli.homeworth.api.controller;

import com.meli.homeworth.api.configuration.ModelMapperConfiguration;
import com.meli.homeworth.api.model.HouseModel;
import com.meli.homeworth.api.repository.DistrictRepository;
import com.meli.homeworth.api.service.DistrictService;
import com.meli.homeworth.api.service.HomeAppraisalService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@Import({
  DistrictService.class,
  HomeAppraisalService.class,
  ModelMapperConfiguration.class
})
@WebFluxTest(properties = "spring.profiles.active:development", controllers = RoomController.class)
public class RoomControllerTest {
  @Autowired
  private WebTestClient client;

  @MockBean
  private DistrictRepository districtRepository;

  @ParameterizedTest
  @MethodSource("com.meli.homeworth.api.util.FixtureParameterProvider#buildHouse")
  public void shouldGetLargestSuccessfully(HouseModel houseRequest) {
    client
      .post()
      .uri("/rooms/largest")
      .contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromValue(houseRequest))
      .exchange()
      .expectStatus()
      .is2xxSuccessful();
  }

  @ParameterizedTest
  @MethodSource("com.meli.homeworth.api.util.FixtureParameterProvider#buildHouse")
  public void shouldCalculateAreasSuccessfully(HouseModel houseRequest) {
    client
      .post()
      .uri("/rooms/areas")
      .contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromValue(houseRequest))
      .exchange()
      .expectStatus()
      .is2xxSuccessful();
  }
}
