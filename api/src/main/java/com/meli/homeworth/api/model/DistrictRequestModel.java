package com.meli.homeworth.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DistrictRequestModel {
  @Pattern(regexp = "[A-Z].*", message = "The name field must start with a capital letter.")
  @Length(max = 45, message = "The name field must have a maximum of 45 characters.")
  @NotBlank(message = "The name field is required.")
  @JsonProperty("name")
  private String name;

  @Min(value = 0, message = "The square meter price field must have a value greater than or equal to zero.")
  @NotNull(message = "The square meter price field is required.")
  @JsonProperty("square_meter_price")
  private Double squareMeterPrice;
}
