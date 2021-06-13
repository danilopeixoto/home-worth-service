package com.meli.homeworth.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DistrictPartialRequestModel {
  @Pattern(regexp = "[A-Z].*", message = "The name field must start with a capital letter.")
  @Size(max = 45, message = "The name field must have between one and 45 characters.")
  @JsonProperty("name")
  private String name;

  @Min(value = 0, message = "The square meter price field must have a value greater than or equal to zero.")
  @JsonProperty("square_meter_price")
  private Double squareMeterPrice;
}
