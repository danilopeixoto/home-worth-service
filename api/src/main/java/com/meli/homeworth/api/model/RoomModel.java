package com.meli.homeworth.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomModel {
  @Pattern(regexp = "[A-Z].*", message = "The name field must start with a capital letter.")
  @Length(max = 30, message = "The name field must have a maximum of 30 characters.")
  @NotBlank(message = "The name field is required.")
  @JsonProperty("name")
  private String name;

  @Max(value = 25, message = "The width field must have a value less than or equal to 25.")
  @Min(value = 0, message = "The width field must have a value greater than or equal to zero.")
  @NotNull(message = "The width field is required.")
  @JsonProperty("width")
  private Double width;

  @Max(value = 33, message = "The length field must have a value less than or equal to 33.")
  @Min(value = 0, message = "The length field must have a value greater than or equal to zero.")
  @NotNull(message = "The length field is required.")
  @JsonProperty("length")
  private Double length;

  @JsonIgnore
  public Double calculateArea() {
    return this.width * this.length;
  }
}
