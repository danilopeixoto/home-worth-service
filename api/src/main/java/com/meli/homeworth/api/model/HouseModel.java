package com.meli.homeworth.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HouseModel {
  @Pattern(regexp = "[A-Z].*", message = "The name field must start with a capital letter.")
  @Length(max = 30, message = "The name field must have a maximum of 30 characters.")
  @NotBlank(message = "The name field is required.")
  @JsonProperty("name")
  private String name;

  @Length(max = 45, message = "The district field must have a maximum of 45 characters.")
  @NotBlank(message = "The district field is required.")
  @JsonProperty("district")
  private String district;

  @Valid
  @NotEmpty(message = "The rooms field must contains at least one room.")
  @JsonProperty("rooms")
  private List<RoomModel> rooms;

  @JsonIgnore
  public Double calculateArea() {
    return this.rooms
      .stream()
      .map(RoomModel::calculateArea)
      .reduce(0.0, Double::sum);
  }
}
