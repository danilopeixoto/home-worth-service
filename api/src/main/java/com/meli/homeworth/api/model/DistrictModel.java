package com.meli.homeworth.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("district")
public class DistrictModel {
  @NotNull(message = "The ID field is required.")
  @JsonProperty("id")
  @Column("id")
  @Id
  private UUID id;

  @Pattern(regexp = "[A-Z].*", message = "The name field must start with a capital letter.")
  @Length(max = 45, message = "The name field must have a maximum of 45 characters.")
  @NotBlank(message = "The name field is required.")
  @JsonProperty("name")
  @Column("name")
  private String name;

  @Min(value = 0, message = "The square meter price field must have a value greater than or equal to zero.")
  @NotNull(message = "The square meter price field is required.")
  @JsonProperty("square_meter_price")
  @Column("square_meter_price")
  private Double squareMeterPrice;
}
