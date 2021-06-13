package com.meli.homeworth.api.configuration;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();

    mapper
      .getConfiguration()
      .setMatchingStrategy(MatchingStrategies.STRICT)
      .setPropertyCondition(Conditions.isNotNull());

    return mapper;
  }
}
