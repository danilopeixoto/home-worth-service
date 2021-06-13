package com.meli.homeworth.api.util;

import com.meli.homeworth.api.model.DistrictModel;
import com.meli.homeworth.api.model.HouseModel;
import com.meli.homeworth.api.model.RoomModel;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class FixtureParameterProvider {
  public static Stream<DistrictModel> buildDistrict() {
    return Stream.of(new DistrictModel(UUID.randomUUID(), "Downtown", 10.0));
  }

  public static Stream<HouseModel> buildHouse() {
    return Stream
      .of(new HouseModel(
        "Devonshire House",
        "Downtown",
        List.of(
          new RoomModel("Kitchen", 10.0, 8.0),
          new RoomModel("Utility Room", 10.0, 2.0))));
  }

  public static Stream<Arguments> buildHouseAndDistrict() {
    return Stream.of(
      Arguments.of(
        FixtureParameterProvider
          .buildHouse()
          .findFirst()
          .orElseThrow(),
        FixtureParameterProvider
          .buildDistrict()
          .findFirst()
          .orElseThrow()));
  }
}
