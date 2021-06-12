package com.meli.homeworth.api.controller;

import com.meli.homeworth.api.model.ErrorResponseModel;
import com.meli.homeworth.api.model.HouseAreaResponse;
import com.meli.homeworth.api.model.HouseModel;
import com.meli.homeworth.api.model.HouseValuationResponse;
import com.meli.homeworth.api.service.HomeAppraisalService;
import io.swagger.v3.oas.annotations.Operation;
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
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Tag(name = "Houses")
@Validated
@RequestMapping("/houses")
@RestController
public class HouseController {
  @Autowired
  private HomeAppraisalService service;

  @Operation(summary = "Calculate house area", responses = {
    @ApiResponse(
      responseCode = "200",
      content = @Content(
        schema = @Schema(implementation = HouseAreaResponse.class),
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
  @PostMapping("/area")
  public Mono<HouseAreaResponse> calculateArea(@Valid @RequestBody Mono<HouseModel> houseRequest) {
    return this.service.calculateArea(houseRequest);
  }

  @Operation(summary = "Calculate house valuation", responses = {
    @ApiResponse(
      responseCode = "200",
      content = @Content(
        schema = @Schema(implementation = HouseValuationResponse.class),
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
  @PostMapping("/valuation")
  public Mono<HouseValuationResponse> calculateValuation(@Valid @RequestBody Mono<HouseModel> houseRequest) {
    return this.service.calculateValuation(houseRequest);
  }
}
