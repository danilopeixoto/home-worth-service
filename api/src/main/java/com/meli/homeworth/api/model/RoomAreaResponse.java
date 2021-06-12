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
public class RoomAreaResponse {
  @Valid
  @NotNull(message = "The room field is required.")
  @JsonProperty("room")
  private RoomModel room;

  @Min(value = 0, message = "The area field must have a value greater than or equal to zero.")
  @NotNull(message = "The area field is required.")
  @JsonProperty("area")
  private Double area;
}
