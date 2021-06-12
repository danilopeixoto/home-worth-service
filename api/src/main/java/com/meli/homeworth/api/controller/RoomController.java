package com.meli.homeworth.api.controller;

import com.meli.homeworth.api.model.ErrorResponseModel;
import com.meli.homeworth.api.model.HouseModel;
import com.meli.homeworth.api.model.RoomAreaResponse;
import com.meli.homeworth.api.service.HomeAppraisalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Tag(name = "Rooms")
@Validated
@RequestMapping("/rooms")
@RestController
public class RoomController {
  @Autowired
  private HomeAppraisalService service;

  @Operation(summary = "Get largest room", responses = {
    @ApiResponse(
      responseCode = "200",
      content = @Content(
        schema = @Schema(implementation = RoomAreaResponse.class),
        mediaType = "application/json")),
    @ApiResponse(
      responseCode = "400",
      content = @Content(
        schema = @Schema(implementation = ErrorResponseModel.class),
        mediaType = "application/json")),
    @ApiResponse(
      responseCode = "500",
      content = @Content(
        schema = @Schema(implementation = ErrorResponseModel.class),
        mediaType = "application/json"))
  })
  @PostMapping("/largest")
  public Mono<RoomAreaResponse> getLargest(@Valid @RequestBody Mono<HouseModel> houseRequest) {
    return this.service.getLargestRoom(houseRequest);
  }

  @Operation(summary = "Calculate room areas", responses = {
    @ApiResponse(
      responseCode = "200",
      content = @Content(
        array = @ArraySchema(schema = @Schema(implementation = RoomAreaResponse.class)),
        mediaType = "application/json")),
    @ApiResponse(
      responseCode = "400",
      content = @Content(
        schema = @Schema(implementation = ErrorResponseModel.class),
        mediaType = "application/json")),
    @ApiResponse(
      responseCode = "500",
      content = @Content(
        schema = @Schema(implementation = ErrorResponseModel.class),
        mediaType = "application/json"))
  })
  @PostMapping("/areas")
  public Flux<RoomAreaResponse> calculateAreas(@Valid @RequestBody Mono<HouseModel> houseRequest) {
    return this.service.calculateRoomAreas(houseRequest);
  }
}
