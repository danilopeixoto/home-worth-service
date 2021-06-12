package com.meli.homeworth.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HouseAreaResponse {
  @Valid
  @NotNull(message = "The house field is required.")
  @JsonProperty("house")
  private HouseModel house;

  @Min(value = 0, message = "The area field must have a value greater than or equal to zero.")
  @NotNull(message = "The area field is required.")
  @JsonProperty("area")
  private Double area;
}
